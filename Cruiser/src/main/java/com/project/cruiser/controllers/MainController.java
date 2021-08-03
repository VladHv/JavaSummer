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

}
