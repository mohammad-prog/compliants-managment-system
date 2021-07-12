package com.example.complaintssystem.controller;

import com.example.complaintssystem.User;
import com.example.complaintssystem.repository.UserRepository;
import com.example.complaintssystem.repository.jdbcUserRepository;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/Api/v1/user")
public class UserController {
    private UserRepository userRepository = new jdbcUserRepository();

    @PostMapping("/login")
    public boolean login (@RequestBody User user){
    return userRepository.login(user);
    }

    @PostMapping("/registration")
    public void registration(@RequestBody User user) {
        userRepository.registration(user);
    }
}
