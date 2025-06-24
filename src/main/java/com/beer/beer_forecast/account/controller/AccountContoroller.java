package com.beer.beer_forecast.account.controller;

import com.beer.beer_forecast.account.model.Login;
import com.beer.beer_forecast.account.service.AccountService;
import com.beer.beer_forecast.sales.model.Product;
import com.beer.beer_forecast.sales.model.SalesResult;
import com.beer.beer_forecast.sales.service.ProductService;
import com.beer.beer_forecast.sales.service.SalesResultService;
import com.beer.beer_forecast.sales.service.SalesSummaryService;
import com.beer.beer_forecast.weather.model.WeatherData;
import com.beer.beer_forecast.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AccountContoroller {
    @Autowired
    private AccountService service;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private SalesResultService salesResultService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SalesSummaryService salesSummaryService;

    // 管理者画面全体統合（admin.html）
    @GetMapping("/admin")
    public String showAdminPage(
            @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {

        model.addAttribute("employeeList", service.getAllEmployees());
        model.addAttribute("employee", new Login());

        List<WeatherData> weatherData = weatherService.getAllWeather();
        model.addAttribute("weatherList", weatherData);

        model.addAttribute("salesResult", new SalesResult());
        model.addAttribute("productList", productService.findAll());

        if (date == null) {
            date = LocalDate.now();
        }
        List<Object[]> summaryList = salesSummaryService.getSalesByDate(date);
        model.addAttribute("summaryList", summaryList);
        model.addAttribute("selectedDate", date);

        return "admin";
    }

    @GetMapping("/admin/edit/sales")
    public String editSales(@RequestParam("id") Integer salesNumber, Model model) {
        SalesResult salesResult = salesResultService.findById(salesNumber)
                .orElseThrow(() -> new IllegalArgumentException("無効なID: " + salesNumber));
        model.addAttribute("salesResult", salesResult);
        model.addAttribute("productList", productService.findAll());

        model.addAttribute("employeeList", service.getAllEmployees());
        model.addAttribute("employee", new Login());
        model.addAttribute("weatherList", weatherService.getAllWeather());

        List<Object[]> summaryList = salesSummaryService.getSalesByDate(salesResult.getDate());
        model.addAttribute("summaryList", summaryList);
        model.addAttribute("selectedDate", salesResult.getDate());

        return "editsalesresultAdmin";
    }

    @PostMapping("/admin/sales/submitAll")
    public String submitAll(
            @RequestParam("date") String date,
            @RequestParam("productIdList") List<Integer> productIdList,
            @RequestParam("numOfSalesList") List<Integer> numOfSalesList,
            @RequestParam("numOfCustomers") Integer numOfCustomers) {

        LocalDate recordDate = LocalDate.parse(date);
        LocalDate editedDate = LocalDate.now();

        for (int i = 0; i < productIdList.size(); i++) {
            SalesResult sr = new SalesResult();
            sr.setDate(recordDate);
            sr.setEditedDate(editedDate);
            sr.setProductId(productIdList.get(i));
            sr.setNumOfSales(numOfSalesList.get(i));
            sr.setNumOfCustomers(numOfCustomers);
            salesResultService.saveSalesResult(sr);
        }

        return "redirect:/admin";
    }

    @PostMapping("/admin/sales/delete")
    public String deleteSales(@RequestParam("id") Integer id) {
        salesResultService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/sales/update")
    public String updateSales(@ModelAttribute SalesResult updatedResult) {
        updatedResult.setEditedDate(LocalDate.now());
        salesResultService.saveSalesResult(updatedResult);
        return "redirect:/admin";
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
        return "editaccount";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Login updatedEmployee) {
        service.updateEmployeeInfo(updatedEmployee);
        return "redirect:/admin";
    }
}
