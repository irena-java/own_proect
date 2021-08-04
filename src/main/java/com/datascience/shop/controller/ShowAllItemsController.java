package com.datascience.shop.controller;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.dao.ItemDao;
import com.datascience.shop.entity.Item;
import com.datascience.shop.service.ItemService;
//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

    public class ShowAllItemsController implements Controller {

        private  ItemService itemService = new ItemService();

        @Override
        public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
            try {
                List<Item> items = itemService.findAll();

//                List<Integer> items = Arrays.asList(5,6,77,99);

                req.setAttribute("items", items);
                System.out.println(items.toString());

                return new ControllerResultDto("items");
            } catch (DaoException e) {
                return null;
            }
        }
    }
