package com.beer.beer_forecast.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beer.beer_forecast.sales.Repository.SalesResultRepository;
import com.beer.beer_forecast.sales.model.SalesResult;

@Service
public class SalesResultService {

    private final SalesResultRepository salesResultRepository;

    @Autowired
    public SalesResultService(SalesResultRepository salesResultRepository) {
        this.salesResultRepository = salesResultRepository;
    }

    public void saveSalesResult(SalesResult salesResult) {
        salesResultRepository.save(salesResult);
    }

    public List<SalesResult> findAll() {
        return salesResultRepository.findAll();
    }

    public Optional<SalesResult> findById(Integer id) {
        return salesResultRepository.findById(id);
    }

       public void deleteById(Integer id) {
        salesResultRepository.deleteById(id);
    }
}