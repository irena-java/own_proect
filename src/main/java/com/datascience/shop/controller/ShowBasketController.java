package com.datascience.shop.controller;

import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBasketController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(ShowBasketController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer userId = (Integer) req.getSession().getAttribute("userId");
            User user = ControllerFactory.userServiceImpl.findById(userId);
            Basket basket = ControllerFactory.basketServiceImpl.findOrCreateForUser(user);
            req.setAttribute(REQUEST_ATTRIBUTE_BASKET, basket);
            return new ControllerResultDto(VIEW_BASKET);
        } catch (ServiceException e) {
            logger.error("Failed executing ShowBasketController" + e);
            return new ControllerResultDto(VIEW_SERVER_ERROR);
        }
    }
}