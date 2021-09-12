package com.project.cruiser.controllers;

import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.User;
import com.project.cruiser.services.BookingListService;
import com.project.cruiser.services.CruiseService;
import com.project.cruiser.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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
    public String findAll(@RequestParam(value = "cruiseStart", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                          @RequestParam(value = "cruiseEnd", required = false)
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                          Model model){
        List<Cruise> cruises = cruiseService.findAll();
        if(start != null || end != null) {
            cruises = cruiseService.findAll(start, end);
        }
        model.addAttribute("cruiseStart", start);
        model.addAttribute("cruiseEnd", end);
        model.addAttribute("cruises", cruises);
        return "list_of_cruises";
    }

    @GetMapping("cruise_book/{id}")
    public String bookCruise(@PathVariable("id") Long id, Principal principal,
                             RedirectAttributes redirectAttributes){
        User user = userService.findByName(principal.getName());
        Cruise cruise = cruiseService.findById(id);
        log.info("User {} try to book cruise {}", user, cruise);
        if (bookingListService.isCruiseAlreadyBookedByUser(user, cruise)){
            log.warn("Cruise {} already booked by user {}", cruise, user);
            redirectAttributes.addFlashAttribute("alreadyBooked", true);
            return "redirect:/list_of_cruises";
        }
        if( !cruiseService.hasFreePlace(cruise) ){
            log.warn("Cruise {} has no free places", cruise);
            redirectAttributes.addFlashAttribute("noFreePlace", true);
            return "redirect:/list_of_cruises";
        }
        bookingListService.bookCruise(cruise, user);
        redirectAttributes.addFlashAttribute("successfullyBooked", true);
        return "redirect:/list_of_cruises";
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
        log.info("Admin deleted cruise # {}", id);
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
        cruiseService.update(cruise);
        log.info("Admin update cruise # {}", cruise.getId());
        return "redirect:/list_of_cruises";
    }

}
