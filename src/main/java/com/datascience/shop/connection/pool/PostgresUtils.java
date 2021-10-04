package com.datascience.shop.connection.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresUtils {
    private static final Logger logger = LoggerFactory.getLogger(PostgresUtils.class);

    /**
     *
     * @return physical connection to database. Parameters for connection to database are taking
     * from property-file
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        //todo сделать относит.ссылку на проперти
        File file = new File("C:/Users/Ira/IdeaProjects/irena.ownproject/src/main/resources/config.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String driver = properties.getProperty("db.driver");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("Failed to get connection. Failed class: PostgresUtils, failed metod: getConnection(). " + e);
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }
}