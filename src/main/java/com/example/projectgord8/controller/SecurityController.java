package com.example.projectgord8.controller;

import com.example.projectgord8.entity.User;
import com.example.projectgord8.service.UserActionService;
import com.example.projectgord8.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SecurityController {
    private final UserService userService;
    @Autowired
    private UserActionService userActionService;

    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home() {
        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        userActionService.saveAction(authInfo.getName(), "index");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {

        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        userActionService.saveAction(authInfo.getName(), "try to register with " + user.getUsername() + " username");

        User existingUser = userService.findByUsername(user.getUsername());

        if (existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()) {
            result.rejectValue("username", null,
                    "Данный логин уже занят");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        userActionService.saveAction(authInfo.getName(), "users");

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
