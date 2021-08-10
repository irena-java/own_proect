package com.datascience.shop.mainTrash;

import com.datascience.shop.SimpleAnnotaion;
import com.datascience.shop.dao.DaoException;
import com.datascience.shop.utils.PostgresUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class oldContactInfoDAO {
    private static final String CONTACT_INFO_FIELD = "contact_person_name,phone,email";
    private static final String SELECT_ALL = "SELECT * FROM contact_info";
    private static final String SELECT_BY_ID = "SELECT * FROM contact_info WHERE id=?";
    private static final String INSERT_SQL = "INSERT INTO contact_info (" + CONTACT_INFO_FIELD + ") VALUES (?,?,?)";
    private static final String DELETE_SQL = "DELETE FROM contact_info WHERE id=?";


    public static List<oldContactInfo> findAll() {
        LocalDate localDate;
        List<oldContactInfo> contactInfos = new ArrayList<>();
        try (Connection connection = PostgresUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL);) {
            while (resultSet.next()) {
                oldContactInfo contactInfo = new oldContactInfo();
                contactInfo.setId(resultSet.getInt("id"));
                contactInfo.setContactPersonName(resultSet.getString("contact_person_name"));
                contactInfo.setPhone(resultSet.getString("phone"));
                contactInfo.setEmail(resultSet.getString("email"));
                if (resultSet.getDate("date_closed") != null) {
                    localDate = resultSet.getDate("date_closed").toLocalDate();
                    contactInfo.setClosedDate(localDate);
                }
                contactInfos.add(contactInfo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contactInfos;
    }

    public static oldContactInfo getById(Integer id) throws DaoException {
        LocalDate localDate;
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                oldContactInfo contactInfo = new oldContactInfo();
                contactInfo.setId(resultSet.getInt("id"));
                contactInfo.setContactPersonName(resultSet.getString("contact_person_name"));
                contactInfo.setPhone(resultSet.getString("phone"));
                contactInfo.setEmail(resultSet.getString("email"));
                if (resultSet.getDate("date_closed") != null) {
                    localDate = resultSet.getDate("date_closed").toLocalDate();
                    contactInfo.setClosedDate(localDate);
                }
                return contactInfo;
            }
        } catch (SQLException |
                ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SimpleAnnotaion(name = "")
    public Integer create(oldContactInfo contactInfo) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, contactInfo.getContactPersonName());
            preparedStatement.setString(2, contactInfo.getPhone());
            preparedStatement.setString(3, contactInfo.getEmail());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException();
        }
    }

    //@Override
    public void delete(oldContactInfo contactInfo) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, contactInfo.getId());

            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException();
        }
    }
}
