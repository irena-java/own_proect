package com.datascience.shop.service;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.dao.UserDaoImpl;
import com.datascience.shop.entity.User;

import java.sql.Connection;
import java.util.ConcurrentModificationException;
import java.util.List;


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

        public void delete(User user, Connection connection) {
            try {
                userDao.delete(user,connection);
            } catch (DaoException e) {
                System.out.println("Failed to delete user");
            }
        }

    public List<User> findAll() {
            return userDao.findAll();
    }

}
