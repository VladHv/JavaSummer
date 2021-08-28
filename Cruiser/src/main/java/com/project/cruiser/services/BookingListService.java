package com.project.cruiser.services;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.BookingStatus;
import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.BookingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<BookingList> findAll(int pageNumber, String sortField, String sortDir, BookingStatus keyword) {
        int pageSize = 5;
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        if(keyword != null){
            return Optional.of(bookingListRepository.findAll(keyword, pageable))
                    .orElseThrow(RuntimeException::new);
        }
        return Optional.of(bookingListRepository.findAll(pageable))
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

    @Transactional
    public void confirmBookById(Long id) {
        BookingList booking = Optional.of(bookingListRepository.findById(id)).get()
                .orElseThrow(RuntimeException::new);
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingListRepository.save(booking);
    }

    @Transactional
    public void rejectBookById(Long id) {
        BookingList booking = Optional.of(bookingListRepository.findById(id)).get()
                .orElseThrow(RuntimeException::new);
        booking.setStatus(BookingStatus.REJECTED);
        bookingListRepository.save(booking);
    }
}
