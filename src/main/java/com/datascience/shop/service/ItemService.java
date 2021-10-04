package com.datascience.shop.service;

import com.datascience.shop.dao.ItemDao;
import com.datascience.shop.dao.impl.ItemDaoImpl;
import com.datascience.shop.entity.Item;
import com.datascience.shop.service.impl.ItemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
/**
 * The interface containing business logic when operating with the item
 */
public interface ItemService {
    ItemDao itemDao=new ItemDaoImpl();
    Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    List<Item> findAll() throws ServiceException;


    Item findById(Integer id) throws ServiceException;
    void delete(Item item) throws ServiceException;
}
