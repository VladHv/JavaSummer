package com.project.cruiser.controllers;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.User;
import com.project.cruiser.services.BookingListService;
import com.project.cruiser.services.CruiseService;
import com.project.cruiser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class BookingController {

    private final BookingListService bookingListService;
    private final CruiseService cruiseService;
    private final UserService userService;

    @Autowired
    public BookingController(BookingListService bookingListService, CruiseService cruiseService, UserService userService) {
        this.bookingListService = bookingListService;
        this.cruiseService = cruiseService;
        this.userService = userService;
    }

    @GetMapping("cruise_book/{id}")
    public String bookCruise(@PathVariable("id") Long id, Principal principal){

        User user = userService.findByName(principal.getName());
        Cruise cruise = cruiseService.findById(id);
        bookingListService.bookCruise(cruise, user);
        return "redirect:/list_of_cruises";
    }

    @GetMapping("/booking_list/page/{pageNumber}")
    public String bookingListByPage(Model model, @PathVariable("pageNumber") int currentPage){

        Page<BookingList> page = bookingListService.findAll(currentPage);
        int totalPages = page.getTotalPages();
        List<BookingList> bookingList = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("bookingList", bookingList);
        return "booking_list";
    }

    @GetMapping("/booking_list")
    public String bookingList(Model model){
        int firstPage = 1;
        return bookingListByPage(model, firstPage);
    }

    @GetMapping("booking_confirm/{id}")
    public String confirmBook(@PathVariable("id") Long id) {
        bookingListService.confirmBookById(id);
        return "redirect:/booking_list";
    }

    @GetMapping("booking_reject/{id}")
    public String rejectBook(@PathVariable("id") Long id) {
        bookingListService.rejectBookById(id);
        return "redirect:/booking_list";
    }

}
