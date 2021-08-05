package com.project.cruiser.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "reg_form";
    }
}
