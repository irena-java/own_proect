package com.datascience.shop.controller;

import com.datascience.shop.dao.ItemDaoImpl;
import com.datascience.shop.entity.Item;
import com.datascience.shop.service.ItemService;
import com.datascience.shop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllItemsController implements Controller {
    private ItemService itemService = new ItemService(new ItemDaoImpl());
    private static final Logger logger = LoggerFactory.getLogger(ShowAllItemsController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Item> items = itemService.findAll();
            req.setAttribute("items", items);
            return new ControllerResultDto("items");
        } catch (ServiceException e) {
            logger.error("Failed executing ShowAllItemsController" + e);
            return new ControllerResultDto("error-500");
        }
    }
}
