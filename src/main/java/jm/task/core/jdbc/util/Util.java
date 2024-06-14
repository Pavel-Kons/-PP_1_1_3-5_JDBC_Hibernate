package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    // реализуйте настройку соеденения с БД
    public static Connection getConnection() throws SQLException {
        String hostName = "localhost";
        String userName = "root";
        String password = "{tkkj4Djhkl";
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/new_schema";

        return DriverManager.getConnection(connectionURL, userName, password);
    }

    public static Statement createStatement() throws SQLException {
        return getConnection().createStatement();
    }
}
