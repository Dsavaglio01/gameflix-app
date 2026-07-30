package com.example.gameflix.service;

import com.example.gameflix.model.User;
import com.example.gameflix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public boolean registerUser(String username, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false; // to prevent duplicate usernames
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        userRepository.save(user);
        return true;
    }
    @Override
    public boolean loginUser(String username, String rawPassword) {
        Optional<User> currentUser = userRepository.findByUsername(username);
        if (currentUser.isEmpty()) {
            return false; // user not found
        }
        return passwordEncoder.matches(rawPassword, currentUser.get().getPassword());
    }
}
