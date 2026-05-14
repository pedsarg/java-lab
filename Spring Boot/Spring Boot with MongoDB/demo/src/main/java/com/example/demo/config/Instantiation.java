package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{


    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... arg0) throws Exception{
        userRepository.deleteAll();
    
        User sam = new User(null, "Sam Lin", "sali@gmail.com");
        User jhon = new User(null, "Jhon", "j@gmail.com");

        userRepository.saveAll(Arrays.asList(sam, jhon));
    }

}
