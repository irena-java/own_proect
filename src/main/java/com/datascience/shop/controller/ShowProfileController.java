package com.datascience.shop.controller;

import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProfileController implements Controller {

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer userId = (Integer) req.getSession().getAttribute(parameterUserId);

        User user = null;
        try {
            user = ControllerFactory.userServiceImpl.findById(userId);
            req.setAttribute(parameterUserName, user.getName());
            req.setAttribute(parameterPassword, user.getPassword());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ControllerResultDto(viewProfile);
    }
}