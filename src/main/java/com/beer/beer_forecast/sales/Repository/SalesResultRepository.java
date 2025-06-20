package com.beer.beer_forecast.sales.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beer.beer_forecast.sales.model.SalesResult;

@Repository
public interface SalesResultRepository extends JpaRepository<SalesResult, Integer> {
    @Query(value = """
            SELECT record_date, product_id, SUM(num_of_sales) AS total_sales
            FROM sales_results
            WHERE record_date BETWEEN :startDate AND :endDate
            GROUP BY record_date, product_id
            ORDER BY record_date, product_id
            """, nativeQuery = true)
    List<Object[]> findSalesSummaryByDateAndProduct(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query(value = """
            SELECT sr.product_id, p.name, SUM(sr.num_of_sales) AS total_sales
            FROM sales_results sr
            JOIN products p ON sr.product_id = p.id
            WHERE sr.record_date = :date
            GROUP BY sr.product_id, p.name
            ORDER BY sr.product_id
            """, nativeQuery = true)
    List<Object[]> findSalesSummaryBySingleDate(@Param("date") LocalDate date);
}
