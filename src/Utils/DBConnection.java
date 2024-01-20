package Utils;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    Connection connection;
    Statement statement;

    public DBConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrental", "root", "Forgate@123");
            statement = connection.createStatement();
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing the connection");
            e.printStackTrace();
        }
    }
}
