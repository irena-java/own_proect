package com.datascience.shop.controller;

import com.datascience.shop.dao.BasketDaoImpl;
import com.datascience.shop.dao.ItemDaoImpl;
import com.datascience.shop.dao.UserDaoImpl;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.Item;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ItemService;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBasketController implements Controller {

    private final UserService userService = new UserService(new UserDaoImpl());
    private final BasketService basketService = new BasketService(new BasketDaoImpl());
    private static final Logger logger = LoggerFactory.getLogger(AddBasketController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String itemId = req.getParameter("itemId");
            ItemService itemService = new ItemService(new ItemDaoImpl());
            Item item = itemService.findById(Integer.parseInt(itemId));
            Integer userId = (Integer) req.getSession().getAttribute("userId");
            User user = userService.findById(userId);
            Basket basket = basketService.findOrCreateForUser(user);
            basket.getItems().add(item);
            basketService.createOrUpdate(basket);
            return new ControllerResultDto("items", true);
        } catch (ServiceException e) {
            logger.error("Failed executing AddBasketController" + e);
            return new ControllerResultDto("error-500");
        }
    }
}