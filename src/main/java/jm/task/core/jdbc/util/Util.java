package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

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

    public static void sessionHibernate(Consumer<Session> consumer) {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            consumer.accept(session);
        }
    }
}
