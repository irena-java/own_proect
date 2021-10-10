package com.datascience.shop.controller;

import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProfileController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(ShowProfileController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer userId = (Integer) req.getSession().getAttribute(REQUEST_ATTRIBUTE_USER_ID);

        User user = null;
        try {
            user = ControllerFactory.userServiceImpl.findById(userId);
            req.setAttribute(REQUEST_ATTRIBUTE_USER_NAME, user.getName());
            req.setAttribute(REQUEST_ATTRIBUTE_PASSWORD, user.getPassword());
        } catch (ServiceException e) {
            logger.error("Failed in ShowProfileController" + e);
            e.printStackTrace();
        }
        return new ControllerResultDto(VIEW_PROFILE);
    }
}