
package com.datascience.shop;

import com.datascience.shop.dao.*;
import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.Item;
import com.datascience.shop.entity.User;
import com.datascience.shop.entity.UserRole;
import com.datascience.shop.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Main {




    public static void main(String[] args)  {


        URL url = null;
        try {
            url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Scanner scanner = null;
        try {
            scanner = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (scanner.hasNext()) {
            stringBuffer.append(scanner.next());
        }
        String result = stringBuffer.toString();
        result = result.replaceAll("<[^>]*>", "");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Rates> ratesActual = null;
        try {
            ratesActual = Arrays.asList(objectMapper.readValue(result, Rates[].class));
            System.out.println(ratesActual.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

  /*      URL url = null;
        try {
            url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuffer stringBuffer = new StringBuffer();
            while (scanner.hasNext()) {
                stringBuffer.append(scanner.next());
            }
            String rresult = stringBuffer.toString();
            rresult = rresult.replaceAll("<[^>]*>", "");
            ObjectMapper objectMapper = new ObjectMapper();
        List<Rates> ratesActual = null;
        try {
            ratesActual = Arrays.asList(objectMapper.readValue(rresult, Rates[].class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(ratesActual.toString());

*/


        try {
/*                URL url=new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
                Scanner scanner=new Scanner(url.openStream());
                StringBuffer stringBuffer=new StringBuffer();
                while (scanner.hasNext()){
                    stringBuffer.append(scanner.next());
                }
            System.out.println(stringBuffer.toString());
            System.out.println("-----------------------");
                String result=stringBuffer.toString();
                        result=result.replaceAll("<[^>]*>","");
            String result1=result.replaceAll("\\[","");
            result1=result1.replaceAll("]","");
//            String s1="[gdfdfdf]h]";
  //          System.out.println(s1.replaceAll("\\[","ff"));
            System.out.println(result1);

            ObjectMapper objectMapper=new ObjectMapper();
            List<Rates> rr0=Arrays.asList(objectMapper.readValue(result,Rates[].class));
            System.out.println(rr0.toString());

         //   Map<String,Double> rrr=new ObjectMapper().readValue(result,HashMap.class);


            //Rates rates=objectMapper.readValue(result, Rates.class);
            //Map<String,Double> rr1=objectMapper.readValues(result,Map.class);

*/
            UserService userService=new UserService(new UserDaoImpl());
            User user777=userService.findById(9);
            BasketService basketService777=new BasketService(new BasketDaoImpl());

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





            ItemService itemService=new ItemService(new ItemDaoImpl());
            List<Item> items17=itemService.findAll();
            System.out.println(items17.toString());

        UserDaoImpl userDaoImpl =new UserDaoImpl();
        User user1= userDaoImpl.findById(8);



        BasketDaoImpl basketDaoImpl =new BasketDaoImpl();
        Basket basket1= basketDaoImpl.findById(user1);
//        System.out.println(basket1.toString());

        ItemDaoImpl itemDaoImpl =new ItemDaoImpl();
        List<Item> items=new ArrayList<>();
        items= itemDaoImpl.findAll();
        System.out.println(items.toString());

        System.out.println("---------------");
        Basket basket2=new Basket(5,user1,items);

        Basket basket3= basketDaoImpl.insertOrUpdate(basket2);
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



            Item item1= itemDaoImpl.findById(4);
            System.out.println(item1.toString());

           int i= itemDaoImpl.create(item);
           item.setId(i);
            itemDaoImpl.delete(item);

            System.out.println("getDataScienceSectionId- "+ itemDaoImpl.getDataScienceSectionId("COMPUTER_VISION"));
            System.out.println("AUTOMATION- "+ itemDaoImpl.getDataScienceDirectionId("AUTOMATION"));
            System.out.println("CORPORATE_TRAINING- "+ itemDaoImpl.getJobTypeId("CORPORATE_TRAINING"));


            System.out.println("Россия - "+ userDaoImpl.getCountryId("Россия"));
            System.out.println("админ - "+ userDaoImpl.getRoleId(UserRole.ADMIN));

            User user= userDaoImpl.findById(21);
            System.out.println(user.toString());
            user= userDaoImpl.findByUsername("ТОВ Рога и копыта 777 4");
            System.out.println(user.toString());
            // - уже тестила раньше userDaoImpl.delete(user);
            System.out.println(user.toString());

            List<User> userList= userDaoImpl.findAll();
            System.out.println();
            System.out.println();
            System.out.println(userList.toString());

        } catch (DaoException | ServiceException  e) {
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
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        int i = 0;
        try {
            i = userDaoImpl.create(user);
            System.out.println("ID = " + i);
            user.setId(i);
            System.out.println(user.toString());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return user;
    }



}


