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