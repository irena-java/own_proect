package com.datascience.shop.entity;
/**
 *value id уникальный идентификатор сущности в базе данных
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
