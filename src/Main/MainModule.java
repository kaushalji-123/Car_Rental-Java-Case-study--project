package Main;

import java.sql.SQLException;
import java.util.Scanner;
import Utils.DBConnection;
import dao.ICarLeaseRepository;
import dao.ICarLeaseRepositoryImpl;
import Model.Vehicle;

public class MainModule {
    static Scanner sc = new Scanner(System.in);
    static ICarLeaseRepository repo = new ICarLeaseRepositoryImpl();

    public static void main(String[] args) {
        System.out.println("Welcome to Kirubanantham Car Rental System");
        checkDatabaseConnection();
        addCar();
    }

    public static void checkDatabaseConnection() {
        DBConnection dbConnection = new DBConnection();

        if (dbConnection.isConnected()) {
            System.out.println("Database connection is successful.");
        } else {
            System.out.println("Unable to connect to the database.");
        }

        // Close the connection when done
        dbConnection.closeConnection();
    }

    public static void addCar() {
        System.out.println("Enter the following details:");
        System.out.println("Enter vehicleID");
        int vehicleId = sc.nextInt();
        sc.nextLine(); // consume the newline left by nextInt()

        System.out.println("Enter make");
        String make = sc.nextLine();

        System.out.println("Enter model");
        String model = sc.nextLine();

        System.out.println("Enter year");
        int year = sc.nextInt();

        System.out.println("Enter dailyRate");
        double dailyRate = sc.nextInt();

        System.out.println("Enter status: AVAILABLE (true) OR UNAVAILABLE (false)");
        boolean isAvailable = Boolean.valueOf(sc.next().toUpperCase());

        System.out.println("Enter Passenger capacity");
        int passengerCapacity = sc.nextInt();

        System.out.println("Enter engine Capacity");
        int engineCapacity = sc.nextInt();

        Vehicle car = new Vehicle(vehicleId, make, model, year, dailyRate, isAvailable, passengerCapacity, engineCapacity);

        try {
            repo.addVehicle(car);
            System.out.println("Car added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding car to the database: " + e.getMessage());
        }
    }
}
