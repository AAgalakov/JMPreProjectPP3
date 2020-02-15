package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetConfigDAO {

    private static final String PATH_TO_PROPERTIES = "config.properties";

    private static Properties properties = new Properties();

    public static String daoType;

    static {
        InputStream fileInputStream;
        try {
            fileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
            daoType = properties.getProperty("daoType");
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файла " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }
    }

//    public static String getTypeDAO() {
//        InputStream fileInputStream;
//        Properties prop = new Properties();
//        String daoType = null;
//        try {
//            fileInputStream = UserDaoFactory.class.getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES);
//            fileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH_TO_PROPERTIES);
//            prop.load(fileInputStream);
//            daoType = prop.getProperty("daoType");
//        } catch (IOException e) {
//            System.out.println("Ошибка в программе: файла " + PATH_TO_PROPERTIES + " не обнаружено");
//            e.printStackTrace();
//        }
//        return daoType;
//    }
}
