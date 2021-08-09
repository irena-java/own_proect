package com.datascience.shop.controller;

import com.datascience.shop.dao.BasketDaoImpl;
import com.datascience.shop.dao.UserDaoImpl;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.Item;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

public class DeleteFromBasketController implements Controller {

    private final BasketService basketService = new BasketService(new BasketDaoImpl());
    private final UserService userService = new UserService(new UserDaoImpl());
    private static final Logger logger = LoggerFactory.getLogger(DeleteFromBasketController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer itemId = Integer.parseInt(req.getParameter("itemId"));
            Integer userId = (Integer) req.getSession().getAttribute("userId");
            basketService.deleteFromBasketByItemId(userId, itemId);
            User user = userService.findById(userId);
            Basket basket = deleteItemFromBasket(user, itemId);
            return new ControllerResultDto("basket", true);
        } catch (ServiceException e) {
            logger.error("Failed executing DeleteFromBasketController" + e);
            return new ControllerResultDto("error-500");
        }
    }

    private Basket deleteItemFromBasket(User user, Integer itemId) throws ServiceException {
        Basket basket = basketService.findOrCreateForUser(user);
        List<Item> items = basket.getItems();
        Iterator<Item> itemIterator = items.iterator();
        Item currentItem;
        while (itemIterator.hasNext()) {
            currentItem = itemIterator.next();
            if (currentItem.getId() == itemId) {
                itemIterator.remove();
            }
        }
        System.out.println(items);
        return new Basket(user, items);
    }
}
