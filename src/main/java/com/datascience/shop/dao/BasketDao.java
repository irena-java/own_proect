package com.datascience.shop.dao;

import com.datascience.shop.dao.impl.DaoException;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;

/**
 * The interface for referring to the database when operating with the basket
 */
public interface BasketDao {
    Basket insertOrUpdate(Basket basket) throws DaoException;

    void deleteBasket(Basket basket) throws DaoException;

    void deleteFromBasketByItemId(Integer userId, Integer itemId) throws DaoException;

    Basket findById(User user) throws DaoException;
}
