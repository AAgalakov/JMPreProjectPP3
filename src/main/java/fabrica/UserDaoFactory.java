package fabrica;

import DAO.DaoInterface;
import DAO.DaoHibernate;
import DAO.DaoJDBC;
import util.DBHelper;

import static util.GetConfigDAO.getProperties;

public class UserDaoFactory {

    public static DaoInterface getDAO() {
        DaoInterface daoInterface;
        String daoType = getProperties("daoType");
        if ("hibernate".equals(daoType)) {
            daoInterface = new DaoHibernate(DBHelper.createSessionFactory());
        } else if ("jdbc".equals(daoType)) {
            daoInterface = new DaoJDBC();
        } else {
            throw new RuntimeException(daoType + " is unknown connection");
        }
        return daoInterface;
    }
}
