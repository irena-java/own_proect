package com.datascience.shop.service;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class UserService {
    private final UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error("Failed to executing findByUserName in class UserService-DaoException" + e);
            throw new ServiceException("Failed to executing findAll in class ItemService" + e);
        }
    }

    public User findByUserName(String username) throws ServiceException {
        try {
            return userDao.findByUsername(username);
        } catch (DaoException e) {
            logger.error("Failed to executing findByUserName in class UserService" + e);
            throw new ServiceException("Failed to executing findByUserName in class ItemService" + e);
        }
    }

    public User findById(Integer id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            logger.error("Failed to executing findById in class UserService-DAO" + e);
            throw new ServiceException("Failed to executing findById in class UserService" + e);
        }
    }

    public void delete(User user, Connection connection) throws ServiceException {
        if (user == null) {
            throw new ServiceException("fdf");
        } else {
            try {
                userDao.delete(user, connection);
            } catch (DaoException e) {
                logger.error("Failed to executing delete in class UserService-DAO" + e);
                throw new ServiceException("Failed to executing delete in class UserService");
            }
        }
    }
}

