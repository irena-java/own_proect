package com.datascience.shop.controller;

import com.datascience.shop.entity.User;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;
import com.datascience.shop.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {
//    private final UserService userService = new UserService(new UserDaoImpl());
//    public static ConnectionPool connectionPool = MySpecialContext.get();
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
//    private static UserService userServiceImpl=new UserServiceImpl();



    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter(parameterUserName);
        String password = req.getParameter(parameterPassword);
        String encryptedPassword = DigestUtils.sha256Hex(password);
        try {
            User user = ControllerFactory.userServiceImpl.findByUserName(userName);
            if (user.getPassword().equals(encryptedPassword)) {
                req.setAttribute(parameterUser, user);
                HttpSession session = req.getSession();
                session.setAttribute(parameterUserId, user.getId());
                return new ControllerResultDto(viewProfile);
            }
        } catch (ServiceException e) {
            logger.error("Failed executing LoginController" + e);
            return new ControllerResultDto(VIEW_ACCESS_DENIED);
        }
        return new ControllerResultDto(viewServerError);
    }

    public boolean validateName(String name){
        //todo
        return true;
    }
}