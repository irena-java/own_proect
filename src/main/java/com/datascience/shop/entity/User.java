package com.datascience.shop.entity;

public class User extends BaseEntity{
    private String name;
    private UserRole userRole;
    private String clientInn;
    private String country;
    private String contactInfo;
    private String password;


    public User() {
    }

    public User(Integer id, String name, UserRole userRole, String clientInn, String country, String contactInfo, String password) {
        super(id);
        if(name!=null && password!=null) {
            this.name = name;
            this.userRole = userRole;
            this.clientInn = clientInn;
            this.country = country;
            this.contactInfo = contactInfo;
            this.password = password;
        }
    }

    public User(String name, UserRole userRole, String clientInn, String country, String contactInfo, String password) {
        if(name!=null && password!=null) {
        this.name = name;
        this.userRole = userRole;
        this.clientInn = clientInn;
        this.country = country;
        this.contactInfo = contactInfo;
        this.password = password;
    }}

    public User(Integer id, String name, String password) {
        super(id);
        if(name!=null && password!=null) {
        this.name = name;
        this.password = password;
    }}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + super.getId() + '\'' +
                ",      name='" + name + '\'' +
                ", userRole=" + userRole +
                ", clientInn='" + clientInn + '\'' +
                ", country='" + country + '\'' +
                ", contactInfo=" + contactInfo + "}"+'\n';
    }

    public String toStringShort() {
        return "User{" +
                "name='" + name + '\'' +
                ", userRole=" + userRole +
                ", clientInn='" + clientInn + '\'' +
                ", country='" + country + '\'' +
                ", contactInfo=" + contactInfo.toString();

    }
}