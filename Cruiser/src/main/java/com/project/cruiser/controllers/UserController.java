package com.project.cruiser.controllers;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.User;
import com.project.cruiser.services.BookingListService;
import com.project.cruiser.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@Controller
public class UserController {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserService userService;
    private final BookingListService bookingListService;

    @Autowired
    public UserController(UserService userService, BookingListService bookingListService) {
        this.userService = userService;
        this.bookingListService = bookingListService;
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
        Set<BookingList> bookingList = bookingListService.getUserBookingList(currentUser.getUsername());
        model.addAttribute("bookingList", bookingList);
        return "user_info";
    }

    @PostMapping("/successful_replenish")
    public String addMoney(@ModelAttribute("money") Integer money, Model model,
                           @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByName(currentUser.getUsername());
        if(money <= 0){
            model.addAttribute("user", user);
            Set<BookingList> bookingList = bookingListService.getUserBookingList(currentUser.getUsername());
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
            Set<BookingList> bookingList = bookingListService.getUserBookingList(currentUser.getUsername());
            model.addAttribute("bookingList", bookingList);
            model.addAttribute("notEnoughMoney", true);
            return "user_info";
        }

        return "successfully_paid";
    }

    @GetMapping("documents")
    public String documentMenu(@AuthenticationPrincipal UserDetails currentUser,
                               Model model){
        User user = userService.findByName(currentUser.getUsername());
        model.addAttribute("user", user);
        return "documents";
    }

    @PostMapping("doc_upload")
    public String documentSave(@RequestParam("file") MultipartFile file,
                               @AuthenticationPrincipal UserDetails currentUser,
                               RedirectAttributes redirectAttributes) throws IOException {

        if (file !=null){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }


            String fileName = file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + fileName));
            User user = userService.findByName(currentUser.getUsername());
            userService.addFileName(fileName, user);
        }


        return "redirect:documents";
    }
}
