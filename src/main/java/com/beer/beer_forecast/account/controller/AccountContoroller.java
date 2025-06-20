package com.beer.beer_forecast.account.controller;

import com.beer.beer_forecast.account.service.*;
import com.beer.beer_forecast.account.model.Login;
import com.beer.beer_forecast.weather.service.WeatherService;
import com.beer.beer_forecast.weather.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class AccountContoroller {
    @Autowired
    private AccountService service;

    @Autowired
    private WeatherService weatherService;

    // admin画面のgetメソッド。logincontrollerからリダイレクト
    @GetMapping("/admin")
    public String showAddForm(Model model) {
        model.addAttribute("employeeList", service.getAllEmployees());
        model.addAttribute("employee", new Login());
        List<WeatherData> data = weatherService.getAllWeather();
        model.addAttribute("weatherList", data);
        return "admin";
    }

    // アカウント追加
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Login employee) {
        service.addEmployee(employee);
        return "redirect:/admin";
    }

    // アカウント削除
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        service.deleteEmployeeById(id);
        return "redirect:/admin";
    }

    // アカウント編集
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Login employee = service.findById(id);
        model.addAttribute("employee", employee);
        return "editaccount"; // edit.html に飛ぶ
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Login updatedEmployee) {
        service.updateEmployeeInfo(updatedEmployee);

        return "redirect:/admin";
    }
}
