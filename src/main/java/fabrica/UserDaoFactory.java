package fabrica;


import DAO.*;

import java.io.*;
import java.util.Properties;

public class UserDaoFactory {

    private static final String PATH_TO_PROPERTIES = "config.properties";

    public static DaoInterface getDAO() {
        String daoType = getTypeDAO();
        DaoInterface daoInterface = null;
        switch (daoType){
            case ("jdbc"):
                daoInterface = new UserJDBCDAO();
                break;
            case ("hibernate"):
                daoInterface = new UserHibernateDaoInterface();
                break;
        }
        return daoInterface;
    }

    public static String getTypeDAO(){
        InputStream fileInputStream;
        Properties prop = new Properties();
        String daoType = null;
        try {
//            fileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
            fileInputStream = UserDaoFactory.class.getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES);
            assert fileInputStream != null;
            prop.load(fileInputStream);
            daoType = prop.getProperty("daoType");
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файла " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }
        return daoType;
    }


}
