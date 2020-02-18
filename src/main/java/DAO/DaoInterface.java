package DAO;

import model.User;

import java.util.List;

public interface DaoInterface {

    List<User> getAllUser();

    void addUser(User user);

    User getUserById(long id);

    User getUserByName(String name);

    void updateUser (User user);

    void deleteUser(long id);

}
