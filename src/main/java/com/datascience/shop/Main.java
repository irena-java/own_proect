package com.datascience.shop;

import com.datascience.shop.controller.DeleteBasketController;
import com.datascience.shop.dao.*;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.Item;
import com.datascience.shop.main.oldContactInfo;
import com.datascience.shop.entity.User;
import com.datascience.shop.entity.UserRole;
import com.datascience.shop.service.BasketService;
import com.datascience.shop.service.ItemService;
import com.datascience.shop.service.ServiceException;
import com.datascience.shop.service.UserService;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.datascience.shop.dao.ItemDao.*;

public class Main {

    public static void main(String[] args) {
        try {
            UserService userService=new UserService();
            User user777=userService.findById(9);
            BasketService basketService777=new BasketService();

            Basket basket=basketService777.findOrCreateForUser(user777);//из нее надо удалить итем

            List<Item> items777=basket.getItems(); //из  этого списка надо удалить
            System.out.println(items777.toString());

            Iterator<Item> itemIterator=items777.iterator();
            Item currentItem;
            while(itemIterator.hasNext()){
                currentItem=itemIterator.next();
                if(currentItem.getId()==3) {
                    itemIterator.remove();
                }
            }
            System.out.println(items777.toString());





            ItemService itemService=new ItemService();
            List<Item> items17=itemService.findAll();
            System.out.println(items17.toString());

        UserDao userDao=new UserDao();
        User user1=userDao.findById(8);



        BasketDao basketDao=new BasketDao();
        Basket basket1=basketDao.findById(user1);
//        System.out.println(basket1.toString());

        ItemDao itemDao=new ItemDao();
        List<Item> items=new ArrayList<>();
        items=itemDao.findAll();
        System.out.println(items.toString());

        System.out.println("---------------");
        Basket basket2=new Basket(5,user1,items);

        Basket basket3= basketDao.insertOrUpdate(basket2);
            System.out.println(basket3);

        LocalDate l1=LocalDate.of(2021,8,2);
        System.out.println(Date.valueOf(l1));



       LocalDate startDate=        LocalDate.of(2010,5,21);
        LocalDate endDate=        LocalDate.of(2025,1,1);
        Item item = new Item("COMPUTER_VISION",
                "AUTOMATION",
                "CORPORATE_TRAINING",
                startDate,
                 endDate,
                15000.02);



            Item item1=itemDao.findById(4);
            System.out.println(item1.toString());

           int i=itemDao.create(item);
           item.setId(i);
            itemDao.delete(item);

            System.out.println("getDataScienceSectionId- "+itemDao.getDataScienceSectionId("COMPUTER_VISION"));
            System.out.println("AUTOMATION- "+itemDao.getDataScienceDirectionId("AUTOMATION"));
            System.out.println("CORPORATE_TRAINING- "+itemDao.getJobTypeId("CORPORATE_TRAINING"));


            System.out.println("Россия - "+userDao.getCountryId("Россия"));
            System.out.println("админ - "+userDao.getRoleId(UserRole.ADMIN));

            User user=userDao.findById(21);
            System.out.println(user.toString());
            user=userDao.findByUsername("ТОВ Рога и копыта 777 4");
            System.out.println(user.toString());
            userDao.delete(user);
            System.out.println(user.toString());

            List<User> userList=userDao.findAll();
            System.out.println();
            System.out.println();
            System.out.println(userList.toString());

        } catch (DaoException | ServiceException e) {
            e.printStackTrace();
        }


        User user = add_user("ТОВ Рога и копыта 777", UserRole.MANAGER, "54546543254", "Белоруссия", "contactInfo55555","fgfgfg");


/*
        try {
            System.out.println("findById(5)=" + oldContactInfoDAO.getById(5));
        } catch (DaoException e) {
            e.printStackTrace();
        }

        System.out.println("Все записи - " + oldContactInfoDAO.findAll());

        oldContactInfo contactInfo = add_contact("Филатова Галина Витальевна", "0635041713", "galina@ukr.net");
*/
    }

/*    private static oldContactInfo add_contact(String personName, String phone, String email) {
        oldContactInfo contactInfo = new oldContactInfo(personName, phone, email);
        oldContactInfoDAO oldContactInfoDAO = new oldContactInfoDAO();
        int i = 0;
        try {
            i = oldContactInfoDAO.create(contactInfo);
            System.out.println("вставлена запись с ID = " + i);
            contactInfo.setId(i);
            System.out.println("объект с Id-шкой "+contactInfo.toString());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return contactInfo;
    }
*/
    private static User add_user(String name, UserRole userRole, String clientInn, String country, String contactInfo, String password) {
        User user = new User(name, userRole, clientInn, country, contactInfo, password);
        UserDao userDao = new UserDao();
        int i = 0;
        try {
            i = userDao.create(user);
            System.out.println("ID = " + i);
            user.setId(i);
            System.out.println(user.toString());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return user;
    }



}


