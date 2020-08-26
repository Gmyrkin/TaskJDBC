package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;
    public UserDaoJDBCImpl(){

    }


    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable() {
        try (PreparedStatement stmt1 = connection.prepareStatement
                ("CREATE TABLE IF NOT EXISTS user" +
                "(id int auto_increment primary key," +
                "name varchar (64), " +
                "lastname varchar (64), " +
                "age int)")) {
            stmt1.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement stmt2 = connection.prepareStatement
                ("DROP TABLE IF EXISTS user")) {
            stmt2.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prstmt = connection.prepareStatement
                ("INSERT INTO user(name, lastname, age) VALUE (?,?,?)")) {
            prstmt.setString(1, name);
            prstmt.setString(2, lastName);
            prstmt.setInt(3, age);
            prstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement prstmt2 = connection.prepareStatement
            ("DELETE FROM user WHERE id = (?)")){
            prstmt2.setLong(1, id);
            prstmt2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement stmt3 = connection.prepareStatement("SELECT * FROM user")) {
            ResultSet resultSet = stmt3.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong((1)));
                user.setName(resultSet.getString((2)));
                user.setLastName(resultSet.getString((3)));
                user.setAge(resultSet.getByte((4)));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement stmt4 = connection.prepareStatement
                (("DELETE FROM user"))) {
            stmt4.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
