package com.datascience.shop.service.impl;

import com.datascience.shop.dao.impl.DaoException;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            logger.error("Failed to find all users (service)" + e);
            throw new ServiceException("Failed to find all users (service)" + e);
        }
    }

    public User findByUserName(String username) throws ServiceException {
        try {
            return userDao.findByUsername(username);
        } catch (DaoException e) {
            logger.error("Failed to find user by userName " + username + e);
            throw new ServiceException("Failed to find user by userName " + username + e);
        }
    }

    public User findById(Integer id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            logger.error("Failed to find user by id " + id + e);
            throw new ServiceException("Failed to find user by id " + id + e);
        }
    }

    public void delete(User user) throws ServiceException {
        if (user == null) {
            throw new ServiceException("Failed, user does not exist");
        } else {
            try {
                userDao.delete(user);
            } catch (DaoException e) {
                logger.error("Failed to delete user " + user.toString() + e);
                throw new ServiceException("Failed to delete user " + user.toString());
            }
        }
    }
}