package com.datascience.shop;

import com.datascience.shop.connection.pool.ConnectionPoolImpl;
import com.datascience.shop.connection.pool.MySpecialContext;
import com.datascience.shop.dao.BasketDao;
import com.datascience.shop.dao.UserDao;
import com.datascience.shop.dao.impl.BasketDaoImpl;
import com.datascience.shop.dao.impl.UserDaoImpl;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.UserService;
import com.datascience.shop.service.impl.BasketServiceImpl;
import com.datascience.shop.service.impl.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
        ConnectionPoolImpl connectionPoolImpl = MySpecialContext.get();
        UserDao userDao=new UserDaoImpl();
        UserService USER_SERVICE_IMPL=new UserServiceImpl();
        BasketServiceImpl BASKET_SERVICE_IMPL = new BasketServiceImpl();
//        User rrr=new User();
//        rrr.setId(62);
        BasketDao basketDao=new BasketDaoImpl();
        try {
//            Basket basket=basketDao.findById(rrr);
            User user = USER_SERVICE_IMPL.findById(80);
            Basket basket = BASKET_SERVICE_IMPL.findOrCreateForUser(user);
            BASKET_SERVICE_IMPL.deleteBasket(basket);
//            BASKET_SERVICE_IMPL.deleteBasket(basket, connectionPool.get());
            USER_SERVICE_IMPL.delete(user);
//            USER_SERVICE_IMPL.delete(user,  connectionPool.get());
            System.out.println(basket.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
