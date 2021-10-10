package com.datascience.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * layer that returns the name of controller based on the combination
 * of the HTTP command and the URL
 */
public interface Controller {
    String VIEW_ACCESS_DENIED = "error-403";
    String VIEW_PAGE_NOT_FOUND = "error-404";
    String VIEW_SERVER_ERROR = "error-500";
    String VIEW_BASKET = "basket";
    String VIE_ITEMS = "items";
    String VIEW_LOGIN = "login";
    String VIEW_PROFILE = "profile";
    String VIEW_RATES = "rates";
    String VIEW_USERS = "users";

    String REQUEST_ATTRIBUTE_ITEM_ID = "itemId";
    String REQUEST_ATTRIBUTE_USER_ID = "userId";
    String REQUEST_ATTRIBUTE_USER_NAME = "userName";
    String REQUEST_ATTRIBUTE_PASSWORD = "password";
    String REQUEST_ATTRIBUTE_USER = "user";
    String REQUEST_ATTRIBUTE_USERS = "users";
    String REQUEST_ATTRIBUTE_ITEMS = "items";
    String REQUEST_ATTRIBUTE_BASKET = "basket";
    String REQUEST_ATTRIBUTE_RATES = "rates";

    ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp);
}
