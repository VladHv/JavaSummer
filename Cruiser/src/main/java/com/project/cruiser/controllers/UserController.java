package com.project.cruiser.controllers;

import com.project.cruiser.entity.RoleType;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.UserRepository;
import com.project.cruiser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reg_form")
    public String createAccount(Model model) {
        model.addAttribute("user", new User());
        return "reg_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        userService.save(user);
        return "process_register";
    }

    @GetMapping("/user_info")
    public String getUserInfo(Model model, Principal principal) {
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        return "user_info";
    }

}
