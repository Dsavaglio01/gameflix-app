package com.example.gameflix.controller;

import com.example.gameflix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

        @Autowired
        private UserService userService;
    
        @RequestMapping(value = "/register", method = RequestMethod.POST)
        public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
           String username = request.get("username");
           String password = request.get("password");
           boolean success = userService.registerUser(username, password);
           if (!success) {
               return ResponseEntity.status(400).body(Map.of("message", "Username already exists"));
           }
           return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        }

        @RequestMapping(value = "/login", method = RequestMethod.POST)
        public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {
            String username = request.get("username");
            String password = request.get("password");
            boolean success = userService.loginUser(username, password);
            if (!success) {
                return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
            }
            return ResponseEntity.ok(Map.of("message", "Login successful"));
        }
}
