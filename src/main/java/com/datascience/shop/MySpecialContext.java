package com.datascience.shop;

import com.datascience.shop.connectionPool.ConnectionPool;

public class MySpecialContext {

    private static ConnectionPool connectionPool;

    public static ConnectionPool get() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
            connectionPool.init();
        }
        return connectionPool;
    }

    public static void main(String[] args) {

    }
}
