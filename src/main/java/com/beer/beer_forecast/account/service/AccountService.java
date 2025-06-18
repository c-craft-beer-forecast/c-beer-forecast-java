package com.beer.beer_forecast.account.service;

import com.beer.beer_forecast.account.model.Login;
import com.beer.beer_forecast.account.repository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private LoginRepository repository;

    // パスワードエンコーダー
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 新規アカウント登録
    public void addEmployee(Login employee) {
        // パスワードをハッシュ化
        String rawPassword = employee.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);
        employee.setPassword(hashedPassword);

        repository.save(employee);
    }

    // アカウント削除
    public void deleteEmployeeById(Integer id) {
        repository.deleteById(id);
    }

    // アカウントの全件取得
    public List<Login> getAllEmployees() {
        return repository.findAll();
    }

}
