package com.project.cruiser.controllers;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.BookingStatus;
import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.User;
import com.project.cruiser.services.BookingListService;
import com.project.cruiser.services.CruiseService;
import com.project.cruiser.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class BookingController {

    private final BookingListService bookingListService;

    @Autowired
    public BookingController(BookingListService bookingListService) {
        this.bookingListService = bookingListService;
    }

    @GetMapping("/booking_list/page/{pageNumber}")
    public String bookingListByPage(Model model,
                                    @PathVariable("pageNumber") int currentPage,
                                    @Param("sortField") String sortField,
                                    @Param("sortDir") String sortDir,
                                    @Param("filter") String filter){

        BookingStatus keyword = null;
        if(filter.equals("new")){
            keyword = BookingStatus.NEW;
        }

        Page<BookingList> page = bookingListService.findAll(currentPage, sortField, sortDir, keyword);

        List<BookingList> bookingList = page.getContent();

        int totalPages = page.getTotalPages();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("bookingList", bookingList);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("filter", filter);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir", reverseSortDir);
        return "booking_list";
    }

    @GetMapping("/booking_list")
    public String bookingList(Model model){
        int firstPage = 1;
        String filter = "false";
        return bookingListByPage(model, firstPage, "id", "asc", filter);
    }

    @GetMapping("/booking_list_filtered")
    public String bookingListNewOnly(Model model) {
        int firstPage = 1;
        String filter = "new";
        return bookingListByPage(model, firstPage, "id", "asc", filter);
    }

    @GetMapping("booking_confirm/{id}")
    public String confirmBook(@PathVariable("id") Long id) {
        bookingListService.confirmBookById(id);
        log.info("Admin confirm booking # {}", id);
        return "redirect:/booking_list";
    }

    @GetMapping("booking_reject/{id}")
    public String rejectBook(@PathVariable("id") Long id) {
        bookingListService.rejectBookById(id);
        log.info("Admin reject booking # {}", id);
        return "redirect:/booking_list";
    }

}
