package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userAPI")

public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/createUser")
    public ResponseEntity<String>  createUser(@RequestBody User user)
    {
         userService.createUser(user);
    return new ResponseEntity<>("User created with the name:"+ user.getFullName(), HttpStatus.CREATED);}
@GetMapping("/getAllUsers")
    public ResponseEntity<String> getUser(){
        List<User> list = userService.getUser();
    return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);}

@PutMapping("/updateUser")
public  ResponseEntity<String> updateUser(@RequestParam String id,@RequestParam String username){
userService.updateUser(id, username);
 return  new ResponseEntity<>("update User" , HttpStatus.OK);}

@DeleteMapping("/deleteUser")
public ResponseEntity<String> deleteUser(@RequestParam String id){

userService.deleteUser(id);
return new ResponseEntity<>("User deleted" ,    HttpStatus.OK);
}
}