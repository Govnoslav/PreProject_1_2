package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = """
                CREATE TABLE IF NOT EXISTS users (
                id bigint AUTO_INCREMENT,
                name varchar(255),
                last_name varchar(255),
                age tinyint,
                PRIMARY KEY(id)
                )
                """;
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(createTable);) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropTable = """
                DROP TABLE IF EXISTS Users
                """;
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(dropTable);) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String insertUser = """
                INSERT INTO Users(name, last_name, age)
                VALUES(?, ?, ?)
                """;
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(insertUser);) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String removeUserById = """
                DELETE FROM Users
                WHERE id = ?
                """;
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(removeUserById);) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        String selectAllUsers = """
                SELECT *
                FROM Users
                """;
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(selectAllUsers);) {

            List<User> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                                     resultSet.getString("last_name"),
                                     resultSet.getByte("age"));

                user.setId(resultSet.getLong("id"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return null;
    }

    public void cleanUsersTable() {
        String clearTable = """
                DELETE FROM Users
                """;
        try (Connection conn = Util.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(clearTable);) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
