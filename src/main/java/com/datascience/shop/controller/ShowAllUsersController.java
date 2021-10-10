package com.datascience.shop.controller;

import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllUsersController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(ShowAllUsersController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<User> users = ControllerFactory.userServiceImpl.findAll();
            req.setAttribute(REQUEST_ATTRIBUTE_USERS, users);
            return new ControllerResultDto(VIEW_USERS);
        } catch (ServiceException e) {
            logger.error("Failed executing ShowAllUsersController" + e);
            return new ControllerResultDto(VIEW_SERVER_ERROR);
        }
    }
}