package Utils;
import java.sql.*;

public class DBConnection {
    Connection c;
   public Statement s;

    public DBConnection() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrental", "root", "Forgate@123");
            s = c.createStatement();
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database");
            e.printStackTrace();
        }
    }
}
