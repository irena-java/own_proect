package com.datascience.shop.service.impl;

import com.datascience.shop.dao.impl.DaoException;
import com.datascience.shop.entity.Item;
import com.datascience.shop.service.ItemService;
import com.datascience.shop.service.ServiceException;

import java.util.ArrayList;
import java.util.List;


public class ItemServiceImpl implements ItemService {

    public List<Item> findAll() throws ServiceException {
        try {
            List<Item> items = itemDao.findAll();
            return items == null ? new ArrayList<>() : items;
        } catch (DaoException e) {
            logger.error("Failed to find all items." + e);
            throw new ServiceException("Failed to find all items." + e);
        }
    }

    public Item findById(Integer id) throws ServiceException {
        try {
            return itemDao.findById(id);
        } catch (DaoException e) {
            logger.error("Failed to find item by id =" + id + e);
            throw new ServiceException("Failed to find item by id =" + id + e);
        }
    }

    public void delete(Item item) throws ServiceException {
        if (item == null) {
            throw new ServiceException("Failed to delete item - item does not exist");
        } else {
            try {
                itemDao.delete(item);
            } catch (DaoException e) {
                logger.error("Failed to delete item:" + item.toString());
                throw new ServiceException("Failed to delete item" + item.toString() + e);
            }
        }
    }
}