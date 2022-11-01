package ru.korobko.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.korobko.warehouse.form.RegistrationForm;
import ru.korobko.warehouse.model.User;
import ru.korobko.warehouse.model.auth.Role;
import ru.korobko.warehouse.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    public String usersTable(Model model) {
        List<User> userList = userService.index();
        userList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        model.addAttribute("userList", userList);
        return "users/index";
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.insert(user);
    }

    @GetMapping("/register")
    private String showRegisterForm(
            @ModelAttribute("form")
                    RegistrationForm form) {
        return "users/register";
    }
    @PostMapping("/register")
    public String handleRegister(
            @ModelAttribute("form")
            @Valid
                    RegistrationForm form,
            BindingResult result
    ) {
        if (!form.getPassword().equals(form.getPasswordConfirmation())) {
            result.addError(new FieldError("form", "passwordConfirmation",
                    "Пароли не совпадают"));
        }

        if (result.hasErrors()) {
            return "users/register";
        }

        String encryptedPassword = encoder.encode(form.getPassword());

        try {
            userService.insert(new User(form.getUsername(), encryptedPassword, Role.USER));
        }
        catch (Exception cause) {
            result.addError(new FieldError(
                    "form",
                    "username",
                    "Пользователь с таким логином уже существует"
            ));
        }

        if (result.hasErrors()) {
            return "users/register";
        }

        return "redirect:/main";
    }
}
