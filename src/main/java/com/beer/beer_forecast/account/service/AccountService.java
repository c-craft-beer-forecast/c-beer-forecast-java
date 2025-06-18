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

    // アカウント編集
    public Login findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("従業員データが存在しません: " + id));
    }

    public void updateEmployeeInfo(Login updatedEmployee) {
        Login existing = repository.findById(updatedEmployee.getId())
                .orElseThrow(() -> new RuntimeException("従業員データが存在しません: " + updatedEmployee.getId()));

        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());

        String newPassword = updatedEmployee.getPassword();
        if (newPassword != null && !newPassword.isBlank()){
            String hashed = passwordEncoder.encode(newPassword);
            existing.setPassword(hashed);
        }
        existing.setAdmin(updatedEmployee.getAdmin());
        repository.save(existing);
    }

    // アカウントの全件取得
    public List<Login> getAllEmployees() {
        return repository.findAll();
    }

}
