package idm3.project.gallery.controllers;

import idm3.project.gallery.model.Project;
import idm3.project.gallery.model.ProjectNote;
import idm3.project.gallery.service.EmployerProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    private final EmployerProjectService employerService;

    public EmployerController(EmployerProjectService employerService) {
        this.employerService = employerService;
    }

    @GetMapping
    public ModelAndView dashboard(HttpSession session) {

        Object userObj = session.getAttribute("employerUser");
        if (userObj == null) {
            return new ModelAndView("redirect:/login");
        }

        Long employerId = ((idm3.project.gallery.model.User) userObj).getUserId();

        List<Project> allProjects = employerService.findAllProjects();
        List<ProjectNote> savedProjects = employerService.getSavedProjects(employerId);

        ModelAndView mv = new ModelAndView("employer");
        mv.addObject("projects", allProjects);
        mv.addObject("savedProjects", savedProjects);

        return mv;
    }

    @PostMapping("/save")
    public String save(@RequestParam Long projectId, HttpSession session) {
        Long employerId = ((idm3.project.gallery.model.User) session.getAttribute("employerUser")).getUserId();
        employerService.saveProject(projectId, employerId);
        return "redirect:/employer";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam Long projectId, HttpSession session) {
        Long employerId = ((idm3.project.gallery.model.User) session.getAttribute("employerUser")).getUserId();
        employerService.removeSavedProject(projectId, employerId);
        return "redirect:/employer";
    }

    @PostMapping("/note")
    public String addNote(@RequestParam Long projectId,
                          @RequestParam String noteText,
                          HttpSession session) {

        Long employerId = ((idm3.project.gallery.model.User) session.getAttribute("employerUser")).getUserId();
        employerService.addNote(projectId, employerId, noteText);

        return "redirect:/employer";
    }

    @GetMapping("/notes")
    public ModelAndView allNotes(HttpSession session) {

        Long employerId = ((idm3.project.gallery.model.User) session.getAttribute("employerUser")).getUserId();

        List<ProjectNote> notes = employerService.getAllNotesForEmployer(employerId);

        ModelAndView mv = new ModelAndView("notes");
        mv.addObject("notes", notes);

        return mv;
    }
}
