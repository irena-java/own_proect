package com.datascience.shop.dao.impl;

import com.datascience.shop.controller.ControllerFactory;
import com.datascience.shop.dao.ItemDao;
import com.datascience.shop.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);
    private static final String GET_DATA_SCIENCE_SECTION_ID_BY_NAME =
            "SELECT id FROM data_science_sections WHERE data_science_section=?";
    private static final String GET_DATA_SCIENCE_DIRECTION_ID_BY_NAME =
            "SELECT id FROM data_science_directions WHERE data_science_direction=?";
    private static final String GET_JOB_TYPES_ID_BY_NAME =
            "SELECT id FROM job_types WHERE job_type=?";
    private static final String USER_FIELD =
            "data_science_section_id,data_science_direction_id,job_type_id," +
                    "start_date,deadline,price";
    private static final String INSERT_SQL =
            "INSERT INTO items (" + USER_FIELD + ") VALUES (?,?,?,?,?,?)";
    private static final String SELECT_TEMPLATE =
            "SELECT i.id, " +
                    "s.data_science_section, " +
                    "d.data_science_direction, " +
                    "j.job_type, " +
                    "i.start_date, " +
                    "i.deadline, " +
                    "i.price " +
                    "FROM items i " +
                    "LEFT JOIN data_science_sections s ON i.data_science_section_id=s.id " +
                    "LEFT JOIN data_science_directions d ON i.data_science_direction_id=d.id " +
                    "LEFT JOIN job_types j ON i.job_type_id=j.id";
    private static final String SELECT_ALL = SELECT_TEMPLATE + " ORDER BY i.id";
    private static final String SELECT_BY_ID = SELECT_TEMPLATE + " WHERE i.id=?";

    public int getDataScienceSectionId(String dataScienceSection) throws DaoException {
        try (
                Connection connection = ControllerFactory.connectionPoolImpl.get();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(GET_DATA_SCIENCE_SECTION_ID_BY_NAME)) {
            preparedStatement.setString(1, dataScienceSection);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            logger.error("Failed getDataScienceSectionId" + dataScienceSection + e);
            throw new DaoException("Failed getDataScienceSectionId" + dataScienceSection + e);
        }
    }

    public int getDataScienceDirectionId(String dataScienceDirection) throws DaoException {
        try (
                Connection connection = ControllerFactory.connectionPoolImpl.get();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(GET_DATA_SCIENCE_DIRECTION_ID_BY_NAME)) {
            preparedStatement.setString(1, dataScienceDirection);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            logger.error("Failed to execute getDataScienceDirectionId " +
                    dataScienceDirection + e);
            throw new DaoException("Failed to execute getDataScienceDirectionId "
                    + dataScienceDirection + e);
        }
    }

    public int getJobTypeId(String jobType) throws DaoException {
        try (
                Connection connection = ControllerFactory.connectionPoolImpl.get();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(GET_JOB_TYPES_ID_BY_NAME)) {
            preparedStatement.setString(1, jobType);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            logger.error("Failed to execute getJobTypeId" + jobType + e);
            throw new DaoException("Failed to execute getJobTypeId" +jobType + e);
        }
    }

    public Integer create(Item item) throws DaoException {
        try (
                Connection connection = ControllerFactory.connectionPoolImpl.get();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, getDataScienceSectionId(item.getDataScienceSection()));
            preparedStatement.setInt(2, getDataScienceDirectionId(item.getDataScienceDirection()));
            preparedStatement.setInt(3, getJobTypeId(item.getJobType()));
            preparedStatement.setDate(4, Date.valueOf(item.getStartDate()));
            preparedStatement.setDate(5, Date.valueOf(item.getDeadline()));
            preparedStatement.setDouble(6, item.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            logger.error("Failed to create new item " +item.toString() + e);
            throw new DaoException("Failed to create new item" + item.toString() + e);
        }
    }

    public void delete(Item item) throws DaoException {
        try (
                Connection connection = ControllerFactory.connectionPoolImpl.get();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("DELETE FROM items WHERE id=?")) {
            preparedStatement.setInt(1, item.getId());
            preparedStatement.execute();
            logger.debug("Successfully deleted item with Id = " + item.getId());
        } catch (SQLException e) {
            logger.error("Failed to delete item, itemId=" + item.getId() + e);
            throw new DaoException("Failed to delete item"+item.toString()+e);
        }
    }

    public List<Item> findAll() throws DaoException {
        List<Item> items = new ArrayList<>();
        try (
                Connection connection = ControllerFactory.connectionPoolImpl.get();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setDataScienceSection(resultSet.getString(2));
                item.setDataScienceDirection(resultSet.getString(3));
                item.setJobType(resultSet.getString(4));
                item.setStartDate(resultSet.getDate(5).toLocalDate());
                item.setDeadline(resultSet.getDate(6).toLocalDate());
                item.setPrice(resultSet.getDouble(7));
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Failed to get all item." + e);
            throw new DaoException("Failed to get all item." + e);
        }
        return items;
    }

    public Item findById(Integer id) throws DaoException {
        try (
                Connection connection = ControllerFactory.connectionPoolImpl.get();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(SELECT_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setDataScienceSection(resultSet.getString(2));
                item.setDataScienceDirection(resultSet.getString(3));
                item.setJobType(resultSet.getString(4));
                item.setStartDate(resultSet.getDate(5).toLocalDate());
                item.setDeadline(resultSet.getDate(6).toLocalDate());
                item.setPrice(resultSet.getDouble(7));
                return item;
            }
            return null;
        } catch (SQLException e) {
            logger.error("Failed to find item by Id, itemId=" + id + e);
            throw new DaoException("Failed to find item by Id, itemId=" + id + e);
        }
    }
}