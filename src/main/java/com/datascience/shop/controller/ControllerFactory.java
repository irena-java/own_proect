package com.datascience.shop.controller;

import com.datascience.shop.connection.pool.ConnectionPoolImpl;
import com.datascience.shop.connection.pool.Context;
import com.datascience.shop.service.impl.BasketServiceImpl;
import com.datascience.shop.service.impl.ItemServiceImpl;
import com.datascience.shop.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {
    private final Map<String, Controller> controllerMap = new HashMap<>();
    public static UserServiceImpl userServiceImpl = new UserServiceImpl();
    public static BasketServiceImpl basketServiceImpl = new BasketServiceImpl();
    public static ItemServiceImpl itemServiceImpl = new ItemServiceImpl();
    public static ConnectionPoolImpl connectionPoolImpl = Context.get();

    private void init() {
        controllerMap.put("GET/profile", new ShowPageController(Controller.VIEW_PROFILE));
        controllerMap.put("GET/login", new ShowPageController(Controller.VIEW_LOGIN));
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