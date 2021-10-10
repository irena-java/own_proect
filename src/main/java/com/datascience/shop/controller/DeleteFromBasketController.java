package com.datascience.shop.controller;

import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.Item;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

public class DeleteFromBasketController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(DeleteFromBasketController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer itemId = Integer.parseInt(req.getParameter(REQUEST_ATTRIBUTE_ITEM_ID));
            Integer userId = (Integer) req.getSession().getAttribute(REQUEST_ATTRIBUTE_USER_ID);
            ControllerFactory.basketServiceImpl.deleteFromBasketByItemId(userId, itemId);
            User user =ControllerFactory.userServiceImpl.findById(userId);
            Basket basket = deleteItemFromBasket(user, itemId);
            return new ControllerResultDto(VIEW_BASKET, true);
        } catch (ServiceException e) {
            logger.error("Failed executing DeleteFromBasketController" + e);
            return new ControllerResultDto(VIEW_SERVER_ERROR);
        }
    }

    private Basket deleteItemFromBasket(User user, Integer itemId) throws ServiceException {
        Basket basket =ControllerFactory.basketServiceImpl.findOrCreateForUser(user);
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
