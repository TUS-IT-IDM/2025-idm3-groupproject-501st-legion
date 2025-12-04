package idm3.project.gallery.controllers;

import idm3.project.gallery.model.User;
import idm3.project.gallery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        boolean success = userService.registerUser(user);

        if (success) {
            model.addAttribute("success", "Registration successful! You can now log in.");
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Username or email already exists. Please try again.");
            return "register";
        }
    }
}
