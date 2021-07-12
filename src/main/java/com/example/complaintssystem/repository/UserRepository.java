package com.example.complaintssystem.repository;

import com.example.complaintssystem.User;

public interface UserRepository {
//
    void registration(User user);

    boolean login(User user);

}
