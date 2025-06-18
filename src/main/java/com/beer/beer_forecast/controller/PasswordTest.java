
package com.beer.beer_forecast.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        String rawPassword = "admin123";
        String encodedPassword = "$2a$10$cPs1.CRM7tDohmC4pU5.Y.IFD0wzZ6teQCMVdqk5VtS4yxKqcs5Na";

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("匹配结果: " + encoder.matches(rawPassword, encodedPassword));
        System.out.println("新加密密码: " + encoder.encode(rawPassword));
    }
}

