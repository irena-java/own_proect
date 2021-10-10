package com.datascience.shop.controller;

import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.Item;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBasketController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(AddBasketController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String itemId = req.getParameter(REQUEST_ATTRIBUTE_ITEM_ID);
            Item item = ControllerFactory.itemServiceImpl.findById(Integer.parseInt(itemId));
            Integer userId = (Integer) req.getSession().getAttribute(REQUEST_ATTRIBUTE_USER_ID);
            User user = ControllerFactory.userServiceImpl.findById(userId);
            Basket basket = ControllerFactory.basketServiceImpl.findOrCreateForUser(user);
            basket.getItems().add(item);
            ControllerFactory.basketServiceImpl.createOrUpdate(basket);
            return new ControllerResultDto(VIE_ITEMS, true);
        } catch (ServiceException e) {
            logger.error("Failed executing AddBasketController" + e);
            return new ControllerResultDto(VIEW_SERVER_ERROR);
        }
    }
}