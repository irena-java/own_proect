package com.datascience.shop.mainTrash;

import com.datascience.shop.entity.BaseEntity;

import java.time.LocalDate;

public class oldContactInfo extends BaseEntity {
    private String contactPersonName;
    private String phone;
    private String email;
    private LocalDate closedDate;

    public oldContactInfo() {
    }

    public oldContactInfo(String contactPersonName, String phone, String email) {
        this.contactPersonName = contactPersonName;
        this.phone = phone;
        this.email = email;
    }

    public oldContactInfo(Integer id, String contactPersonName, String phone, String email) {
        super(id);
        this.contactPersonName = contactPersonName;
        this.phone = phone;
        this.email = email;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getClosedDate() {
        return closedDate;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "ID='" + super.getId() + '\'' +
                "contactPersonName='" + contactPersonName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", closedDate=" + closedDate +
                '}';
    }
}
