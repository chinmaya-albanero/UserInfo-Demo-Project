package com.example.demo;

import com.example.demo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class SampleTestUsingRestTemplate {
    String url ="http://localhost:8080/";

    RestTemplate restTemplate = new RestTemplate();
   @Test
   public void  testcaseForPOSTAPI(){
       TestRestTemplate testRestTemplate = new TestRestTemplate();
       // create new user object
       User newUser = new User("Alice", "Smith", "alice.smith@example.com");
       ResponseEntity<String> response = testRestTemplate.postForEntity(
               url  + "/userAPI/createUser", newUser, String.class);

       Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);

   }
    @Test

    public  void testcaseForGETAPI(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();

    ResponseEntity<String> response = testRestTemplate.getForEntity(url + "/userAPI/getAllUsers" , String.class);
      Assertions.assertEquals(response.getStatusCode(), HttpStatus.ACCEPTED);
    }
}
