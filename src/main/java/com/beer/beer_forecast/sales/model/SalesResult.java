package com.beer.beer_forecast.sales.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_results")
public class SalesResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesNumber;

    @Column(name = "record_date")
    private LocalDate record_date;

    @Column(name = "edited_date")
    private LocalDate editedDate;

    @Column(name = "num_of_sales")
    private Integer numOfSales;

    @Column(name = "num_of_customers", nullable = true)
    private Integer numOfCustomers;

    @Column(name = "product_id")
    private Integer productId;

    public SalesResult() {
    }

    public SalesResult(LocalDate record_date, LocalDate editedDate, Integer numOfSales, Integer numOfCustomers,
            Integer productId) {
        this.record_date = record_date;
        this.editedDate = editedDate;
        this.numOfSales = numOfSales;
        this.numOfCustomers = numOfCustomers;
        this.productId = productId;
    }

    public Integer getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(Integer salesNumber) {
        this.salesNumber = salesNumber;
    }

    public LocalDate getDate() {
        return record_date;
    }

    public void setDate(LocalDate date) {
        this.record_date = date;
    }

    public LocalDate getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(LocalDate editedDate) {
        this.editedDate = editedDate;
    }

    public Integer getNumOfSales() {
        return numOfSales;
    }

    public void setNumOfSales(Integer numOfSales) {
        this.numOfSales = numOfSales;
    }

    public Integer getNumOfCustomers() {
        return numOfCustomers;
    }

    public void setNumOfCustomers(Integer numOfCustomers) {
        this.numOfCustomers = numOfCustomers;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    
}
