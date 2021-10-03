package com.datascience.shop.controller;

import com.datascience.shop.connection.pool.MySpecialContext;
import com.datascience.shop.connection.pool.ConnectionPoolImpl;
import com.datascience.shop.service.impl.BasketServiceImpl;
import com.datascience.shop.service.impl.ItemServiceImpl;
import com.datascience.shop.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public interface Controller {
//    UserServiceImpl USER_SERVICE_IMPL = new UserServiceImpl(new UserDaoImpl());
//    BasketServiceImpl BASKET_SERVICE_IMPL = new BasketServiceImpl(new BasketDaoImpl());
//    ItemServiceImpl ITEM_SERVICE_IMPL = new ItemServiceImpl(new ItemDaoImpl());

    //todo по всем константам вынести как у али в цветах в отд.класс
    String VIEW_ACCESS_DENIED ="error-403";
    String viewPageNotFound ="error-404";
    String viewServerError ="error-500";
    String viewBasket ="basket";
    String viewItems ="items";
    String viewLogin ="login";
    String viewProfile ="profile";
    String viewRates ="rates";
    String viewUsers ="users";

    String REQUEST_ATTRIBUTE_NAME_ITEM_ID ="itemId";
    String parameterUserId ="userId";
    String parameterUserName ="userName";
    String parameterPassword ="password";
    String parameterUser ="user";
    String parameterUsers ="users";
    String parameterItems ="items";
    String parameterBasket ="basket";
    String parameterRates ="rates";

//    UserServiceImpl USER_SERVICE_IMPL = new UserServiceImpl();
//    BasketServiceImpl BASKET_SERVICE_IMPL = new BasketServiceImpl();
//    ItemServiceImpl ITEM_SERVICE_IMPL = new ItemServiceImpl();
//    ConnectionPoolImpl CONNECTION_POOL_IMPL = MySpecialContext.get();
//    Connection connection = CONNECTION_POOL_IMPL.get();
//или это плохой вариант
    ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp);
}
