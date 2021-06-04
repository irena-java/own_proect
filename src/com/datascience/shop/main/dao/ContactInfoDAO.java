package com.datascience.shop.main.dao;

import com.datascience.shop.main.entity.ContactInfo;
import com.datascience.shop.main.utils.PostgresUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ContactInfoDAO {
    private static final String CONTACT_INFO_FIELD = "contact_person_name,phone,email";
    private static final String SELECT_ALL = "SELECT * FROM contact_info";
    private static final String SELECT_BY_ID = "SELECT * FROM contact_info where id=?";
    private static final String INSERT_SQL = "INSERT INTO contact_info (" + CONTACT_INFO_FIELD + ") VALUES (?,?,?)";

    public static List<ContactInfo> findAll() {
        LocalDate localDate;
        List<ContactInfo> contactInfos = new ArrayList<>();
        try (Connection connection = PostgresUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL);) {
            while (resultSet.next()) {
                ContactInfo contactInfo = new ContactInfo();
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

    public static ContactInfo findById(Integer id) throws DaoException {
        LocalDate localDate;
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ContactInfo contactInfo = new ContactInfo();
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

    public Integer create(ContactInfo contactInfo) throws DaoException {
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
}
