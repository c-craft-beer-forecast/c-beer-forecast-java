package com.beer.beer_forecast.account.service;

import com.beer.beer_forecast.account.model.Login;
import com.beer.beer_forecast.account.repository.LoginRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginService {
    private final LoginRepository repository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(LoginRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Login> getAllLogin() {
        return repository.findAll();
    }

    public void addLogin(String email, String rawPassword) {
      
        String encodedPassword = passwordEncoder.encode(rawPassword);
        repository.save(new Login(email, encodedPassword));
    }

    public Optional<Login> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean checkLogin(String email, String rawPassword) {
        Optional<Login> userOpt = repository.findByEmail(email);
        if (userOpt.isPresent()) {
            String encodedPassword = userOpt.get().getPassword();
            return passwordEncoder.matches(rawPassword, encodedPassword); 
        }
        return false;
    }

    public boolean checkPassword(Login user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public boolean checkAdminLogin(String email, String rawPassword) {
        Optional<Login> userOpt = repository.findByEmail(email);
        if (userOpt.isPresent()) {
            Login user = userOpt.get();
            boolean passwordMatch = passwordEncoder.matches(rawPassword, user.getPassword());
            boolean isAdmin = Boolean.TRUE.equals(user.getAdmin());
            return passwordMatch && isAdmin;
        }
        return false;
    }

    public boolean changePasswordWithOld(String email, String oldPassword, String newPassword) {
        Optional<Login> userOpt = repository.findByEmail(email);
        if (userOpt.isPresent()) {
            Login user = userOpt.get();
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                String encodedNewPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encodedNewPassword);
                repository.save(user);
                return true;
            }
        }
        return false; 
    }

}
