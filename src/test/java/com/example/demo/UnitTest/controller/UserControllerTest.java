package com.example.demo.UnitTest.controller;


//package com.example.demo.UnitTest.controller;
//
//import com.example.demo.controller.UserController;
//import com.example.demo.model.User;
//import com.example.demo.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User testUser;
    private List<User> testUserList;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId("1");
        testUser.setUsername("John");
        testUser.setFullName("Doe");
        testUser.setEmail("johndoe@example.com");

        testUserList = new ArrayList<>();
        testUserList.add(testUser);
    }

    @Test
    void addUser() {
      when(userService.addUser(any(User.class))).thenReturn(testUser);

        ResponseEntity<User> response = userController.addUser(testUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void getAllUsers() {
   when(userService.getAllUsers()).thenReturn(testUserList);

        ResponseEntity<List<User>> response = userController.getUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUserList, response.getBody());


    }

    @Test
    void updateUser() {
        when(userService.updateUser(eq("1"), any(User.class))).thenReturn(testUser);

        ResponseEntity<User> response = userController.updateUser("1", testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());


    }

    @Test
    void updateUser_notFound() {
//        when(userService.updateUser(eq("2"), any(User.class))).thenReturn(null);

        ResponseEntity<User> response = userController.updateUser("2", testUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());


    }

    @Test
    void deleteUser() {
//       doNothing().when(userService).deleteUser(eq("1"));

        ResponseEntity<String> response = userController.deleteUser("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("This Userinfo is deleted with the id:1", response.getBody());


    }
}
