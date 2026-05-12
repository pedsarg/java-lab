package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.User;


public interface UserRepository extends MongoRepository<User, String>{

}
