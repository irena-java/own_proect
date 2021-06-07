package com.datascience.shop.main.service;

import com.datascience.shop.main.dao.DaoException;
import com.datascience.shop.main.dao.UserDao;
import com.datascience.shop.main.entity.User;


public class UserService {

    private UserDao userDao = new UserDao();

    public User getByUserName(String username) {
        try {
            return userDao.findByUsername(username);
        } catch (DaoException e) {
            System.out.println("Failed to find");
            return null;
        }
    }
}
