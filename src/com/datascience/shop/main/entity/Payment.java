package com.datascience.shop.main.entity;

import java.time.LocalDate;

public class Payment extends BaseEntity{
    private LocalDate date;
    private Double sum;

    public Payment() {
    }

    public Payment(Integer id, LocalDate date, Double sum) {
        super(id);
        this.date = date;
        this.sum = sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}


