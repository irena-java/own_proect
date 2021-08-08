package com.datascience.shop.controller;

import com.datascience.shop.ConnectionPool;
import com.datascience.shop.MySpecialContext;
import com.datascience.shop.dao.BasketDaoImpl;
import com.datascience.shop.dao.DaoException;
import com.datascience.shop.dao.UserDaoImpl;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.Item;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShowAllUsersController implements Controller {
    private final UserService userService = new UserService(new UserDaoImpl());

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
                List<User> users = userService.findAll();
                req.setAttribute("users", users);
                return new ControllerResultDto("users");
            }
    }

