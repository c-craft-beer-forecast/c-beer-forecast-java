package com.beer.beer_forecast.sales.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beer.beer_forecast.sales.model.SalesResult;

@Repository
public interface SalesResultRepository extends JpaRepository<SalesResult, Integer> {
}
