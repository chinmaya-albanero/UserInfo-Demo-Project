package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(User user){

        userRepository.save(user);
    }

    public List<User> getUser() {
        return userRepository.findAll();

}

public void updateUser(String id , String username){
        User user = userRepository.findById(id).get();
        user.setUsername(username);
userRepository.save(user);
}

public void deleteUser(String id){
        User user = userRepository.findById(id).get();
        userRepository.delete(user);

}
}
