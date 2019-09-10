package com.example.demo.services;

import com.example.demo.document.Users;

public interface UserService {

    Users checkUser(String username, String password);
    Boolean saveUser(Users user);
}
