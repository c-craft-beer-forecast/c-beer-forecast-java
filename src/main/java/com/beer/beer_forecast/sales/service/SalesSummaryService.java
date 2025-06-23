package com.beer.beer_forecast.sales.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.beer.beer_forecast.sales.Repository.*;

@Service

public class SalesSummaryService {
    private final SalesResultRepository salesResultRepository;

    // コンストラクタインジェクション
    public SalesSummaryService(SalesResultRepository salesResultRepository) {
        this.salesResultRepository = salesResultRepository;
    }

    // テーブル表示用に作成
    public List<Object[]> getSalesByDate(LocalDate date) {
    return salesResultRepository.findSalesDetailsByDate(date);
}
}
