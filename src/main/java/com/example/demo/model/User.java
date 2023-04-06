package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Random;

@Data
@Document(collection = "UserDB")
public class User{
    @Id
    private  String id;

    @NotBlank
    @Size(max=20)
    public String username;

    @NotBlank
    @Size(max=100)
    public String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String address;

    @NotBlank
    private String mobileNumber;


    @NotBlank
    @Size(max=100)
    private String currentOrganization;

    public User( String id   ,String username, String fullName, String email, String address, String mobileNumber, String currentOrganization) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.currentOrganization = currentOrganization;
    }

    public User() {

    }

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCurrentOrganization() {
        return currentOrganization;
    }

    public void setCurrentOrganization(String currentOrganization) {
        this.currentOrganization = currentOrganization;
    }



    public void replaceVowelsWithSpecialChars() {
        String specialChars = "!@#$%^&*()_+-={}[]\\|:\";'<>?,./";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (char c : fullName.toCharArray()) {
            if ("aeiouAEIOU".indexOf(c) >= 0) {
                sb.append(specialChars.charAt(random.nextInt(specialChars.length())));
            } else {
                sb.append(c);
            }
        }
        fullName = sb.toString();
    }



}
/**
 * Construct a POST API that takes in user details
 * (username, full name, email, address, mobile number, current organization)
 * and adds it to a "user" table in a database named "userDB" */