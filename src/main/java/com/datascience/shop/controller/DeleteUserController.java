package com.datascience.shop.controller;

import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;
import com.datascience.shop.service.impl.BasketServiceImpl;
import com.datascience.shop.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteUserController implements Controller {
//    private final UserService userService = new UserService(new UserDaoImpl());
//    private final BasketService basketService = new BasketService(new BasketDaoImpl());
//    private static UserService userServiceImpl = new UserServiceImpl();
//    private static BasketService basketServiceImpl = new BasketServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer userId = Integer.parseInt(req.getParameter(parameterUserId));
            User user =ControllerFactory.userServiceImpl.findById(userId);
            Basket basket =ControllerFactory.basketServiceImpl.findOrCreateForUser(user);
//            Connection connection = connectionPool.get();
            try {
                ControllerFactory.connection.setAutoCommit(false);
                ControllerFactory.basketServiceImpl.deleteBasket(basket);
//                BASKET_SERVICE_IMPL.deleteBasket(basket, connection);
                ControllerFactory.userServiceImpl.delete(user);
//                USER_SERVICE_IMPL.delete(user, connection);
                ControllerFactory.connection.commit();
            } catch (ServiceException | SQLException e) {
                logger.error("Failed transaction in DeleteUserController" + e);
                try {
                    ControllerFactory.connection.rollback();
                } catch (SQLException ex) {
                    logger.error("Failed rollback after failed transaction in DeleteUserController" + ex);
                    throw new ServiceException("failed to rollback connection after failed transaction in DeleteUserController");
                }
                throw new ServiceException("Failed transaction in DeleteUserController");
            } finally {
                try {
                    ControllerFactory.connection.setAutoCommit(true);
                    ControllerFactory.connection.close();
                } catch (SQLException throwables) {
                    logger.error("Failed to set AutoCommit or close connection in DeleteUserController" + throwables);
                    throw new ServiceException("Failed to set AutoCommit or close connection in DeleteUserController"+throwables);
                }
            }
            return new ControllerResultDto(viewUsers, true);
        } catch (ServiceException exp) {
            logger.error("Failed before executing transaction in DeleteUserController" + exp);
            return new ControllerResultDto(viewServerError);
        }
    }
}