package com.datascience.shop.dao;

import com.datascience.shop.dao.impl.DaoException;
import com.datascience.shop.entity.User;

import java.util.List;

/**
 * The interface for referring to the database when operating with the users
 */

public interface UserDao {

    Integer create(User user) throws DaoException;

    User findByUsername(String username) throws DaoException;

    User findById(Integer id) throws DaoException;

    void delete(User user) throws DaoException;

    List<User> findAll() throws DaoException;
}
