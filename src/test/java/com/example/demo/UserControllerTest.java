package com.example.demo;

import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private UserRepository userRepository;
//
//    @BeforeEach
//    public void setUp() {
//        userRepository.deleteAll().block();
//    }

    @Test
    
    public void testCreateUser() {
        
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        webClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().valueEquals("Location", "/users/1")
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.name").isEqualTo("John Doe")
                .jsonPath("$.email").isEqualTo("john.doe@example.com");
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId("1");
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        userRepository.save(user).block();

        webClient.get()
                .uri("/users/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.name").isEqualTo("John Doe")
                .jsonPath("$.email").isEqualTo("john.doe@example.com");
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId("1");
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        userRepository.save(user).block();

        User updatedUser = new User();
        updatedUser.setId("1");
        updatedUser.setName("Jane Doe");
        updatedUser.setEmail("jane.doe@example.com");

        webClient.put()
                .uri("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(updatedUser), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.name").isEqualTo("Jane Doe")
                .jsonPath("$.email").isEqualTo("jane.doe@example.com");
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId("1");
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        userRepository.save(user).block();

        webClient.delete()
                .uri("/users/1")
                .exchange()
                .expectStatus().isOk();

        webClient.get()
                .uri("/users/1")
                .exchange()
                .expectStatus().isNotFound();
    }

}
