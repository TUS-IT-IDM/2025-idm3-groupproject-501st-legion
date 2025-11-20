package idm3.project.gallery.controllers;

import idm3.project.gallery.model.User;
import idm3.project.gallery.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String validateLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userRepository.findByUserNameAndPassword(username, password);

        if (user == null) {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }

        if ("Admin".equalsIgnoreCase(user.getUserType())) {
            model.addAttribute("error", "Admins must sign in using the Admin Login page.");
            return "login";
        }

        session.setAttribute("loggedInUser", user);
        return "redirect:/MainGallery";
    }
}
