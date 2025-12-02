package idm3.project.gallery.controllers;

import idm3.project.gallery.model.Project;
import idm3.project.gallery.model.User;
import idm3.project.gallery.service.ProjectService;
import idm3.project.gallery.service.ShowcaseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final ShowcaseService showcaseService;

    public ProjectController(ProjectService projectService, ShowcaseService showcaseService) {
        this.projectService = projectService;
        this.showcaseService = showcaseService;
    }

    @GetMapping
    public String listProjects(HttpSession session, Model model) {
        User user = (User) session.getAttribute("studentUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("projects", projectService.findByUser(user));
        return "Project";
    }

    @GetMapping("/add")
    public String showAddForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("studentUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("project", new Project());
        model.addAttribute("showcases", showcaseService.findAll());
        return "addProject";
    }

    @PostMapping("/add")
    public String addProject(@ModelAttribute Project project,
                             @RequestParam("showcaseId") Long showcaseId,
                             @RequestParam("projectHeroImageFile") MultipartFile file,
                             HttpSession session) {
        try {
            User user = (User) session.getAttribute("studentUser");
            if (user == null) {
                return "redirect:/login";
            }
            projectService.addProject(project, file, showcaseId, user);
        } catch (Exception ignored) {}
        return "redirect:/project";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("studentUser");
        if (user == null) {
            return "redirect:/login";
        }
        Project project = projectService.findOne(id);
        if (project.getUser().getUserId() != user.getUserId()) {
            return "redirect:/project";
        }
        model.addAttribute("project", project);
        model.addAttribute("showcases", showcaseService.findAll());
        return "editProject";
    }

    @PostMapping("/edit")
    public String editProject(@ModelAttribute Project project,
                              @RequestParam("showcaseId") Long showcaseId,
                              @RequestParam("projectHeroImageFile") MultipartFile file,
                              HttpSession session) {
        try {
            User user = (User) session.getAttribute("studentUser");
            if (user == null) {
                return "redirect:/login";
            }
            projectService.updateProject(project, file, showcaseId, user);
        } catch (Exception ignored) {}
        return "redirect:/project";
    }

    @PostMapping("/delete")
    public String deleteProject(@RequestParam Long id, HttpSession session) {
        User user = (User) session.getAttribute("studentUser");
        if (user == null) {
            return "redirect:/login";
        }
        Project project = projectService.findOne(id);
        if (project.getUser().getUserId() != user.getUserId()) {
            return "redirect:/project";
        }
        projectService.delete(id);
        return "redirect:/project";
    }

    @GetMapping("/view/{id}")
    public String viewProject(@PathVariable Long id, HttpSession session, Model model) {

        Project project = projectService.findOne(id);
        model.addAttribute("project", project);
        return "viewProject";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, HttpSession session, Model model) {
        User user = (User) session.getAttribute("studentUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("projects", projectService.searchByName(keyword, user));
        return "Project";
    }
}
