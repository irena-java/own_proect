package com.datascience.shop.service;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;

import java.sql.Connection;

public interface BasketDao {
    Basket insertOrUpdate(Basket basket) throws DaoException;

    void deleteBasket(Basket basket,Connection connection) throws DaoException;

    void deleteFromBasketByItemId(Integer userId, Integer itemId) throws DaoException;

    Basket findById(User user) throws DaoException;
}
