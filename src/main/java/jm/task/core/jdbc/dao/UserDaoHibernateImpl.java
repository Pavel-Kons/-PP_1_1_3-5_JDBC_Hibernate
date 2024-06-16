package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {
    private static final Logger logger = Logger.getGlobal();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Util.sessionHibernate(session -> {
            NativeQuery nativeQuery = session.createNativeQuery("CREATE TABLE IF NOT EXISTS `new_schema`.`user` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `last_name` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT(3) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`));");
            nativeQuery.executeUpdate();
        });
    }

    @Override
    public void dropUsersTable() {
        Util.sessionHibernate(session -> {
            NativeQuery nativeQuery = session.createNativeQuery("DROP TABLE IF EXISTS user");
            nativeQuery.executeUpdate();
        });
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Util.sessionHibernate((session) -> {
            User user1 = new User(name, lastName, age);
            session.save(user1);
        });
    }
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
//        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
//             Session session = sessionFactory.getCurrentSession()) {
//
//            session.beginTransaction();
//            User user1 = new User(name, lastName, age);
//            session.save(user1);
//        }
//    }

    @Override
    public void removeUserById(long id) {
        Util.sessionHibernate((session) -> {
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            session.getTransaction().commit();
        });
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Util.sessionHibernate((session) -> {
            users.addAll(session.createCriteria(User.class).list());
            System.out.println(users);
//            for (long i = 1; i < 101; i++) {
//                User user = session.get(User.class, i);
//                if (user == null) {
//                    break;
//                }
//                users.add(user);
//            }
        });
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Util.sessionHibernate((session) -> {
            session.createQuery("delete from User").executeUpdate();
//            for (long i = 1; i < 101; i++) {
//                User user = session.get(User.class, i);
//                if (user != null) {
//                    System.out.println(user);
//                    session.remove(user);
//                }
//            }
            session.getTransaction().commit();
        });
    }
}
