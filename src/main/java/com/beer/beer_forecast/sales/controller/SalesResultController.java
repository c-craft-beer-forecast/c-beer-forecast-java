package com.beer.beer_forecast.sales.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beer.beer_forecast.sales.model.SalesResult;
import com.beer.beer_forecast.sales.model.Product;
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
    public String edit(@RequestParam("id") Integer salesNumber, Model model) {
        SalesResult salesResult = salesResultService.findById(salesNumber)
                .orElseThrow(() -> new IllegalArgumentException("無効なID: " + salesNumber));
        model.addAttribute("salesResult", salesResult);
        model.addAttribute("productList", productService.findAll());
        Product product = productService.findById(salesResult.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("無効な商品ID: " + salesResult.getProductId()));
        model.addAttribute("productName", product.getName());
        return "editsalesresult";
    }

    // 登録・更新

    @PostMapping("/sales/submitAll")
    public String submitAll(
            @RequestParam("date") String date,
            @RequestParam("productIdList") List<Integer> productIdList,
            @RequestParam("numOfSalesList") List<Integer> numOfSalesList,
            @RequestParam("numOfCustomers") Integer numOfCustomers ){
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

        return "redirect:/index";
    }

    // 削除
    @PostMapping("/sales/delete")
    public String delete(@RequestParam("id") Integer id) {
        salesResultService.deleteById(id);
        return "redirect:/index";
    }
}
