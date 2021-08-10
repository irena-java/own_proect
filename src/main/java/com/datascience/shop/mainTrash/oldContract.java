package com.datascience.shop.mainTrash;

import com.datascience.shop.entity.BaseEntity;
import com.datascience.shop.entity.Basket;

import java.time.LocalDate;

public class oldContract extends BaseEntity {
    private Basket basket;
    private LocalDate dateBeginContract;
    private LocalDate deadlineContract;
    private oldPaymentsCalendar oldPaymentsCalendar;
    private oldStatusContract oldStatusContract;

    public oldContract() {
    }

    public oldContract(Integer id, Basket basket, LocalDate dateBeginContract, LocalDate deadlineContract, oldPaymentsCalendar oldPaymentsCalendar, oldStatusContract oldStatusContract) {
        super(id);
        this.basket = basket;
        this.dateBeginContract = dateBeginContract;
        this.deadlineContract = deadlineContract;
        this.oldPaymentsCalendar = oldPaymentsCalendar;
        this.oldStatusContract = oldStatusContract;
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

    public oldPaymentsCalendar getPaymentsCalendar() {
        return oldPaymentsCalendar;
    }

    public void setPaymentsCalendar(oldPaymentsCalendar oldPaymentsCalendar) {
        this.oldPaymentsCalendar = oldPaymentsCalendar;
    }

    public oldStatusContract getStatusContract() {
        return oldStatusContract;
    }

    public void setStatusContract(oldStatusContract oldStatusContract) {
        this.oldStatusContract = oldStatusContract;
    }
}