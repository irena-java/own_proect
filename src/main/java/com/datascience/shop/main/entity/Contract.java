package com.datascience.shop.main.entity;

import java.time.LocalDate;

public class Contract extends BaseEntity{
    private Basket basket;
    private LocalDate dateBeginContract;
    private LocalDate deadlineContract;
    private PaymentsCalendar paymentsCalendar;
    private StatusContract statusContract;

    public Contract() {
    }

    public Contract(Integer id, Basket basket, LocalDate dateBeginContract, LocalDate deadlineContract, PaymentsCalendar paymentsCalendar, StatusContract statusContract) {
        super(id);
        this.basket = basket;
        this.dateBeginContract = dateBeginContract;
        this.deadlineContract = deadlineContract;
        this.paymentsCalendar = paymentsCalendar;
        this.statusContract = statusContract;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public LocalDate getDateBeginContract() {
        return dateBeginContract;
    }

    public void setDateBeginContract(LocalDate dateBeginContract) {
        this.dateBeginContract = dateBeginContract;
    }

    public LocalDate getDeadlineContract() {
        return deadlineContract;
    }

    public void setDeadlineContract(LocalDate deadlineContract) {
        this.deadlineContract = deadlineContract;
    }

    public PaymentsCalendar getPaymentsCalendar() {
        return paymentsCalendar;
    }

    public void setPaymentsCalendar(PaymentsCalendar paymentsCalendar) {
        this.paymentsCalendar = paymentsCalendar;
    }

    public StatusContract getStatusContract() {
        return statusContract;
    }

    public void setStatusContract(StatusContract statusContract) {
        this.statusContract = statusContract;
    }
}