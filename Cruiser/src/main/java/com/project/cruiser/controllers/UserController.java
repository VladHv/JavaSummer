package com.project.cruiser.controllers;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.User;
import com.project.cruiser.services.BookingListService;
import com.project.cruiser.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@Controller
@Slf4j
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
    public String processRegister(@ModelAttribute("user") @Valid User user,
                                  BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "reg_form";
        }

        boolean isUserSaved = userService.save(user);
        if(!isUserSaved){
            log.warn("Guest try to register new account with already exist email {}", user.getEmail());
            model.addAttribute("userExist", true);
            return "reg_form";
        }

        log.info("New user {} registrated", user.getEmail());
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
    public String addMoney(@ModelAttribute("money") Integer money,
                           @AuthenticationPrincipal UserDetails currentUser,
                           RedirectAttributes redirectAttributes) {
        User user = userService.findByName(currentUser.getUsername());
        if(money <= 0){
            redirectAttributes.addFlashAttribute("wrongAmount", true);
            return "redirect:user_info";
        }
        redirectAttributes.addFlashAttribute("success", true);
        userService.addMoney(user, money);
        log.info("User {} topped up the account for {}", user, money);
        return "redirect:user_info";
    }

    @GetMapping("booking_pay/{id}")
    public String payBookingById(@PathVariable("id") Long id,
                                 @AuthenticationPrincipal UserDetails currentUser,
                                 RedirectAttributes redirectAttributes) {
        User user = userService.findByName(currentUser.getUsername());
        try {
            userService.payBooking(id, user);
        } catch (Exception e) {
            log.warn("User {} have not enough money ({}) on account to pay booking #{}",
                                    user, user.getMoneyAmount(), id);
            redirectAttributes.addFlashAttribute("notEnoughMoney", true);
            return "redirect:/user_info";
        }
        redirectAttributes.addFlashAttribute("bookingPaid", true);
        return "redirect:/user_info";
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
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("noDocs", true);
            return "redirect:documents";
        }
        if ( !file.isEmpty() && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String fileName = file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + fileName));
            User user = userService.findByName(currentUser.getUsername());
            userService.addFileName(fileName, user);
            redirectAttributes.addFlashAttribute("docUploaded", true);
        }

        return "redirect:documents";
    }
}
