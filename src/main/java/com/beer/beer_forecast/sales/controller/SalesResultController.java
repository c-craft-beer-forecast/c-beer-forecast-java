package com.beer.beer_forecast.sales.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beer.beer_forecast.sales.model.SalesResult;
import com.beer.beer_forecast.sales.service.ProductService;
import com.beer.beer_forecast.sales.service.SalesResultService;

@Controller
public class SalesResultController {
    @Autowired
    private SalesResultService salesResultService;
    @Autowired
    private ProductService productService;

    // 新規登録画面
    @GetMapping("/index")
    public String showForm(Model model) {
        model.addAttribute("salesResult", new SalesResult());
        model.addAttribute("productList", productService.findAll());
        return "index";
    }

    // 編集時
    @GetMapping("/sales/edit")
    public String edit(@RequestParam("id") Integer id, Model model) {
        SalesResult sr = salesResultService.findById(id).orElse(null);
        model.addAttribute("salesResult", sr);
        model.addAttribute("productList", productService.findAll());
        return "index";
    }

    // 登録・更新
    @PostMapping("/sales/submit")
    public String submit(@ModelAttribute SalesResult salesResult, @RequestParam("date") String date) {
        salesResult.setDate(LocalDate.parse(date));
        salesResult.setEditedDate(LocalDate.now());
        salesResultService.saveSalesResult(salesResult);
        return "redirect:/";
    }

    // 削除
    @PostMapping("/sales/delete")
    public String delete(@RequestParam("id") Integer id) {
        salesResultService.deleteById(id);
        return "redirect:/";
    }
}
