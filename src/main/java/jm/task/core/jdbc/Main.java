package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getGlobal();

    public static void main(String[] args) {
        System.out.println("Hello World!");
        UserDao userDao = new UserDaoJDBCImpl();

//        userDao.createUsersTable();
//        userDao.saveUser("Pavel", "Konstantionov", (byte) 23);
//        userDao.saveUser("Misha", "SecondName", (byte) 5);
//        userDao.saveUser("Sasha", "SecondName", (byte) 66);
//        userDao.removeUserById(3);
//        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
//        userDaoHibernate.createUsersTable();
//        userDaoHibernate.dropUsersTable();
//        userDaoHibernate.getAllUsers();
//        userDaoHibernate.cleanUsersTable();

        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();


            session.getTransaction().commit();
//            User user2 = new User("A", "B", (byte) 1);
//            session.save(user2);

        }
    }
}
