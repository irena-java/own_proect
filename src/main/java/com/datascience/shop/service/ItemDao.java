package com.datascience.shop.service;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.entity.Item;

import java.util.List;

public interface ItemDao {

    Integer create(Item item) throws DaoException;

    void delete(Item item) throws DaoException;

    List<Item> findAll();

    Item findById(Integer id) throws DaoException;

}
