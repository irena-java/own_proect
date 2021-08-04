package com.datascience.shop.service;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.dao.ItemDao;
import com.datascience.shop.entity.Item;

import java.util.List;

public class ItemService {

    private ItemDao itemDao=new ItemDao();

 /*   public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
*/
    public List<Item> findAll() throws DaoException {
        List<Item> items = itemDao.findAll();
        return items;
    }



    public Item findById(Integer id) throws ServiceException {
        try {
            return itemDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("");
        }
    }
}
