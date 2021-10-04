package com.datascience.shop.service.impl;

import com.datascience.shop.dao.BasketDao;
import com.datascience.shop.dao.impl.DaoException;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Class between classes of type Controller and DAO, what fill byzness-logics
 * For example, operarion deleting itgem from basket is beginning in cloe controllers,
 * then come to servise, ..
 */

public class BasketServiceImpl implements BasketService {
//    private final BasketDao basketDao;
//    private static final Logger logger = LoggerFactory.getLogger(BasketServiceImpl.class);

//    public BasketServiceImpl(BasketDao basketDao){
//        this.basketDao = basketDao;
//    }

    /**
     * @param basket,what you want to update
     * @return updated basket
     * @throws ServiceException
     */

    public Basket createOrUpdate(Basket basket) throws ServiceException {
        try {
            return basketDao.insertOrUpdate(basket);
        } catch (DaoException e) {
            logger.error("Failed create or update basket - DaoException" + e);
            throw new ServiceException("Failed createOrUpdate in class BasketService");
        }
    }

    public Basket findOrCreateForUser(User user) throws ServiceException {
        try {
            Basket basket = basketDao.findById(user);
   //         logger.error("ОНО -- - в слое сервисов" + basket.toString());
            return basket == null ? new Basket(null, user, new ArrayList<>()) : basket;
        } catch (DaoException  e) {
            logger.error("Failed create or update basket by user - DaoException" + e);
            throw new ServiceException("failed to find or create user");
        }
    }

    public void deleteFromBasketByItemId(Integer userId, Integer itemId) throws ServiceException {
        try {
            basketDao.deleteFromBasketByItemId(userId, itemId);
        } catch (DaoException e) {
            logger.error("Failed to delete from basket by itemId - DaoException" + e);
            throw new ServiceException("Failed deleteFromBasketByItemId");
        }
    }

    public void deleteBasket(Basket basket) throws ServiceException {
//    public void deleteBasket(Basket basket, Connection connection) throws ServiceException {
        try {
            basketDao.deleteBasket(basket);
//            basketDao.deleteBasket(basket, connection);
        } catch (DaoException e) {
            logger.error("Failed to delete basket - DaoException" + e);
            throw new ServiceException("Failed to delete basket"+e);
        }
    }
}
