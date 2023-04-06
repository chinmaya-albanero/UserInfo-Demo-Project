
package com.example.demo.UnitTest.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User("1", "username1", "full name1", "email1", "address1", "1234567890", "org1");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User result = userService.addUser(user);
        Assertions.assertEquals(user, result);
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "username1", "full name1", "email1", "address1", "1234567890", "org1"));
        userList.add(new User("2", "username2", "full name2", "email2", "address2", "0987654321", "org2"));

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();
        Assertions.assertEquals(userList, result);
    }

    @Test
    public void testUpdateUser() {
        String id = "1";
        User updatedUser = new User("1", "chinu", "csahooo", "c@gmail.com", "bbsr,Odisha", "1111111111", "alban");

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(new User("1", "s.se", "suse mhan", "emo@ela", "anything", "1234567890", "eyan")));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(id, updatedUser);
        Assertions.assertEquals(updatedUser, result);
    }

    @Test
    public void testUpdateUserWithNonExistingUser() {
        String id = "1";
        User updatedUser = new User("1", "gfg", "my fullname", "up@email", "Anyehere", "1111111111", "same");

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        User result = userService.updateUser(id, updatedUser);
        Assertions.assertNull(result);
    }

    @Test
    public void testDeleteUser() {
        String id = "1";

        userService.deleteUser(id);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(id);
    }
}

