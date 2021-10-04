package com.datascience.shop.dao;

import com.datascience.shop.dao.impl.DaoException;
import com.datascience.shop.entity.Item;

import java.util.List;
/**
 * The interface for referring to the database when operating with the items
 */

public interface ItemDao {

    Integer create(Item item) throws DaoException;

    void delete(Item item) throws DaoException;

    List<Item> findAll() throws DaoException;

    Item findById(Integer id) throws DaoException;

}
