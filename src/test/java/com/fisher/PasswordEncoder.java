package com.fisher;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "Mario123";
        String encodedPassword =  encoder.encode(password);
        System.out.println(encodedPassword);

    }
}
