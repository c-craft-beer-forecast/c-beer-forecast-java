// repository/ProductRepository.java
package com.beer.beer_forecast.sales.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beer.beer_forecast.sales.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {}
