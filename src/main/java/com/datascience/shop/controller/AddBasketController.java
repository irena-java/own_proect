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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    public class AddBasketController implements Controller {

        private final UserService userService = new UserService(new UserDaoImpl());
//        private final ItemService itemService = new ItemService(new ItemDao());
        private final BasketService basketService = new BasketService(new BasketDaoImpl());

        @Override
        public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {

            try {
                String itemId = req.getParameter("itemId");

                ItemService itemService=new ItemService(new ItemDaoImpl());
                Item item = itemService.findById(Integer.parseInt(itemId));
//                Item item = ItemService.findById(Integer.parseInt(itemId));

                Integer userId = (Integer) req.getSession().getAttribute("userId");
//                req.getSession().setAttribute("userId", null);
                User user = userService.findById(userId);
                Basket basket = basketService.findOrCreateForUser(user);
                basket.getItems().add(item);
                basketService.createOrUpdate(basket);

                return new ControllerResultDto("items", true);
            } catch (ServiceException e) {
                return new ControllerResultDto("error-500");
            }
        }

    }