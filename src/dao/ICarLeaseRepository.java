package dao;

import Model.Vehicle;
import Model.Customer;
import Model.Lease;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ICarLeaseRepository {
    // Vehicle Management
    void addVehicle(Vehicle vehicle) throws SQLException;
    void removeVehicle(int vehicleID) throws SQLException;
    List<Vehicle> listAvailableVehicles();
    List<Vehicle> listRentedVehicles();
    Vehicle findVehicleById(int vehicleID);

    // Customer Management
    void addCustomer(Customer customer) throws SQLException;
    void removeCustomer(int customerID);
    List<Customer> listCustomers();
    Customer findCustomerById(int customerID);

    // Lease Management
    Lease createLease(int customerID, int vehicleID, Date startDate, Date endDate);
    void returnVehicle(int leaseID);
    List<Lease> listActiveLeases();
    List<Lease> listLeaseHistory();

    // Payment Handling
    void recordPayment(Lease lease, double amount);
	void addCar(Vehicle car) throws SQLException;
}
