package com.datascience.shop.controller;

import com.datascience.shop.dao.BasketDaoImpl;
import com.datascience.shop.dao.UserDaoImpl;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBasketController implements Controller {
    private final UserService userService = new UserService(new UserDaoImpl());
    private final BasketService basketService = new BasketService(new BasketDaoImpl());
    private static final Logger logger = LoggerFactory.getLogger(ShowBasketController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer userId = (Integer) req.getSession().getAttribute("userId");
            User user = userService.findById(userId);
            Basket basket = basketService.findOrCreateForUser(user);
            req.setAttribute("basket", basket);
            return new ControllerResultDto("basket");
        } catch (ServiceException e) {
            logger.error("Failed executing ShowBasketController" + e);
            return new ControllerResultDto("error-500");
        }
    }
}