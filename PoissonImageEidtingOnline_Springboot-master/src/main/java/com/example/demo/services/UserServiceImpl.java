package com.example.demo.services;

import com.example.demo.document.Users;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersRepository usersRepository;
    @Override
    public Users checkUser(String username, String password) {
        Users user = usersRepository.findByUsernameAndPassword(username,password);
        return user;
    }

    @Override
    public Boolean saveUser(Users user) {
        usersRepository.save(user);
        return true;
    }
}
