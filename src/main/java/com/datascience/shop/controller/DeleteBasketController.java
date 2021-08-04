package com.datascience.shop.controller;

import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.Item;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ItemService;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBasketController implements Controller {

     private final BasketService basketService = new BasketService();
    private final UserService userService = new UserService();

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Integer itemId = Integer.parseInt(req.getParameter("itemId"));
            Integer userId = (Integer) req.getSession().getAttribute("userId");
            basketService.deleteFromBasketByItemId(userId,itemId);

 /*           User user=userService.findById(userId);
            Basket basket = basketService.findOrCreateForUser(user);
            basket.getItems().remove(i)  remove(item);
            basketService.createOrUpdate(basket);
*/
            return new ControllerResultDto("basket", true);
        } catch (ServiceException e) {
            return new ControllerResultDto("error-500");
        }
    }

}

