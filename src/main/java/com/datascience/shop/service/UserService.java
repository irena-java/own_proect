package com.datascience.shop.service;

import com.datascience.shop.dao.UserDao;
import com.datascience.shop.dao.impl.UserDaoImpl;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public interface UserService {
    UserDao userDao = new UserDaoImpl();
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    List<User> findAll() throws ServiceException;
    User findByUserName(String username) throws ServiceException;
    User findById(Integer id) throws ServiceException;
    void delete(User user) throws ServiceException;
//    void delete(User user, Connection connection) throws ServiceException;
}
