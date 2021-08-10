package com.datascience.shop.service;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private final ItemDao itemDao;
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public List<Item> findAll() throws ServiceException {
        try {
            List<Item> items = itemDao.findAll();
            return items == null ? new ArrayList<>() : items;
        } catch (DaoException e) {
            logger.error("Failed to find all items - DaoException" + e);
            throw new ServiceException("Failed to find all items" + e);
        }
    }

    public Item findById(Integer id) throws ServiceException {
        try {
            return itemDao.findById(id);
        } catch (DaoException e) {
            logger.error("Failed to find item by id  - DaoException" + e);
            throw new ServiceException("Failed to find item by id" + e);
        }
    }

    public void delete(Item item) throws ServiceException {
        if (item == null) {
            throw new ServiceException("Failed delete item");
        } else {
            try {
                itemDao.delete(item);
            } catch (DaoException e) {
                logger.error("Failed to delete item - DaoException");
                throw new ServiceException("Failed to delete item"+e);
            }
        }
    }
}