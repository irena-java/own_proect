package com.datascience.shop.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {

    private Map<String, Controller> controllerMap = new HashMap<>();



    private void init() {
        controllerMap.put("GET/profile", new ShowPageController("profile"));
        controllerMap.put("GET/login", new ShowPageController("login"));
        controllerMap.put("GET/main", new ShowPageController("main"));
        controllerMap.put("GET/client", new ShowPageController("login"));
        controllerMap.put("GET/items", new ShowAllItemsController());
        controllerMap.put("GET/addToBasket", new AddBasketController());
        controllerMap.put("GET/basket", new ShowBasketController());
        controllerMap.put("GET/deleteFromBasket", new DeleteFromBasketController());
        controllerMap.put("GET/deleteUser", new DeleteUserController());
        controllerMap.put("GET/users", new ShowAllUsersController());
        controllerMap.put("GET/rates", new ShowRatesController());
        controllerMap.put("POST/login", new LoginController());
    }

    public Controller getController(HttpServletRequest request) {
        if (controllerMap.isEmpty()) {
            init();
        }
        return controllerMap.get(request.getMethod() + request.getPathInfo());
    }
}