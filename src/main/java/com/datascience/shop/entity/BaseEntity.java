package com.datascience.shop.entity;
/**
 *@value id - Unique identifier of entity in the database (primary key in the table)
 **/

public class BaseEntity {
    private Integer id;

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
