package com.datascience.shop.service.impl;

import com.datascience.shop.dao.UserDao;
import com.datascience.shop.dao.impl.DaoException;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
//    private final UserDao userDao;
//    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

//    public UserServiceImpl(UserDao userDao) {
//        this.userDao = userDao;
//    }

    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error("Failed to find all users - DaoException" + e);
            throw new ServiceException("Failed to find all users" + e);
        }
    }

    public User findByUserName(String username) throws ServiceException {
        try {
            return userDao.findByUsername(username);
        } catch (DaoException e) {
            logger.error("Failed to find user by userName (failed method findByUserName(): DaoException, catch in class UserService. " + e);
            throw new ServiceException("Failed to find user by userName (failed method findByUserName(): DaoException, catch in class UserService. " + e);
        }
    }

    public User findById(Integer id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            logger.error("Failed to find user by id - DaoException" + e);
            throw new ServiceException("Failed to find user by id " + e);

        }
    }

    public void delete(User user) throws ServiceException {
//    public void delete(User user, Connection connection) throws ServiceException {
        if (user == null) {
            throw new ServiceException("fdf");
        } else {
            try {
                //userDao.delete(user, connection);
                userDao.delete(user);
            } catch (DaoException e) {
                logger.error("Failed to delete user "+ user.getName() + e);
                throw new ServiceException("Failed to delete user");
            }
        }
    }
}

