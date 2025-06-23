// service/ProductService.java
package com.beer.beer_forecast.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beer.beer_forecast.sales.Repository.ProductRepository;
import com.beer.beer_forecast.sales.model.Product;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

        public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
}


