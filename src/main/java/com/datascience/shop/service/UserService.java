package com.datascience.shop.service;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.dao.UserDaoImpl;
import com.datascience.shop.entity.User;


public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

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
