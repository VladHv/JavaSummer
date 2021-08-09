package com.project.cruiser.controllers;

import com.project.cruiser.entity.User;
import com.project.cruiser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value={"/","/main"})
    public String getMainPage(Model model) {
        return "main";
    }

    @RequestMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @RequestMapping("/user_info")
    public String getUserInfo(Model model) {
        return "user_info";
    }

    @RequestMapping("/list_of_cruises")
    public String getCruiseInfo(Model model) {
        return "list_of_cruises";
    }

    @RequestMapping("/booking_list")
    public String getBookings(Model model) {
        return "booking_list";
    }
    @RequestMapping("/reg_form")
    public String getRegistration(Model model) {
        model.addAttribute("user", new User());
        return "reg_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "process_register";
    }
}
