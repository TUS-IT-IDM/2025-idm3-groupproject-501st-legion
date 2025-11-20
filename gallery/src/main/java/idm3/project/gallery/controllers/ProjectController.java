package idm3.project.gallery.controllers;

import idm3.project.gallery.model.Project;
import idm3.project.gallery.model.User;
import idm3.project.gallery.repository.UserRepository;
import idm3.project.gallery.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping(value = {"/project", "/project/"})


public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/allProject", ""})
    public ModelAndView displayAllProducts(
            HttpSession session,
            @CookieValue(value = "username", required = false) String username) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null && username != null) {
            User userFromCookie = userRepository.findByUserName(username);
            if (userFromCookie != null) {
                session.setAttribute("loggedInUser", userFromCookie);
                loggedInUser = userFromCookie;
            }
        }

        if (loggedInUser == null) {
            return new ModelAndView("redirect:/login");
        }
        List<Project> userProjects = projectService.findByUser(loggedInUser);

        return new ModelAndView("/project", "projects", userProjects);
    }
    @GetMapping("/view/{id}")
    public ModelAndView viewProject(@PathVariable("id") long id) {
        if (projectService.findOne(id).isEmpty())
            return new ModelAndView("/error", "error", "Project not found");
        else
            return new ModelAndView("viewProject", "project", projectService.findOne(id).get());
    }

    @GetMapping("/add")
    public ModelAndView showAddProductForm() {
        return new ModelAndView("/addProject", "newProject", new Project());
    }
//    @PostMapping("/addProject")
//    public ModelAndView addABook(@ModelAttribute("newProject") Project x, BindingResult result) {
//        projectService.saveProject(x);
//        return new ModelAndView("redirect:/project");
//    }

    @PostMapping("/delete")
    public ModelAndView deleteProject(@RequestParam("projectId") long id) {
        if (projectService.findOne(id).isEmpty()) {
            return new ModelAndView("/error", "error", "Project not found");
        } else {
            projectService.deleteByID(id);
            return new ModelAndView("redirect:/project/");
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditProjectForm(@PathVariable("id") long id) {
        if (projectService.findOne(id).isEmpty())
            return new ModelAndView("/error", "error", "Project not found");
        else
            return new ModelAndView("/editProject", "aProject", projectService.findOne(id).get());
    }

    @PostMapping("/saveProject")
    public ModelAndView saveOrUpdateProject(
            @ModelAttribute("aProject") Project p,
            @RequestParam(value = "projectHeroImageFile", required = false) MultipartFile file,
            BindingResult result,
            HttpSession session,
            @CookieValue(value = "username", required = false) String username) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null && username != null) {
            User userFromCookie = userRepository.findByUserName(username);
            if (userFromCookie != null) {
                session.setAttribute("loggedInUser", userFromCookie);
                loggedInUser = userFromCookie;
            }
        }

        if (loggedInUser == null) {
            return new ModelAndView("redirect:/login");
        }

        p.setUser(loggedInUser);


        if (result.hasErrors()) {
            String viewName = (p.getProjectId() == null) ? "/addProject" : "/editProject";
            return new ModelAndView(viewName);
        }

        try {
            if (file != null && !file.isEmpty()) {
                projectService.saveProjectWithImage(p, file);
            } else {
                projectService.updateProject(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ModelAndView("/error", "error", "Image upload failed");
        }

        return new ModelAndView("redirect:/project");
    }


    @PostMapping("/addProject")
    public ModelAndView addProject(@ModelAttribute("newProject") @Valid Project project,
                                   BindingResult result,
                                   @RequestParam(value = "projectHeroImageFile", required = false) MultipartFile file,
                                   HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return new ModelAndView("redirect:/login");
        }
        project.setUser(loggedInUser);


        if (result.hasErrors()) {
            return new ModelAndView("/addProject", "newProject", project)
                    .addObject("errors", result.getAllErrors());
        } else {

            try {
                projectService.saveProjectWithImage(project, file);
            } catch (IOException e) {
                e.printStackTrace();
                return new ModelAndView("/error", "error", "Image upload failed");
            }

            return new ModelAndView("redirect:/project/allProject");
        }
    }
    @GetMapping("/search")
    public ModelAndView searchProducts(@RequestParam(value = "keyword", required = false) String keyword) {
        List<Project> searchResults = projectService.searchProject(keyword);
        return new ModelAndView("/project", "projects", searchResults);
    }


}