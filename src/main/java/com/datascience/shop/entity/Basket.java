package com.datascience.shop.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *value client - ...todo по всем сущностям и вообще по всем классам
 *
 **/

public class Basket extends BaseEntity {
    private User client;
    private List<Item> items = new ArrayList<>();

    public Basket() {
    }

    public Basket(Integer id, User client, List<Item> items) {
        super(id);
        this.client = client;
        this.items = items;
    }

    public Basket(User client, List<Item> items) {
        this.client = client;
        this.items = items;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id" + super.getId() +
                ", client=" + client.toString() + "\n" +
                ", itemsInBasket=" + items.toString() +
                '}' + "\n";
    }
}