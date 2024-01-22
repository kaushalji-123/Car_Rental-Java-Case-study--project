//package dao;
//
//import Model.Vehicle;
//import Model.Customer;
//import Model.Lease;
//
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.List;
//
//import Exception.CarNotFoundException;
//import Exception.CustomerNotFoundException;
//
//public interface ICarLeaseRepository {
//    // Vehicle Management
//    void addVehicle(Vehicle vehicle) throws SQLException;
//    void removeVehicle(int vehicleID) throws SQLException;
//    List<Vehicle> listAvailableVehicles();
//    List<Vehicle> listRentedVehicles();
//    Vehicle findVehicleById(int vehicleID) throws CarNotFoundException;
//
//    // Customer Management
//    void addCustomer(Customer customer) throws SQLException;
//    void removeCustomer(int customerID);
//    List<Customer> listCustomers();
//    Customer findCustomerById(int customerID) throws CustomerNotFoundException;
//
//    // Lease Management
//    Lease createLease(int customerID, int vehicleID, Date startDate, Date endDate);
//    void returnVehicle(int leaseID);
//    List<Lease> listActiveLeases();
//    List<Lease> listLeaseHistory();
//
//    // Payment Handling
//    void recordPayment(Lease lease, double amount);
//	void addCar(Vehicle car) throws SQLException;
//}
package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import Model.Customer;
import Model.Lease;
import Model.Vehicle;
import Exception.CarNotFoundException;

public interface ICarLeaseRepository {
    void addCar(Vehicle car);

    void removeCar(int vehicleID);

    List<Vehicle> listAvailableCars();

    List<Vehicle> listRentedCars();

    Vehicle findCarById(int vehicleID) throws CarNotFoundException;

    void addCustomer(Customer customer);

    void removeCustomer(int customerID);

    List<Customer> listCustomers();

    Customer findCustomerById(int customerID);

    Lease createLease(int vehicleID, int customerID, String startDate, String endDate);

    void returnCar(int leaseID);

    List<Lease> listActiveLease();

    List<Lease> listLeaseHistory();

    void recordPayment(int leaseID);

    void addVehicle(Vehicle vehicle) throws SQLException;

    void removeVehicle(int vehicleID) throws SQLException;

    List<Vehicle> listAvailableVehicles();

    List<Vehicle> listRentedVehicles();

    Vehicle findVehicleById(int vehicleID) throws CarNotFoundException;

    Lease createLease(int customerID, int vehicleID, Date startDate, Date endDate);

    void returnVehicle(int leaseID);

    List<Lease> listActiveLeases();

    void recordPayment(Lease lease, double amount);
}
