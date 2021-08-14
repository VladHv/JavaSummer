package com.project.cruiser.controllers;

import com.project.cruiser.entity.RoleType;
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

    @RequestMapping("/booking_list")
    public String getBookings(Model model) {
        return "booking_list";
    }

}
