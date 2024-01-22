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
    private LeaseType type;

    public enum LeaseType {
        DAILY, MONTHLY
    }

    public Lease(int leaseID, int customerID, int vehicleID, Date startDate2, Date endDate2, LeaseType type) {
        this.leaseID = leaseID;
        this.customerID = customerID;
        this.carID = vehicleID;
        this.startDate = startDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.endDate = endDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.type = type;
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

    public LeaseType getType() {
        return type;
    }

    // Setter methods (if needed)

    // Additional methods
    public void displayLeaseDetails() {
        System.out.println("Lease ID: " + leaseID +
                ", Customer ID: " + customerID +
                ", Car ID: " + carID +
                ", Start Date: " + startDate +
                ", End Date: " + endDate +
                ", Lease Type: " + type);
    }

    public Customer getCustomer() {
        // TODO: Implement this method
        return null;
    }

    public String getModel() {
        // TODO: Implement this method
        return null;
    }

    // Add more methods as needed for specific functionality
}
