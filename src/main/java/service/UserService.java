package service;


import DAO.DaoInterface;
import DAO.DaoJDBC;
import fabrica.UserDaoFactory;
import model.User;

import java.util.List;

public class UserService implements Service {

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private static UserService userService;

    private DaoInterface daoInterface = UserDaoFactory.getDAO();

    private UserService() {
    }

    @Override
    public List<User> allUser() {
        return daoInterface.getAllUser();
    }

    @Override
    public User getUserById(long id) {
        return daoInterface.getUserById(id);
    }

    public User getUserByName(String name){
        return daoInterface.getUserByName(name);
    }

    @Override
    public boolean addUser(User user) {
        if (isNameUnique(user.getName())) {
            daoInterface.addUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(long id, String name, String password, String age, String role) {
        if (getUserById(id).getName().equals(name) || isNameUnique(name)) {
            daoInterface.updateUser(new User(id, name, password, Integer.parseInt(age), role));
            return true;
        }
        return false;
    }

    @Override
    public void deleteUserById(long id) {
        daoInterface.deleteUser(id);
    }

    @Override
    public boolean validateUser(String name, String password) {
        if (!isNameUnique(name)) {
            return getUserByName(name).getPassword().equals(password);
        }
        return false;
    }

    public void deleteAllUser() {
        new DaoJDBC().dropTable();
        new DaoJDBC().createTable();
    }

    private boolean isNameUnique(String name) {
        return daoInterface.getUserByName(name) == null;
    }
}
