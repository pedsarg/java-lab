package com.example.demo.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
    
    @GetMapping
    public ResponseEntity<User> findAll(){
        User u = new User(1L, "Bruna", "bruna@gmail.com", "9999999", "12345");

        return ResponseEntity.ok().body(u);
    }


}
