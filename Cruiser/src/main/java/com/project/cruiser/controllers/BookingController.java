package com.project.cruiser.controllers;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.BookingStatus;
import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.User;
import com.project.cruiser.services.BookingListService;
import com.project.cruiser.services.CruiseService;
import com.project.cruiser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public String bookingListByPage(Model model,
                                    @PathVariable("pageNumber") int currentPage,
                                    @Param("sortField") String sortField,
                                    @Param("sortDir") String sortDir){

        //todo filter for status.new
        BookingStatus keyword = null;

        Page<BookingList> page = bookingListService.findAll(currentPage, sortField, sortDir, keyword);

        List<BookingList> bookingList = page.getContent();

        int totalPages = page.getTotalPages();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("bookingList", bookingList);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverseSortDir);
        return "booking_list";
    }

    @GetMapping("/booking_list")
    public String bookingList(Model model){
        int firstPage = 1;
        return bookingListByPage(model, firstPage, "id", "asc");
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
