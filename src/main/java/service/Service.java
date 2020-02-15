package service;

import model.User;

import java.util.List;

public interface Service {

    List<User> allUser();

    User getUserById(long id);

    boolean addUser(User user);

    boolean updateUser(long id, String name, String password, String age, String role);

    void deleteUserById(long id);

    boolean validateUser(String name, String password);

}
