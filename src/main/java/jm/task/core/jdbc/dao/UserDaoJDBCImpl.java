package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE users (id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(20) NOT NULL ," +
                    "lastName VARCHAR(30)NOT NULL," +
                    "age TINYINT NOT NULL )");
        } catch (Exception ignored) {}

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE users");
        } catch (Exception ignored) {}

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            throw new IllegalArgumentException();

        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalArgumentException ();
        }
    }


    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            for (User user1 : userList) {
                System.out.println(user1);
            }
            return userList;
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }

    }


    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM users");
        } catch (SQLException e) {
            throw new IllegalArgumentException();

        }
    }
}
