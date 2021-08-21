package com.datascience.shop.mainTrash.trash;

import com.datascience.shop.entity.BaseEntity;

import java.util.List;

public class oldPaymentsCalendar extends BaseEntity {
    private List<oldPayment> planedOldPayments;
    private List<oldPayment> factualOldPayments;

    public oldPaymentsCalendar() {
    }

    public oldPaymentsCalendar(Integer id, List<oldPayment> planedOldPayments, List<oldPayment> factualOldPayments) {
        super(id);
        this.planedOldPayments = planedOldPayments;
        this.factualOldPayments = factualOldPayments;
    }

    public List<oldPayment> getPlanedPayments() {
        return planedOldPayments;
    }

    public void setPlanedPayments(List<oldPayment> planedOldPayments) {
        this.planedOldPayments = planedOldPayments;
    }

    public List<oldPayment> getFactualPayments() {
        return factualOldPayments;
    }

    public void setFactualPayments(List<oldPayment> factualOldPayments) {
        this.factualOldPayments = factualOldPayments;
    }
}
