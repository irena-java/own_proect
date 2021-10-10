package com.datascience.shop.controller;

import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter(REQUEST_ATTRIBUTE_USER_NAME);
        String password = req.getParameter(REQUEST_ATTRIBUTE_PASSWORD);
        String encryptedPassword = DigestUtils.sha256Hex(password);
        try {
            User user = ControllerFactory.userServiceImpl.findByUserName(userName);
            if (user.getPassword().equals(encryptedPassword)) {
                req.setAttribute(REQUEST_ATTRIBUTE_USER, user);
                HttpSession session = req.getSession();
                session.setAttribute(REQUEST_ATTRIBUTE_USER_ID, user.getId());
                return new ControllerResultDto(VIEW_PROFILE);
            }
        } catch (ServiceException e) {
            logger.error("Failed executing LoginController" + e);
            return new ControllerResultDto(VIEW_ACCESS_DENIED);
        }
        return new ControllerResultDto(VIEW_SERVER_ERROR);
    }
}