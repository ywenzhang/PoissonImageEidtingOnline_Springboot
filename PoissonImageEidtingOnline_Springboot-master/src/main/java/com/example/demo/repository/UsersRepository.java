package com.example.demo.repository;

import com.example.demo.document.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
    Users findByUsernameAndPassword(String name, String password);
}
