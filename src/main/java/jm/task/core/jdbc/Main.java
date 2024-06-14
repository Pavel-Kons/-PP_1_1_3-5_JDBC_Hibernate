package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getGlobal();

    public static void main(String[] args) {
        System.out.println("Hello World!");
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
//        userDao.saveUser("Pavel", "Konstantionov", (byte) 23);
//        userDao.saveUser("Misha", "SecondName", (byte) 5);
//        userDao.saveUser("Sasha", "SecondName", (byte) 66);
//        userDao.removeUserById(3);
//        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
    }
}
