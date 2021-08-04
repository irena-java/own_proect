package com.datascience.shop.service;

import com.datascience.shop.dao.BasketDao;
import com.datascience.shop.dao.DaoException;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;

import java.util.ArrayList;

public class BasketService {
    private BasketDao basketDao = new BasketDao();

    public Basket createOrUpdate(Basket basket) throws ServiceException {
        try {
            return basketDao.insertOrUpdate(basket);
        } catch (DaoException e) {
            throw new ServiceException("Failed to save into database");
        }
    }

    public Basket findOrCreateForUser(User user) throws ServiceException {
        try {
            Basket basket = basketDao.findById(user);

            return basket == null ? new Basket(null, user, new ArrayList<>()) : basket;
        } catch (DaoException e) {
            throw new ServiceException("Failed to find");
        }
    }
}
