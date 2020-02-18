package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetConfigDAO {

    private static final String PATH_TO_PROPERTIES = "config.properties";

    private static Properties properties = new Properties();

    public static String getProperties(String key){
        return properties.getProperty(key);
    }

    static {
        InputStream fileInputStream;
        try {
            fileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файла " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }
    }
}
