package com.datascience.shop.main.dao;

import com.datascience.shop.main.entity.User;
import com.datascience.shop.main.utils.PostgresUtils;

import java.sql.*;

public class UserDao {
    private static final String USER_FIELD = "name,user_role_id,client_inn,country,contact_info_id";
    private static final String SELECT_ALL = "SELECT " + USER_FIELD + " FROM users";
    private static final String SELECT_BY_ID = "SELECT " + USER_FIELD + " FROM users WHERE id=?";
    private static final String INSERT_SQL = "INSERT INTO users (" + USER_FIELD + ") VALUES (?,?,?,?,?)";
    private static final String GET_ID_BY_COUNTRY_NAME = "SELECT id FROM countries WHERE country=?";

    public Integer create(User user) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getUserRole().ordinal());
            preparedStatement.setString(3, user.getClientInn());
            preparedStatement.setInt(5, user.getContactInfo().getId());
            if (getCountryId(user.getCountry()) != -1) {
                preparedStatement.setInt(4, getCountryId(user.getCountry()));
            } else {
                System.out.println("такой страны нет, нужно дозаполнить справочник стран");
                //TODO сделать insert  в таблицу стран
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException();
        }
    }

    public static int getCountryId(String country) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_BY_COUNTRY_NAME)) {
            preparedStatement.setString(1, country);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException();
        }
    }


    public User findByUsername(String username) throws DaoException {
        try (
                Connection connection = PostgresUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "select id, name, password from users where name = ?");
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String usernameField = resultSet.getString(2);
                String password = resultSet.getString(3);

                return new User(id, usernameField, password);
            }

            return null;
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new DaoException();
        }
    }
}