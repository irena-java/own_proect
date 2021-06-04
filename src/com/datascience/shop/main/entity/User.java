package com.datascience.shop.main.entity;

public class User extends BaseEntity{
    private String name;
    private UserRole userRole;
    private String clientInn;
    private String country;
    private ContactInfo contactInfo;


    public User() {
    }

    public User(Integer id, String name, UserRole userRole, String clientInn, String country, ContactInfo contactInfo) {
        super(id);
        this.name = name;
        this.userRole = userRole;
        this.clientInn = clientInn;
        this.country = country;
        this.contactInfo = contactInfo;
    }

    public User(String name, UserRole userRole, String clientInn, String country, ContactInfo contactInfo) {
        this.name = name;
        this.userRole = userRole;
        this.clientInn = clientInn;
        this.country = country;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getClientInn() {
        return clientInn;
    }

    public void setClientInn(String clientInn) {
        this.clientInn = clientInn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + super.getId() + '\'' +
                "name='" + name + '\'' +
                ", userRole=" + userRole +
                ", clientInn='" + clientInn + '\'' +
                ", country='" + country + '\'' +
                ", contactInfo=" + contactInfo +
                '}';
    }

    public String toStringShort() {
        return "User{" +
                "name='" + name + '\'' +
                ", userRole=" + userRole +
                ", clientInn='" + clientInn + '\'' +
                ", country='" + country + '\'' +
                ", contactInfo=" + contactInfo.toString() +
                '}';
    }
}