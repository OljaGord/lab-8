package com.example.projectgord8.service;

import com.example.projectgord8.entity.User;

import java.util.List;

public interface IUserService {
    void saveUser(User user);

    List<User> findAllUsers();

    User findByUsername(String username);
}
