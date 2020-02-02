package DAO;

import model.User;
import util.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDAO implements DaoInterface {

    private DBHelper dbHelper = DBHelper.getInstance();

//    private DBHelper dbHelper;

    public UserJDBCDAO() {
//        dbHelper = DBHelper.getInstance();
    }

    @Override
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        try (Statement statement = dbHelper.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM user")) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
//                String role = resultSet.getString("role");
                list.add(new User(id, name, age));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
    }

    @Override
    public void addUser(User user) {
        try (PreparedStatement preparedStatement = dbHelper.getConnection().prepareStatement(
                "insert into user (name, age) values(?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getAge());
//            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(long id) {
        User user = new User();
        try (Statement statement = dbHelper.getConnection().createStatement()) {
            statement.execute("SELECT * FROM user WHERE id='" + id + "'");
            try (ResultSet result = statement.getResultSet()) {
                result.next();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setAge(result.getInt("age"));
//                user.setRole(result.getString("role"));
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUsersName(long id, String newName) {
        try (PreparedStatement preparedStatement = dbHelper.getConnection().prepareStatement
                ("UPDATE user SET name = '" + newName + "' WHERE id = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUsersAge(long id, int age) {
        try (PreparedStatement preparedStatement = dbHelper.getConnection().prepareStatement
                ("UPDATE user SET age = " + age + " WHERE id = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(long id) {
        try (Statement statement = dbHelper.getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM user WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try (Statement stmt = dbHelper.getConnection().createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS user (id bigint auto_increment," +
                    " name varchar(256), age int, primary key (id))");//","
//                    + " role varchar (256)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try (Statement stmt = dbHelper.getConnection().createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
