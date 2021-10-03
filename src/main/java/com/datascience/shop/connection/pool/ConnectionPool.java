package com.datascience.shop.connection.pool;

/**
 * dfdfdfdf {@link ConnectionPoolImpl#init() init}
 * @exception ConnectionPoolException if you don't receive a connection
 **/
import java.sql.Connection;

public interface ConnectionPool {
    void init() throws ConnectionPoolException;
    Connection get() throws ConnectionPoolException;
    void addNewConnectionToQueue() throws ConnectionPoolException;
}
