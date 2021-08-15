package com.project.cruiser.services;

import com.project.cruiser.entity.RoleType;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findByName(String name) {
        return userRepository.findByEmail(name);
    }

}
