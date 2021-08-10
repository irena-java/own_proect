package com.datascience.shop.controller;

import com.datascience.shop.dao.BasketDaoImpl;
import com.datascience.shop.dao.UserDaoImpl;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

import static com.datascience.shop.controller.LoginController.connectionPool;

public class DeleteUserController implements Controller {
    private final UserService userService = new UserService(new UserDaoImpl());
    private final BasketService basketService = new BasketService(new BasketDaoImpl());
    private static final Logger logger = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer userId = Integer.parseInt(req.getParameter("userId"));
            User user = userService.findById(userId);
            Basket basket = basketService.findOrCreateForUser(user);
            Connection connection = connectionPool.get();
            try {
                connection.setAutoCommit(false);
                basketService.deleteBasket(basket, connection);
                userService.delete(user, connection);
                connection.commit();
            } catch (ServiceException | SQLException e) {
                logger.error("Failed transaction in DeleteUserController" + e);
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error("Failed rollback after failed transaction in DeleteUserController" + ex);
                    throw new ServiceException("failed to rollback connection after failed transaction in DeleteUserController");
                }
                throw new ServiceException("Failed transaction in DeleteUserController");
            } finally {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException throwables) {
                    logger.error("Failed to set AutoCommit or close connection in DeleteUserController" + throwables);
                    throw new ServiceException("Failed to set AutoCommit or close connection in DeleteUserController"+throwables);
                }
            }
            return new ControllerResultDto("users", true);
        } catch (ServiceException exp) {
            logger.error("Failed before executing transaction in DeleteUserController" + exp);
            return new ControllerResultDto("error-500");
        }
    }
}