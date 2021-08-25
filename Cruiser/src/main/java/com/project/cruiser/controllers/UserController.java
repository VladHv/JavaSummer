package com.project.cruiser.controllers;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.User;
import com.project.cruiser.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
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
    public String getUserInfo(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByName(currentUser.getUsername());
        model.addAttribute("user", user);
        Set<BookingList> bookingList = userService.getUserBookingList(currentUser.getUsername());
        model.addAttribute("bookingList", bookingList);
        return "user_info";
    }

    @PostMapping("/successful_replenish")
    public String addMoney(@ModelAttribute("money") Integer money, Model model,
                           @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByName(currentUser.getUsername());
        if(money <= 0){
            model.addAttribute("user", user);
            Set<BookingList> bookingList = userService.getUserBookingList(currentUser.getUsername());
            model.addAttribute("bookingList", bookingList);
            model.addAttribute("wrongAmount", true);
            return "user_info";
        }

        userService.addMoney(user, money);
        return "successful_replenish";
    }

    @GetMapping("booking_pay/{id}")
    public String payBookingById(@PathVariable("id") Long id, Model model,
                                 @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByName(currentUser.getUsername());
        final Integer fixedMoneyAmount = user.getMoneyAmount();
        try {
            userService.payBooking(id, user);
        } catch (Exception e) {
            user.setMoneyAmount(fixedMoneyAmount);
            model.addAttribute("user", user);
            Set<BookingList> bookingList = userService.getUserBookingList(currentUser.getUsername());
            model.addAttribute("bookingList", bookingList);
            model.addAttribute("notEnoughMoney", true);
            return "user_info";
        }

        return "successfully_paid";
    }

}
