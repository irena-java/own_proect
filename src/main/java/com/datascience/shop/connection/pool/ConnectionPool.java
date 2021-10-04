package com.datascience.shop.connection.pool;

/**
 * layer that returns connection from the pool of connections
 * @exception ConnectionPoolException if you don't receive a connection
 **/
import java.sql.Connection;

public interface ConnectionPool {
    void init() throws ConnectionPoolException;
    Connection get() throws ConnectionPoolException;
    void addNewConnectionToQueue() throws ConnectionPoolException;
}
