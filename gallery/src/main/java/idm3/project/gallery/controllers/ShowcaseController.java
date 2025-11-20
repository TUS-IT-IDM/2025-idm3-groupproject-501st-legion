package idm3.project.gallery.controllers;

import idm3.project.gallery.model.Showcase;
import idm3.project.gallery.service.ShowcaseService;
import idm3.project.gallery.service.ProjectService;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/showcases")
public class ShowcaseController {

    private final ShowcaseService showcaseService;
    private final ProjectService projectService;

    public ShowcaseController(ShowcaseService showcaseService, ProjectService projectService) {
        this.showcaseService = showcaseService;
        this.projectService = projectService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("showcases", showcaseService.findAll());
        return "showcase";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("showcase", new Showcase());
        model.addAttribute("formAction", "/showcases/add");
        return "showcase-form";
    }

    @PostMapping("/add")
    public String create(
            @Valid @ModelAttribute("showcase") Showcase showcase,
            BindingResult result,
            @RequestParam("imageFile") MultipartFile imageFile,
            Model model,
            RedirectAttributes ra) {

        if (result.hasErrors()) {
            model.addAttribute("formAction", "/showcases/add");
            return "showcase-form";
        }

        showcaseService.saveShowcase(showcase, imageFile);
        ra.addFlashAttribute("success", "Showcase created successfully!");
        return "redirect:/showcases";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model, RedirectAttributes ra) {
        Showcase showcase = showcaseService.findById(id);

        if (showcase == null) {
            ra.addFlashAttribute("error", "Showcase not found.");
            return "redirect:/showcases";
        }

        model.addAttribute("showcase", showcase);
        model.addAttribute("formAction", "/showcases/edit/" + id);
        return "showcase-form";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable long id,
            @Valid @ModelAttribute("showcase") Showcase showcase,
            BindingResult result,
            @RequestParam("imageFile") MultipartFile imageFile,
            Model model,
            RedirectAttributes ra) {

        if (result.hasErrors()) {
            model.addAttribute("formAction", "/showcases/edit/" + id);
            return "showcase-form";
        }

        showcase.setShowcaseId(id);
        showcaseService.saveShowcase(showcase, imageFile);

        ra.addFlashAttribute("success", "Showcase updated successfully!");
        return "redirect:/showcases";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes ra) {
        showcaseService.deleteById(id);
        ra.addFlashAttribute("success", "Showcase deleted successfully!");
        return "redirect:/showcases";
    }

    @GetMapping("/{id}")
    public String viewShowcaseDetails(@PathVariable long id, Model model, RedirectAttributes ra) {
        Showcase showcase = showcaseService.findById(id);

        if (showcase == null) {
            ra.addFlashAttribute("error", "Showcase not found");
            return "redirect:/showcases";
        }

        model.addAttribute("showcase", showcase);
        model.addAttribute("projects", projectService.findProjectsByShowcaseId(id));

        return "showcase-details";
    }

    @GetMapping("/search")
    public String searchShowcases(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("showcases", showcaseService.searchByName(keyword));
        return "showcase";
    }
}
