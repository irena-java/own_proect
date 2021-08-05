package com.datascience.shop.service;

import com.datascience.shop.dao.DaoException;
import com.datascience.shop.entity.User;

import java.util.List;

public interface UserDao {

    Integer create(User user) throws DaoException;

    User findByUsername(String username) throws DaoException;

    User findById(Integer id) throws DaoException;

    void delete(User user) throws DaoException;

    List<User> findAll();
}
