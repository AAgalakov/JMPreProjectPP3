package DAO;

import model.User;

import java.util.List;

public interface DaoInterface {

    List<User> getAllUser();

    void addUser(User user);

    User getUserById(long id);

    void updateUsersName(long id, String newName);

    void updateUsersAge(long id, int age);

    void deleteUser(long id);

}
