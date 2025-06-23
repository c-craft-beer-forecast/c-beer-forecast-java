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

    // 売り上げ表示用
    @Query(value = """
            SELECT sr.sales_number, sr.record_date, sr.product_id, p.name, sr.num_of_sales, sr.num_of_customers
            FROM sales_results sr
            JOIN products p ON sr.product_id = p.id
            WHERE sr.record_date = :date
            ORDER BY sr.product_id
            """, nativeQuery = true)
    List<Object[]> findSalesDetailsByDate(@Param("date") LocalDate date);

}
