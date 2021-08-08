package com.datascience.shop.service;

import com.datascience.shop.ConnectionPool;
import com.datascience.shop.MySpecialContext;
import com.datascience.shop.dao.DaoException;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
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
//        ConnectionPool connectionPool = MySpecialContext.get();
  //      Connection connection = connectionPool.get();
        //Basket basket=null;
        try {
        //    connection.setAutoCommit(false);
            Basket basket= basketDao.findById(user);
//            connection.commit();

            return basket == null ? new Basket(null, user, new ArrayList<>()) : basket;
        } catch (DaoException | SQLException e) {
                throw new ServiceException("failed to find");
            }
        }


    public void deleteFromBasketByItemId(Integer userId, Integer itemId) throws ServiceException {
        try {
            basketDao.deleteFromBasketByItemId(userId, itemId);
        } catch (DaoException e) {
            throw new ServiceException("Failed to save into database");
        }
    }


    public void deleteBasket(Basket basket,Connection connection) throws ServiceException {
        try {
            basketDao.deleteBasket(basket,connection);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete basket");
        }
    }

}
