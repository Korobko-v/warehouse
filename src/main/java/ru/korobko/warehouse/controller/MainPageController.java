package ru.korobko.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korobko.warehouse.model.auth.Role;
import ru.korobko.warehouse.service.UserService;

@Controller
@RequestMapping("/main")
public class MainPageController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String main(Model model, Authentication authentication) {
        boolean loggedIn = authentication != null && authentication.isAuthenticated();
        String username = "";
        boolean isAdmin = false;

        if (loggedIn) {
            username = authentication.getName();
            isAdmin = userService.findByUsername(username).getRole().equals(Role.ADMIN);
        }

        model.addAttribute("login", username);
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("isAdmin", isAdmin);

        return "main";
    }
}
