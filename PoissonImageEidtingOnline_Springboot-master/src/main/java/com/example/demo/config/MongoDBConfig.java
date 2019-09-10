package com.example.demo.config;

import com.example.demo.document.Users;
import com.example.demo.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses= UsersRepository.class)
@Configuration
public class MongoDBConfig {
    @Bean
    CommandLineRunner commandLineRunner(UsersRepository usersRepository){
        return string ->{
            usersRepository.save(new Users("Yiwen","ywmengjiao@gmail.com","Zyw921019"));
        };
    }
}


