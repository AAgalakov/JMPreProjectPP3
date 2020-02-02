package service;


import DAO.DaoInterface;
import DAO.UserJDBCDAO;
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
//        return new UserJDBCDAO().getAllUser();
//        return new UserHibernateDAO().getAllUser();
        return daoInterface.getAllUser();
    }

    @Override
    public User getUserById(long id) {
//        return new UserJDBCDAO().getUserById(id);
//        return new UserHibernateDAO().getUserById(id);
        return daoInterface.getUserById(id);
    }

    @Override
    public boolean addUser(User user) {
//        new UserJDBCDAO().addUser(user);
//        new UserHibernateDAO().addUser(user);
        daoInterface.addUser(user);
        return true;
    }

    @Override
    public boolean updateUser(long id, String name, String age) {
        if (name.isEmpty() && age.isEmpty()) {
            return false;
        }
        if (!name.isEmpty()) {
//            new UserJDBCDAO().updateUsersName(id, name);
//            new UserHibernateDAO().updateUsersName(id, name);
            daoInterface.updateUsersName(id, name);
        }
        if (!age.isEmpty()) {
//            new UserJDBCDAO().updateUsersAge(id, Integer.parseInt(age));
//            new UserHibernateDAO().updateUsersAge(id, Integer.parseInt(age));
            daoInterface.updateUsersAge(id, Integer.parseInt(age));
        }
        return true;
    }

    @Override
    public boolean deleteUserById(long id) {
//        new UserJDBCDAO().deleteUser(id);
//        new UserHibernateDAO().deleteUser(id);
        daoInterface.deleteUser(id);
        return true;
    }

    public void deleteAllUser() {
        new UserJDBCDAO().dropTable();
        new UserJDBCDAO().createTable();
    }
}
