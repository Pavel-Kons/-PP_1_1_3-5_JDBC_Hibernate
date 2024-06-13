package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static Connection connection = null;

    // реализуйте настройку соеденения с БД
    private static Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;
        }
        String hostName = "localhost";
        String userName = "root";
        String password = "{tkkj4Djhkl";
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/new_schema";

        connection = DriverManager.getConnection(connectionURL, userName, password);
        return connection;
    }

    public static Statement createStatement() throws SQLException {
//        Statement statement = getConnection().createStatement();
        return getConnection().createStatement();
    }
}
