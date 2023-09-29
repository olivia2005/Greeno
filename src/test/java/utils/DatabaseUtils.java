package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    public static String[] getLoginCredentials() {
        String[] credentials = new String[2];
        String query = "SELECT username, password FROM greeno_users WHERE id=1"; // Assuming you have a table 'users' with columns 'username' and 'password'

        Connection connection = ConfigReader.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                credentials[0] = resultSet.getString("username");
                credentials[1] = resultSet.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return credentials;
    }
}
