//package Utils;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class PropertyUtil {
//    public PropertyUtil() {
//    }
//
//    public static String getProperty(String propertyFileName) {
//        Properties properties = new Properties();
//
//        try (FileInputStream input = new FileInputStream("C:/Users/divyanshu kaushal/eclipse-workspace/CarRental/src/" + propertyFileName)) {
//            properties.load(input);
//
//            // Customize these property keys based on your property file structure
//            String host = properties.getProperty("db.host");
//            String dbName = properties.getProperty("db.name");
//            String username = properties.getProperty("db.username");
//            String password = properties.getProperty("db.password");
//            String port = properties.getProperty("db.port");
//
//            // Construct and return the connection string
//            return "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?user=" + username;
//        } catch (IOException e) {
//            e.printStackTrace(); // Handle exceptions according to your application's needs
//            return null;
//        }
//    }
//}
