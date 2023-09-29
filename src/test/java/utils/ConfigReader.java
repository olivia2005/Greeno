package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static Connection connection;

    static {
        try {
            // Load properties from config file
            FileInputStream fileInputStream = new FileInputStream("config.properties");
            properties = new Properties();
            properties.load(fileInputStream);

            // Set up database connection
            String dbUrl = "jdbc:mysql://" + properties.getProperty("dbHostname") + ":"
                    + properties.getProperty("dbPort") + "/" + properties.getProperty("dbSchema");
            String dbUser = properties.getProperty("dbUser");
            String dbPassword = properties.getProperty("dbPassword");

            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyValue(String key) {
        return properties.getProperty(key);
    }

    public static Connection getDatabaseConnection() {
        return connection;
    }
}
