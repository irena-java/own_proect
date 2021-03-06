package com.datascience.shop.service.impl;

import com.datascience.shop.dao.impl.DaoException;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ServiceException;

import java.util.ArrayList;

/**
 * Class between classes of type Controller and DAO, what fill byzness-logics
 * For example, operation deleting itgem from basket is beginning in cloe controllers,
 * then come to service, ..
 */

public class BasketServiceImpl implements BasketService {

    /**
     * @param basket,what you want to update
     * @return updated basket
     * @throws ServiceException
     */
    public Basket createOrUpdate(Basket basket) throws ServiceException {
        try {
            return basketDao.insertOrUpdate(basket);
        } catch (DaoException e) {
            logger.error("Failed create or update basket:" + basket.toString() + e);
            throw new ServiceException("Failed createOrUpdate basket" + basket.toString());
        }
    }

    public Basket findOrCreateForUser(User user) throws ServiceException {
        try {
            Basket basket = basketDao.findById(user);
            return basket == null ? new Basket(null, user, new ArrayList<>()) : basket;
        } catch (DaoException e) {
            logger.error("Failed create or update basket by user:" + user.toString() + e);
            throw new ServiceException("failed to find or create user" + user.toString());
        }
    }

    public void deleteFromBasketByItemId(Integer userId, Integer itemId) throws ServiceException {
        try {
            basketDao.deleteFromBasketByItemId(userId, itemId);
        } catch (DaoException e) {
            logger.error("Failed to delete from basket by itemId =" + itemId + e);
            throw new ServiceException("Failed deleteFromBasketByItemId");
        }
    }

    public void deleteBasket(Basket basket) throws ServiceException {
        try {
            basketDao.deleteBasket(basket);
        } catch (DaoException e) {
            logger.error("Failed to delete basket:" + basket.toString() + e);
            throw new ServiceException("Failed to delete basket" + basket.toString() + e);
        }
    }
}
