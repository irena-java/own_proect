package com.datascience.shop;

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
