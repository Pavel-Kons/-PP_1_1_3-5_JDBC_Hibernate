package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    private static final Logger logger = Logger.getGlobal();

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS `new_schema`.`user` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `last_name` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";
        try (Statement statement = Util.createStatement()) {
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Failed to create table");
        }
    }

    @Override
    public void dropUsersTable() {
        String dropTableSQL = "DROP TABLE user";
        try (Statement statement = Util.createStatement()) {
            statement.executeUpdate(dropTableSQL);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Failed to drop table");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO `new_schema`.`user`(`name`,`last_name`,`age`)\n" +
                "VALUES\n" +
                "(?,?,?);";
        try (Statement statement = Util.createStatement();
             PreparedStatement preparedStatement = statement
                     .getConnection()
                     .prepareStatement(saveUser)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Failed to insert user");
        }
    }

    @Override
    public void removeUserById(long id) {
        String removeUser = "DELETE FROM `new_schema`.`user`\n" +
                "WHERE id=?;";
        try (Statement statement = Util.createStatement();
             PreparedStatement preparedStatement = statement
                     .getConnection()
                     .prepareStatement(removeUser)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warning("Failed remove user");
        }
    }

    @Override
    public List<User> getAllUsers() {
        String getAllUsersSQL = "SELECT * FROM `new_schema`.`user`";
        List<User> users = new ArrayList<>();
        try (Statement statement = Util.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllUsersSQL)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                byte age = resultSet.getByte("age");

                User user = new User();
                user.setAge(age);
                user.setLastName(lastName);
                user.setName(name);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.warning("Can't get all users");
        }
        System.out.println(users);
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = Util.createStatement()) {
            statement.executeUpdate("DELETE FROM user;");
        } catch (SQLException e) {
            logger.warning("Failed to clean table");
        }
    }
}
