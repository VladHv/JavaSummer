package com.project.cruiser.services;

import com.project.cruiser.entity.BookingList;
import com.project.cruiser.entity.BookingStatus;
import com.project.cruiser.entity.RoleType;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.BookingListRepository;
import com.project.cruiser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookingListRepository bookingListRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookingListRepository bookingListRepository) {
        this.userRepository = userRepository;
        this.bookingListRepository = bookingListRepository;
    }

    public User findById(Long id){
        return Optional.of(userRepository.findById(id))
                .get()
                .orElseThrow(RuntimeException::new);
    }

    public List<User> findAll() {
        return Optional.of(userRepository.findAll())
                .orElseThrow(RuntimeException::new);
    }

    public void save(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleType.USER);
        user.setMoneyAmount(0);
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findByName(String name) {
        return userRepository.findByEmail(name);
    }

    public Set<BookingList> getUserBookingList(String email){
        return userRepository.findByEmail(email).getBookingLists();
    }

    @Transactional
    public void addMoney(User user, Integer money) {
        User updatedUser = userRepository.findByEmail(user.getEmail());
        Integer updatedMoneyAmount = updatedUser.getMoneyAmount() + money;
        user.setMoneyAmount(updatedMoneyAmount);
        userRepository.save(user);
    }

    public boolean isUserAlreadyExist(User user) {
        return userRepository.findByEmail(user.getEmail()) != null;
    }


    @Transactional
    public void payBooking(Long id, User user){
        User updatedUser = userRepository.findByEmail(user.getEmail());
        BookingList booking = bookingListRepository.findById(id).get();
        updatedUser.setMoneyAmount(updatedUser.getMoneyAmount()
                - booking.getCruise().getPrice());
        booking.setStatus(BookingStatus.PAID);
        userRepository.save(updatedUser);
        bookingListRepository.save(booking);

    }
}
