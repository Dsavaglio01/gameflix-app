package com.example.gameflix.service;

import org.springframework.stereotype.Service;

public interface UserService {
    boolean registerUser(String username, String rawPassword);
    boolean loginUser(String username, String rawPassword);
}
