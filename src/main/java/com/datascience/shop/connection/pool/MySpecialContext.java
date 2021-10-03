package com.datascience.shop.connection.pool;

public class MySpecialContext {

    private static ConnectionPoolImpl connectionPoolImpl;

    public static ConnectionPoolImpl get() {
        if (connectionPoolImpl == null) {
            connectionPoolImpl = new ConnectionPoolImpl();
            connectionPoolImpl.init();
        }
        return connectionPoolImpl;
    }

    public static void main(String[] args) {

    }
}
