package DAO;

import model.User;
import util.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoJDBC implements DaoInterface {

    public DaoJDBC() {
    }

    @Override
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        try (Statement statement = DBHelper.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM user")) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                String role = resultSet.getString("role");
                list.add(new User(id, name, password, age, role));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
    }

    @Override
    public void addUser(User user) {
        try (PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement(
                "insert into user (name, password, age, role) values(?, ?, ?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getAge());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(long id) {
        User user = new User();
        try (Statement statement = DBHelper.getConnection().createStatement()) {
            statement.execute("SELECT * FROM user WHERE id='" + id + "'");
            try (ResultSet result = statement.getResultSet()) {
                result.next();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setPassword(result.getString("password"));
                user.setAge(result.getInt("age"));
                user.setRole(result.getString("role"));
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByName(String name) {
        try (Statement statement = DBHelper.getConnection().createStatement()) {
            statement.execute("SELECT * FROM user WHERE name ='" + name + "'");
            try (ResultSet result = statement.getResultSet()) {
                if (result.next()) {
                    return new User(result.getLong("id"),
                            result.getString("name"),
                            result.getString("password"),
                            result.getInt("age"),
                            result.getString("role"));
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }



    @Override
    public void updateUser(User user) {
        try (PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement
                ("UPDATE user SET " +
                        "name = '" + user.getName() + "', " +
                        "password = '" + user.getPassword() + "', " +
                        "age = '" + user.getAge() + "', " +
                        "role = '" + user.getRole() + "' " +
                        "WHERE id = '" + user.getId() + "'")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(long id) {
        try (Statement statement = DBHelper.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM user WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try (Statement stmt = DBHelper.getConnection().createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS user (id bigint auto_increment," +
                    " name varchar(256), password varchar(256), age int, role varchar (256), primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try (Statement stmt = DBHelper.getConnection().createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
