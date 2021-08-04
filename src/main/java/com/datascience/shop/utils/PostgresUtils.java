package com.datascience.shop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresUtils {
    private static  final String URL="jdbc:postgresql://localhost:5432/irena_psql";
    private static  final String USER="postgres";
    private static  final String PASSWORD="Nbv29062006";
    private static  final String DB_DRIVER="org.postgresql.Driver";

    public static Connection getConnection() throws SQLException,ClassNotFoundException {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);


}
}
