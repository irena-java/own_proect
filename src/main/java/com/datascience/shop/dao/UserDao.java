package com.datascience.shop.dao;

import com.datascience.shop.entity.User;
import com.datascience.shop.entity.UserRole;
import com.datascience.shop.utils.PostgresUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDao {
    private static final String USER_FIELD = "name,user_role_id,client_inn,country_id,contact_info,password";

    //    private static final String SELECT_ALL = "SELECT id," + USER_FIELD + " FROM users";
    private static final String SELECT_TEMPLATE =
            "SELECT u.id, " +
                    "u.name, " +
                    "r.user_role, " +
                    "u.client_inn, " +
                    "c.country, " +
                    "u.contact_info, " +
                    "u.password " +
                    "FROM users u " +
                    "LEFT JOIN countries c ON u.country_id=c.id " +
                    "LEFT JOIN user_roles r ON u.user_role_id=r.id";
    private static final String SELECT_ALL= SELECT_TEMPLATE +" ORDER BY u.id";
    private static final String SELECT_BY_ID = SELECT_TEMPLATE + " WHERE u.id=?";
    private static final String SELECT_BY_USER_NAME = SELECT_TEMPLATE + " WHERE u.name=?";
//    private static final String SELECT_BY_ID = "SELECT id, " + USER_FIELD + " FROM users WHERE id=?";

    private static final String INSERT_SQL = "INSERT INTO users (" + USER_FIELD + ") VALUES (?,?,?,?,?,?)";
    //todo - а пароль в insert как?
    private static final String GET_COUNTRY_ID_BY_NAME = "SELECT id FROM countries WHERE country=?";
    private static final String GET_ROLE_ID_BY_NAME = "SELECT id FROM user_roles WHERE user_role=?";

    public Integer create(User user) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            //preparedStatement.setInt(2, user.getUserRole().ordinal());
            preparedStatement.setInt(2, getRoleId(user.getUserRole()));
            preparedStatement.setString(3, user.getClientInn());
            if (getCountryId(user.getCountry()) != -1) {
                preparedStatement.setInt(4, getCountryId(user.getCountry()));
            } else {
                System.out.println("такой страны нет, нужно дозаполнить справочник стран");
                //TODO сделать insert  в таблицу стран
            }
            preparedStatement.setString(5, user.getContactInfo());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException();
        }
    }

    public int getCountryId(String country) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNTRY_ID_BY_NAME)) {
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

    public int getRoleId(UserRole userRole) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ROLE_ID_BY_NAME)) {
            preparedStatement.setString(1, userRole.name());  //todo проверить что работает корректно перевод из enum в String
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
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_NAME);
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                UserRole userRole = UserRole.valueOf(resultSet.getString(3));
                String clientInn = resultSet.getString(4);
                String country = resultSet.getString(5);
                String contactInfo = resultSet.getString(6);
                String password = resultSet.getString(7);
                return new User(userId, name, userRole, clientInn, country, contactInfo, password);
            }
            return null;
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new DaoException();
        }
    }


    public User findById(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                UserRole userRole = UserRole.valueOf(resultSet.getString(3));
                String clientInn = resultSet.getString(4);
                String country = resultSet.getString(5);
                String contactInfo = resultSet.getString(6);
                String password = resultSet.getString(7);
                return new User(userId, name, userRole, clientInn, country, contactInfo, password);
            }
            return null;
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new DaoException();
        }
    }

    public void delete(User user) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException();
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = PostgresUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL);) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setUserRole(UserRole.valueOf(resultSet.getString(3)));
                user.setClientInn(resultSet.getString(4));
                user.setCountry(resultSet.getString(5));
                user.setContactInfo(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

}