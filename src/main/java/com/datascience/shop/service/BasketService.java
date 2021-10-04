package com.datascience.shop.service;

import com.datascience.shop.dao.BasketDao;
import com.datascience.shop.dao.impl.BasketDaoImpl;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.impl.BasketServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
/**
 * The interface containing business logic when operating with the basket
 */


public interface BasketService {
    BasketDao basketDao=new BasketDaoImpl();
    Logger logger = LoggerFactory.getLogger(BasketServiceImpl.class);

    Basket createOrUpdate(Basket basket) throws ServiceException;
    Basket findOrCreateForUser(User user) throws ServiceException;
    void deleteFromBasketByItemId(Integer userId, Integer itemId) throws ServiceException;
    void deleteBasket(Basket basket) throws ServiceException;
//    void deleteBasket(Basket basket, Connection connection) throws ServiceException;
}
