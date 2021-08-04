package com.datascience.shop.service;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.dao.UserDao;
import com.datascience.shop.entity.User;


public class UserService {

    private UserDao userDao = new UserDao();

    public User findByUserName(String username) {
        try {
            return userDao.findByUsername(username);
        } catch (DaoException e) {
            System.out.println("Failed to find");
            return null;
        }
    }
    public User findById(Integer id) {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            System.out.println("Failed to find");
            return null;
        }
    }

}
