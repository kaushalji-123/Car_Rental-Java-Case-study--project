package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Utils.DBConnection;

import Model.Customer;
import Model.Lease;
import Model.Vehicle;

public class ICarLeaseRepositoryImpl implements ICarLeaseRepository {
//    private List<Vehicle> vehicleList = new ArrayList<>();
    Connection connection; 
    

    public ICarLeaseRepositoryImpl() {
        connection = new DBConnection().getConnection();
    }
  
    @Override
    public void addCar(Vehicle car) throws SQLException {
        String query = "INSERT INTO vehicle (vehicleID, brand, model, year, dailyRate, status, passengerCapacity, engineCapacity) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, car.getVehicleID());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setInt(4, car.getYear());
            preparedStatement.setDouble(5, car.getDailyRate());
            preparedStatement.setBoolean(6, car.isAvailable());
            preparedStatement.setInt(7, car.getPassengerCapacity());
            preparedStatement.setDouble(8, car.getEngineCapacity());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Car added successfully.");
            } else {
                System.out.println("Failed to add car.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding car to the database: " + e.getMessage());
            throw e; // Re-throw the exception to indicate the error to the calling code
        }
    }


    @Override
    public void removeVehicle(int vehicleID) throws SQLException {
    	Statement statement = connection.createStatement();
        String query = "DELETE FROM Vehicle WHERE vehicleID = '" + vehicleID + "'";

        try {
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Vehicle with ID " + vehicleID + " removed successfully.");
            } else {
                System.out.println("Vehicle with ID " + vehicleID + " not found.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Vehicle> listAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        try {
            String query = "SELECT * FROM Vehicle WHERE isAvailable = true";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle(
                            resultSet.getInt("vehicleID"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getInt("year"),
                            resultSet.getDouble("dailyRate"),
                            resultSet.getBoolean("isAvailable"),
                            resultSet.getInt("passengerCapacity"),
                            resultSet.getInt("engineCapacity")
                    );

                    availableVehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception (e.g., log it or throw a custom exception)
            e.printStackTrace();
        }

        return availableVehicles;
    }


    @Override
    public List<Vehicle> listRentedVehicles() {
        List<Vehicle> rentedVehicles = new ArrayList<>();

        try {
            // Assuming you have a 'Lease' table with a column 'vehicleID' indicating the rented vehicles
            String query = "SELECT v.* FROM Vehicle v JOIN Lease l ON v.vehicleID = l.vehicleID";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    // Assuming you have a constructor in Vehicle class to create an instance from ResultSet
                    Vehicle rentedVehicle = new Vehicle(
                            resultSet.getInt("vehicleID"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getInt("year"),
                            resultSet.getDouble("dailyRate"),
                            resultSet.getBoolean("isAvailable"),
                            resultSet.getInt("passengerCapacity"),
                            resultSet.getInt("engineCapacity")
                    );

                    rentedVehicles.add(rentedVehicle);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception (e.g., log it or throw a custom exception)
            e.printStackTrace();
        }

        return rentedVehicles;
    }

    @Override
    public Vehicle findVehicleById(int vehicleID) {
        String query = "SELECT * FROM Vehicle WHERE vehicleID = '" + vehicleID + "'";
        Vehicle foundVehicle = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                foundVehicle = new Vehicle(
                        resultSet.getInt("vehicleID"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getDouble("dailyRate"),
                        resultSet.getBoolean("isAvailable"),
                        resultSet.getInt("passengerCapacity"),
                        resultSet.getInt("engineCapacity")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding vehicle by ID: " + e.getMessage());
        }

        return foundVehicle;
    }


    @Override
    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customer (customerID, firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.getPhoneNumber());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer added successfully: " + customer.displayCustomerDetails());
            } else {
                System.out.println("Failed to add customer: " + customer.displayCustomerDetails());
            }
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }
    @Override
    public void removeCustomer(int customerID) {
        String query = "DELETE FROM Customer WHERE customerID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer with ID " + customerID + " removed successfully.");
            } else {
                System.out.println("Customer with ID " + customerID + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error removing customer: " + e.getMessage());
        }
    }


    @Override
    public List<Customer> listCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String query = "SELECT * FROM Customer";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int customerID = resultSet.getInt("customerID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                int phoneNumber = resultSet.getInt("phoneNumber");

                Customer customer = new Customer(customerID, firstName, lastName, email, phoneNumber);
                customerList.add(customer);
            }

        } catch (SQLException e) {
            System.out.println("Error listing customers: " + e.getMessage());
        }

        return customerList;
    }


    @Override
    public Customer findCustomerById(int customerID) {
        String query = "SELECT * FROM Customer WHERE customerID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String email = resultSet.getString("email");
                    int phoneNumber = resultSet.getInt("phoneNumber");

                    return new Customer(customerID, firstName, lastName, email, phoneNumber);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding customer by ID: " + e.getMessage());
        }

        return null; // Return null if customer is not found or an error occurs
    }


    @Override
    public Lease createLease(int customerID, int vehicleID, Date startDate, Date endDate) {
        try {
            String query = "INSERT INTO Lease (customerID, vehicleID, startDate, endDate) VALUES (" +
                    customerID + ", " +
                    vehicleID + ", " +
                    "'" + startDate + "', " + 
                    "'" + endDate + "')";

            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int leaseID = generatedKeys.getInt(1);
                        return new Lease(leaseID, customerID, vehicleID, startDate, endDate);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error creating lease: " + e.getMessage());
        }

        return null;
    }



    @Override
    public void returnVehicle(int leaseID) {
        // TODO: Implement logic to return the leased vehicle

        try {
            // Assuming you have a Lease table with a column 'isReturned' to track the return status
            String updateQuery = "UPDATE Lease SET isReturned = true WHERE leaseID = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, leaseID);
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Vehicle with Lease ID " + leaseID + " has been successfully returned.");
                } else {
                    System.out.println("Failed to return vehicle. Lease ID " + leaseID + " not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error returning vehicle: " + e.getMessage());
        }
    }


    @Override
    public List<Lease> listActiveLeases() {
        List<Lease> activeLeases = new ArrayList<>();

        try {
            String query = "SELECT * FROM Lease WHERE isReturned = false";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Lease lease = new Lease(
                            resultSet.getInt("leaseID"),
                            resultSet.getInt("customerID"),
                            resultSet.getInt("carID"),
                            resultSet.getDate("startDate").toLocalDate(),
                            resultSet.getDate("endDate").toLocalDate(),
                            resultSet.getDouble("totalCost")
                    );

                    activeLeases.add(lease);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listing active leases: " + e.getMessage());
        }

        return activeLeases;
    }

    @Override
    public List<Lease> listLeaseHistory() {
        List<Lease> leaseHistory = new ArrayList<>();

        try {
            // Assuming you have a LeaseHistory table with appropriate columns
            String query = "SELECT * FROM LeaseHistory";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int leaseID = resultSet.getInt("leaseID");
                    int customerID = resultSet.getInt("customerID");
                    int vehicleID = resultSet.getInt("vehicleID");
                    LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
                    LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
                    double totalCost = resultSet.getDouble("totalCost");

                    Lease lease = new Lease(leaseID, customerID, vehicleID, startDate, endDate, totalCost);
                    leaseHistory.add(lease);
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception (e.g., log it or throw a custom exception)
            e.printStackTrace();
        }

        return leaseHistory;
    }


    @Override
    public void recordPayment(Lease lease, double amount) {
        try {
            // Assuming you have a Payment table with appropriate columns
            String query = "INSERT INTO Payment (leaseID, paymentAmount, paymentDate) VALUES (?, ?, NOW())";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, lease.getLeaseID());
                preparedStatement.setDouble(2, amount);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Payment recorded successfully for Lease ID: " + lease.getLeaseID());
                } else {
                    System.out.println("Failed to record payment for Lease ID: " + lease.getLeaseID());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error recording payment: " + e.getMessage());
        }
    }
	@Override
	public void addVehicle(Vehicle vehicle) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
