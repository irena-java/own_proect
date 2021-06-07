package com.datascience.shop.main.entity;

import java.util.List;

public class PaymentsCalendar extends BaseEntity{
    private List<Payment> planedPayments;
    private List<Payment> factualPayments;

    public PaymentsCalendar() {
    }

    public PaymentsCalendar(Integer id, List<Payment> planedPayments, List<Payment> factualPayments) {
        super(id);
        this.planedPayments = planedPayments;
        this.factualPayments = factualPayments;
    }

    public List<Payment> getPlanedPayments() {
        return planedPayments;
    }

    public void setPlanedPayments(List<Payment> planedPayments) {
        this.planedPayments = planedPayments;
    }

    public List<Payment> getFactualPayments() {
        return factualPayments;
    }

    public void setFactualPayments(List<Payment> factualPayments) {
        this.factualPayments = factualPayments;
    }
}
