package com.datascience.shop.controller;

import com.datascience.shop.MySpecialContext;
import com.datascience.shop.connectionPool.ConnectionPool;
import com.datascience.shop.dao.UserDaoImpl;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {
    private final UserService userService = new UserService(new UserDaoImpl());
    public static ConnectionPool connectionPool = MySpecialContext.get();
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String encryptedPassword = DigestUtils.sha256Hex(password);
        try {
            User user = userService.findByUserName(userName);
            if (user.getPassword().equals(encryptedPassword)) {
                req.setAttribute("user", user);
                HttpSession session = req.getSession();
                session.setAttribute("userId", user.getId());
                return new ControllerResultDto("profile");
            }
        } catch (ServiceException e) {
            logger.error("Failed executing LoginController" + e);
            return new ControllerResultDto("error-403");
        }
        return new ControllerResultDto("error-500");
    }
}