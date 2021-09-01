package com.project.cruiser.services;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.BookingStatus;
import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.BookingListRepository;
import com.project.cruiser.repository.CruiseRepository;
import com.project.cruiser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookingListService {

    private final BookingListRepository bookingListRepository;
    private final CruiseRepository cruiseRepository;
    private final UserRepository userRepository;


    @Autowired
    public BookingListService(BookingListRepository bookingListRepository, CruiseRepository cruiseRepository,
                              UserRepository userRepository) {
        this.bookingListRepository = bookingListRepository;
        this.cruiseRepository = cruiseRepository;
        this.userRepository = userRepository;
    }

    public BookingList findById(Long id){
        return Optional.of(bookingListRepository.findById(id))
                .get()
                .orElseThrow(RuntimeException::new);
    }

    public Page<BookingList> findAll(int pageNumber, String sortField, String sortDir, BookingStatus keyword) {
        int pageSize = 4;
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        updateStatusWhenCruisesFinished();

        return keyword != null ? Optional.of(bookingListRepository.findAll(keyword, pageable))
                .orElseThrow(RuntimeException::new) : Optional.of(bookingListRepository.findAll(pageable))
                .orElseThrow(RuntimeException::new);
    }

    public Set<BookingList> getUserBookingList(String email){
        updateStatusWhenCruisesFinished();
        return userRepository.findByEmail(email).getBookingLists();
    }

    public BookingList save(BookingList bookingList) {
        return bookingListRepository.save(bookingList);
    }

    public void deleteById(Long id) {
        bookingListRepository.deleteById(id);
    }

    public boolean isCruiseAlreadyBookedByUser(User user, Cruise cruise){
        return bookingListRepository.findAll()
                .stream()
                .filter(x -> x.getCruise().equals(cruise))
                .anyMatch(x -> x.getUser().equals(user));
    }


    public BookingList bookCruise(Cruise cruise, User user) {
        Cruise updatedCruise = cruiseRepository.findById(cruise.getId()).get();
        updatedCruise.setFreePlaces(updatedCruise.getFreePlaces() - 1);
        return bookingListRepository.save(
                BookingList.builder()
                        .cruise(updatedCruise)
                        .user(user)
                        .status(BookingStatus.NEW)
                .build());
    }

    @Transactional
    public BookingList confirmBookById(Long id) {
        BookingList booking = Optional.of(bookingListRepository.findById(id)).get()
                .orElseThrow(RuntimeException::new);
        booking.setStatus(BookingStatus.CONFIRMED);
        return bookingListRepository.save(booking);
    }

    @Transactional
    public BookingList rejectBookById(Long id) {
        BookingList booking = Optional.of(bookingListRepository.findById(id)).get()
                .orElseThrow(RuntimeException::new);
        booking.setStatus(BookingStatus.REJECTED);
        booking.getCruise().setFreePlaces(booking.getCruise().getFreePlaces() + 1);
        return bookingListRepository.save(booking);
    }

    @Transactional
    public List<BookingList> updateStatusWhenCruisesFinished() {
        List<BookingList> updatedBookingList = bookingListRepository.findAll();
        updatedBookingList.forEach(booking -> {
            boolean isCruiseFinished = booking.getCruise().getEnd().isBefore(LocalDateTime.now());
            if (isCruiseFinished) {
                booking.setStatus(BookingStatus.FINISHED);
                bookingListRepository.save(booking);
            }
        });
        return updatedBookingList;
    }

}
