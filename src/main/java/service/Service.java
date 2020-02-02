package service;

import model.User;

import java.util.List;

public interface Service {

    List<User> allUser();

    User getUserById(long id);

    boolean addUser(User user);

    boolean updateUser(long id, String name, String age);

    boolean deleteUserById(long id);

}
