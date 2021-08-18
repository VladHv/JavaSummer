package com.project.cruiser.services;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.BookingStatus;
import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.BookingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingListService {

    private final BookingListRepository bookingListRepository;

    @Autowired
    public BookingListService(BookingListRepository bookingListRepository) {
        this.bookingListRepository = bookingListRepository;
    }

    public BookingList findById(Long id){
        return Optional.of(bookingListRepository.findById(id))
                .get()
                .orElseThrow(RuntimeException::new);
    }

    public List<BookingList> findAll() {
        return Optional.of(bookingListRepository.findAll())
                .orElseThrow(RuntimeException::new);
    }

    public BookingList save(BookingList bookingList) {
        return bookingListRepository.save(bookingList);
    }

    public void deleteById(Long id) {
        bookingListRepository.deleteById(id);
    }


    public void bookCruise(Cruise cruise, User user) {
        BookingList booking = new BookingList();
        booking.setCruise(cruise);
        booking.setUser(user);
        booking.setStatus(BookingStatus.NEW);
        bookingListRepository.save(booking);
    }

    public void confirmBookById(Long id) {
        BookingList booking = Optional.of(bookingListRepository.findById(id)).get()
                .orElseThrow(RuntimeException::new);
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingListRepository.save(booking);
    }

    public void rejectBookById(Long id) {
        BookingList booking = Optional.of(bookingListRepository.findById(id)).get()
                .orElseThrow(RuntimeException::new);
        booking.setStatus(BookingStatus.REJECTED);
        bookingListRepository.save(booking);
    }
}