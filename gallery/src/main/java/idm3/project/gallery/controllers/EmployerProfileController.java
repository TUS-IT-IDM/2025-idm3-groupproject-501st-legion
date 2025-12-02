package idm3.project.gallery.controllers;

import idm3.project.gallery.model.User;
import idm3.project.gallery.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employer/profile")
public class EmployerProfileController {

    private final UserService userService;

    public EmployerProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String viewProfile(HttpSession session, Model model) {

        User employer = (User) session.getAttribute("employerUser");

        if (employer == null) {
            return "redirect:/login";
        }

        model.addAttribute("employer", employer);

        return "employer-profile";
    }

    @GetMapping("/edit")
    public String editProfile(HttpSession session, Model model) {

        User employer = (User) session.getAttribute("employerUser");

        if (employer == null) {
            return "redirect:/login";
        }

        model.addAttribute("employer", employer);

        return "employer-profile-edit";
    }

    @PostMapping("/update")
    public String updateProfile(
            @ModelAttribute("employer") User updatedEmployer,
            HttpSession session
    ) {
        User employer = (User) session.getAttribute("employerUser");

        if (employer == null) {
            return "redirect:/login";
        }

        employer.setFirstName(updatedEmployer.getFirstName());
        employer.setSurname(updatedEmployer.getSurname());
        employer.setEmailAddress(updatedEmployer.getEmailAddress());
        employer.setOrganization(updatedEmployer.getOrganization());

        userService.updateUser(employer);

        session.setAttribute("employerUser", employer);

        return "redirect:/employer/profile";
    }
}
