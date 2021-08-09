package com.datascience.shop.controller;

import com.datascience.shop.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProfileController implements Controller {

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        Integer userId = (Integer) req.getSession().getAttribute("userId");
        User user = null;
        req.setAttribute("username", user.getName());
        req.setAttribute("password", user.getPassword());
        return new ControllerResultDto("profile");
    }
}