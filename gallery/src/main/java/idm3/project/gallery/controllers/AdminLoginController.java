package idm3.project.gallery.controllers;

import idm3.project.gallery.model.User;
import idm3.project.gallery.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminLoginController {

    private final UserRepository userRepository;

    public AdminLoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/adminlogin")
    public String adminLoginPage() {
        return "adminlogin";
    }

    @PostMapping("/adminlogin")
    public String validateAdmin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User admin = userRepository.findByUserNameAndPassword(username, password);

        if (admin == null || !"Admin".equalsIgnoreCase(admin.getUserType())) {
            model.addAttribute("error", "Invalid admin credentials.");
            return "adminlogin";
        }

        session.setAttribute("adminUser", admin);
        return "redirect:/showcases";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        User admin = (User) session.getAttribute("adminUser");

        session.invalidate();

        if (admin != null) {
            return "redirect:/adminlogin";
        }

        return "redirect:/login";
    }
}
