package com.project.cruiser.controllers;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.User;
import com.project.cruiser.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

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
    public String processRegister(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (userService.isUserAlreadyExist(user)) {
            model.addAttribute("userExist", true);
            return "reg_form";
        }
        else if(bindingResult.hasErrors())
            return "reg_form";

        userService.save(user);
        return "process_register";
    }

    @GetMapping("/user_info")
    public String getUserInfo(Model model, Principal principal) {
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        Set<BookingList> bookingList = userService.getBookingList(principal.getName());
        model.addAttribute("bookingList", bookingList);
        Integer money = 0;
        model.addAttribute("money", money);
        return "user_info";
    }


    @PostMapping("/add_money")
    public String addMoney(@RequestParam("money") Integer money, Principal principal) {
        User user = userService.findByName(principal.getName());
        if(money <= 0){

        }
        userService.addMoney(user, money);
        return "redirect:/user_info";
    }

//    @GetMapping("booking_pay/{id}")
//    public String payBookingById(@PathVariable("id") Long id) {
//        userService.payBookingById(id);
//        return "redirect:/user_info";
//    }

}
