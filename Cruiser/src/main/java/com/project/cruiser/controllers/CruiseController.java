package com.project.cruiser.controllers;

import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.User;
import com.project.cruiser.services.BookingListService;
import com.project.cruiser.services.CruiseService;
import com.project.cruiser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class CruiseController {

    private final CruiseService cruiseService;
    private final UserService userService;
    private final BookingListService bookingListService;

    @Autowired
    public CruiseController(CruiseService cruiseService, UserService userService,
                            BookingListService bookingListService) {
        this.cruiseService = cruiseService;
        this.userService = userService;
        this.bookingListService = bookingListService;
    }

    @GetMapping("/list_of_cruises")
    public String findAll(Model model){
        List<Cruise> cruises = cruiseService.findAll();
        model.addAttribute("cruises", cruises);
        return "list_of_cruises";
    }

    @GetMapping("cruise_book/{id}")
    public String bookCruise(@PathVariable("id") Long id, Principal principal, Model model){
        User user = userService.findByName(principal.getName());
        Cruise cruise = cruiseService.findById(id);
        if (bookingListService.isCruiseAlreadyBookedByUser(user, cruise)){
            List<Cruise> cruises = cruiseService.findAll();
            model.addAttribute("cruises", cruises);
            model.addAttribute("alreadyBooked", true);
            return "list_of_cruises";
        }
        bookingListService.bookCruise(cruise, user);
        return "successfully_booked";
    }

    @GetMapping("/cruise_create")
    public String createCruiseForm(Cruise cruise){
        return "cruise_create";
    }

    @PostMapping("/cruise_create")
    public String createCruise(Cruise cruise){
        cruiseService.save(cruise);
        return "redirect:/list_of_cruises";
    }

    @GetMapping("cruise_delete/{id}")
    public String deleteCruise(@PathVariable("id") Long id){
        cruiseService.deleteById(id);
        return "redirect:/list_of_cruises";
    }

    @GetMapping("cruise_update/{id}")
    public String updateCruiseForm(@PathVariable("id") Long id, Model model) {
        Cruise cruise = cruiseService.findById(id);
        model.addAttribute("cruise", cruise);
        return "/cruise_update";
    }

    @PostMapping("/cruise_update")
    public String updateCruise(Cruise cruise) {
        cruiseService.save(cruise);
        return "redirect:/list_of_cruises";
    }

}
