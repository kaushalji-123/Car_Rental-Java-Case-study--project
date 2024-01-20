package Model;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Lease {
    private int leaseID;
    private int customerID;
    private int carID;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;

    // Parameterized constructor
    public Lease(int leaseID, int customerID, int carID, LocalDate startDate, LocalDate endDate, double totalCost) {
        this.leaseID = leaseID;
        this.customerID = customerID;
        this.carID = carID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }

    public Lease(int leaseID, int customerID, int vehicleID, Date startDate2, Date endDate2) {
        this.leaseID = leaseID;
        this.customerID = customerID;
        this.carID = vehicleID;  // Assuming carID corresponds to vehicleID
        this.startDate = startDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.endDate = endDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

	// Getter methods
    public int getLeaseID() {
        return leaseID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getCarID() {
        return carID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    // Setter methods (if needed)

    // Additional methods
    public void displayLeaseDetails() {
        System.out.println("Lease ID: " + leaseID +
                ", Customer ID: " + customerID +
                ", Car ID: " + carID +
                ", Start Date: " + startDate +
                ", End Date: " + endDate +
                ", Total Cost: $" + totalCost);
    }

    // Add more methods as needed for specific functionality
}
