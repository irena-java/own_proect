package com.datascience.shop.main.service;

import com.datascience.shop.main.dao.ContactInfoDAO;
import com.datascience.shop.main.dao.DaoException;
import com.datascience.shop.main.dao.UserDao;
import com.datascience.shop.main.entity.ContactInfo;
import com.datascience.shop.main.entity.User;
import com.datascience.shop.main.entity.UserRole;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("findById(5)=" + ContactInfoDAO.findById(5));
        } catch (DaoException e) {
            e.printStackTrace();
        }

        System.out.println("Все записи - " + ContactInfoDAO.findAll());

        ContactInfo contactInfo = add_contact("Филатова Галина Витальевна", "0635041713", "galina@ukr.net");
        User user = add_user("ТОВ Рога и копыта 777", UserRole.MANAGER, "54546543254", "Белоруссия", contactInfo);
    }

    private static ContactInfo add_contact(String personName, String phone, String email) {
        ContactInfo contactInfo = new ContactInfo(personName, phone, email);
        ContactInfoDAO contactInfoDAO = new ContactInfoDAO();
        int i = 0;
        try {
            i = contactInfoDAO.create(contactInfo);
            System.out.println("вставлена запись с ID = " + i);
            contactInfo.setId(i);
            System.out.println("объект с Id-шкой "+contactInfo.toString());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return contactInfo;
    }

    private static User add_user(String name, UserRole userRole, String clientInn, String country, ContactInfo contactInfo) {
        User user = new User(name, userRole, clientInn, country, contactInfo);
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


