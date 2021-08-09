package com.datascience.shop.dao;

import com.datascience.shop.entity.Basket;
import com.datascience.shop.entity.Item;
import com.datascience.shop.entity.User;
import com.datascience.shop.service.BasketDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.datascience.shop.controller.LoginController.connectionPool;

public class BasketDaoImpl implements BasketDao {
    private static final Logger logger = LoggerFactory.getLogger(BasketDaoImpl.class);
    private static final String INSERT_SQL = "INSERT INTO baskets(user_id, item_id) VALUES(?, ?)";
    private static final String DELETE_BASKET_SQL = "DELETE FROM baskets WHERE user_id = ?";
    private static final String DELETE_FROM_BASKET_SQL = "DELETE FROM baskets WHERE user_id = ? and item_id=?";
    private static final String FIND_BY_ID = "    SELECT " +
            "b.id as basket_id," +
            "u.id as user_id," +
            "u.name as user_name," +
            "u.client_inn as client_inn," +
            "u.contact_info as contact_info," +
            "u.password as password," +
            "c.id as country_id," +
            "c.country as country," +
            "r.user_role as user_role," +
            "i.id as item_id," +
            "s.data_science_section as data_science_section," +
            "d.data_science_direction as data_science_direction," +
            "j.job_type as job_type," +
            "i.start_date as start_date," +
            "i.deadline as deadLine," +
            "i.price as price " +
            "FROM baskets b " +
            "INNER JOIN users u on u.id = b.user_id " +
            "INNER JOIN countries c ON u.country_id=c.id " +
            "INNER JOIN user_roles r ON u.user_role_id=r.id " +
            "INNER JOIN items i on i.id = b.item_id " +
            "INNER JOIN data_science_sections s ON i.data_science_section_id=s.id " +
            "INNER JOIN data_science_directions d ON i.data_science_direction_id=d.id " +
            "INNER JOIN job_types j ON i.job_type_id=j.id " +
            "WHERE u.id = ?";

    public Basket insertOrUpdate(Basket basket) throws DaoException{
        BasketDaoImpl basketDaoImpl = new BasketDaoImpl();
        Basket basket1 = basketDaoImpl.findById(basket.getClient());
        if (basket1 != null) {
            deleteBasket(basket1);
        }
        try {
            for (Item item : basket.getItems()) {
                try (Connection connection = connectionPool.get();
                     PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
                    preparedStatement.setInt(1, basket.getClient().getId());
                    preparedStatement.setInt(2, item.getId());
                    preparedStatement.execute();
                }
            }
            return basket;
        } catch (SQLException e) {
            logger.error("Failed to insert or update basket, basketId=" + basket.getId() + e);
            throw new DaoException();
        }
    }

    public void deleteBasket(Basket basket, Connection connection) throws DaoException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BASKET_SQL)) {
            preparedStatement.setInt(1, basket.getClient().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Failed to delete basket, basketId=" + basket.getId() + e);
            throw new DaoException();
        }
    }

    public void deleteBasket(Basket basket) throws DaoException {
        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BASKET_SQL)) {
            preparedStatement.setInt(1, basket.getClient().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Failed to delete basket, basketId=" + basket.getId() + e);
            throw new DaoException();
        }
    }

    public void deleteFromBasketByItemId(Integer userId, Integer itemId) throws DaoException {
        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_BASKET_SQL)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, itemId);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Failed to delete from basket item, itemId=" + itemId + e);
           throw new DaoException();
        }
    }

    public Basket findById(User user) throws DaoException {
        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            Basket basket = new Basket(null, null, null);
            ArrayList<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                int basketId = resultSet.getInt("basket_id");
                basket.setId(basketId);
                int itemId = resultSet.getInt("item_id");
                String dataScienceSection = resultSet.getString("data_science_section");
                String dataScienceDirection = resultSet.getString("data_science_direction");
                String jobType = resultSet.getString("job_type");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                LocalDate deadline = resultSet.getDate("deadLine").toLocalDate();
                Double price = resultSet.getDouble("price");
                Item item = new Item(itemId, dataScienceSection, dataScienceDirection, jobType, startDate, deadline, price);
                items.add(item);
            }
            if (basket.getId() == null) {
                return null;
            }
            basket.setItems(items);
            basket.setClient(user);
            return basket;
        } catch (SQLException e) {
            logger.error("Failed to find basket by user, userId=" + user.getId() + e);
            throw new DaoException();
        }
    }
}