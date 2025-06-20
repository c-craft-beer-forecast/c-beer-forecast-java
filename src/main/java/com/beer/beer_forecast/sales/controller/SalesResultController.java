package com.beer.beer_forecast.sales.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beer.beer_forecast.sales.model.SalesResult;
import com.beer.beer_forecast.sales.service.ProductService;
import com.beer.beer_forecast.sales.service.SalesResultService;
import com.beer.beer_forecast.sales.service.SalesSummaryService;

@Controller
public class SalesResultController {
    @Autowired
    private SalesResultService salesResultService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SalesSummaryService salesSummaryService;

    // 新規登録画面
    @GetMapping("/index")
    public String showForm(
            @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        model.addAttribute("salesResult", new SalesResult());
        model.addAttribute("productList", productService.findAll());

        if (date == null) {
            date = LocalDate.now(); // 初期値を今日に設定
        }

        if (date == null) {
            // dateパラメータがなければ今日の日付を初期値にする
            date = LocalDate.now();
        }
        // 指定された日付の売上集計を取得
        List<Object[]> summaryList = salesSummaryService.getSalesByDate(date);
        model.addAttribute("summaryList", summaryList);

        // 選択された日付をモデルにセットして画面のinput valueに反映
        model.addAttribute("selectedDate", date);

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
        return "redirect:/index";
    }

    // 削除
    @PostMapping("/sales/delete")
    public String delete(@RequestParam("id") Integer id) {
        salesResultService.deleteById(id);
        return "redirect:/index";
    }
}
