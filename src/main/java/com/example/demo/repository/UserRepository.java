package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

@Repository

public interface UserRepository extends MongoRepository<User, String> {


}




