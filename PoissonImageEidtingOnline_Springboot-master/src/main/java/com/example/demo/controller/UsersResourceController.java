
package com.example.demo.controller;

import com.example.demo.document.Users;
import com.example.demo.repository.UsersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersResourceController {
    private UsersRepository usersRepository;
    public UsersResourceController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @GetMapping("/all")
    public List<Users> getAll(){
        return usersRepository.findAll();
    }
}
