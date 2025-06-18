package com.beer.beer_forecast.account.controller;

import com.beer.beer_forecast.account.service.*;
import com.beer.beer_forecast.account.model.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;

import java.util.List;

@Controller
public class LoginController {
    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Login> logins = service.getAllLogin();
        model.addAttribute("logins", logins);
        return "login";
    }

    @GetMapping("/admin-login")
    public String showAdminLoginPage() {
        return "admin-login"; // 注意不要写 ".html"
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // 注意不要写 ".html"
    }

    @GetMapping("/changepassword")
    public String showChangePasswordForm() {
        return "changepassword"; // 返回 changepassword.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        if (email.isBlank() || password.isBlank()) {
            model.addAttribute("error", "必須項目が未入力です");
            return "login";
        }

        Optional<Login> userOpt = service.findByEmail(email);

        if (userOpt.isPresent()) {
            Login user = userOpt.get();
            if (service.checkLogin(email, password)) {
                // ✅ 登录成功，存入 session
                session.setAttribute("loginUser", user);
                return "index";
            }
        }

        model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
        return "login";
    }

    @PostMapping("/admin-login")
    public String adminLogin(@RequestParam String email,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        if (email.isBlank() || password.isBlank()) {
            model.addAttribute("error", "必須項目が未入力です");
            return "admin-login";
        }

        Optional<Login> userOpt = service.findByEmail(email);

        if (service.checkAdminLogin(email, password)) {
            if (userOpt.isPresent()) {
                Login user = userOpt.get();
                session.setAttribute("loginUser", user);
                model.addAttribute("message", "管理者ログイン成功");
                return "redirect:/admin";
            }
        }

        model.addAttribute("error", "メールアドレスまたはパスワードが間違っている、または管理者権限がありません");
        return "admin-login";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String email,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            Model model) {

        boolean success = service.changePasswordWithOld(email, oldPassword, newPassword);

        if (success) {
            model.addAttribute("message", "パスワードを変更しました。");
        } else {
            model.addAttribute("error", "古いパスワードが正しくありません。");
        }
        return "admin"; // 或重定向到确认页面
    }

}
