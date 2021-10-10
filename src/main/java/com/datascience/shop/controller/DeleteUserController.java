package com.datascience.shop.controller;

import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteUserController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        Connection connection = null;
        try {
            Integer userId = Integer.parseInt(req.getParameter(REQUEST_ATTRIBUTE_USER_ID));
            User user = ControllerFactory.userServiceImpl.findById(userId);
            Basket basket = ControllerFactory.basketServiceImpl.findOrCreateForUser(user);
            try {
                connection = ControllerFactory.connectionPoolImpl.get();
                connection.setAutoCommit(false);
                ControllerFactory.basketServiceImpl.deleteBasket(basket);
                ControllerFactory.userServiceImpl.delete(user);
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
                    throw new ServiceException("Failed to set AutoCommit or close connection in DeleteUserController" + throwables);
                }
            }
            return new ControllerResultDto(VIEW_USERS, true);
        } catch (ServiceException exp) {
            logger.error("Failed before executing transaction in DeleteUserController" + exp);
            return new ControllerResultDto(VIEW_SERVER_ERROR);
        }
    }
}