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

public class DeleteUserController implements Controller {
    private final UserService userService = new UserService(new UserDaoImpl());
    private final BasketService basketService = new BasketService(new BasketDaoImpl());

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer userId = Integer.parseInt(req.getParameter("userId"));
            User user = userService.findById(userId);
            Basket basket = basketService.findOrCreateForUser(user);


            ConnectionPool connectionPool = MySpecialContext.get();
            Connection connection = connectionPool.get();
            try {
                connection.setAutoCommit(false);
                basketService.deleteBasket(basket,connection);
                userService.delete(user,connection);
                connection.commit();
            } catch (ServiceException | SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new ServiceException("failed to rollback connection");
                }
                throw new ServiceException("failed to find");
            }        finally {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException throwables) {
                    throw new ServiceException("failed to rollback connection");
                }
            }
            return new ControllerResultDto("users", true);
        } catch (ServiceException e) {
            return new ControllerResultDto("error-500");
        }
    }
}

