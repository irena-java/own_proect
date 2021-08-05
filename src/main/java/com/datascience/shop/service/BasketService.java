package com.datascience.shop.service;

import com.datascience.shop.dao.BasketDaoImpl;
import com.datascience.shop.dao.DaoException;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;

import java.util.ArrayList;

public class BasketService {
    private BasketDao basketDao;

    public BasketService(BasketDao basketDao) {
        this.basketDao = basketDao;
    }

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

    public void deleteFromBasketByItemId(Integer userId, Integer itemId) throws ServiceException {
        try {
            basketDao.deleteFromBasketByItemId(userId,itemId);
        } catch (DaoException e) {
            throw new ServiceException("Failed to save into database");
        }
    }

}
