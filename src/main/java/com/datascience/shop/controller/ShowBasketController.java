package com.datascience.shop.controller;

import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowBasketController implements Controller {

    private UserService userService = new UserService();
    private BasketService basketService = new BasketService();

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer userId = (Integer) req.getSession().getAttribute("userId");
            User user = userService.findById(userId);
            Basket basket = basketService.findOrCreateForUser(user);
            req.setAttribute("basket", basket);
            return new ControllerResultDto("basket");
        } catch (ServiceException e) {
            return new ControllerResultDto("error-500");
        }
    }
}