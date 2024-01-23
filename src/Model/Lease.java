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


    public Lease(int leaseID, int customerID, int vehicleID, Date startDate2, Date endDate2) {
        this.leaseID = leaseID;
        this.customerID = customerID;
        this.carID = vehicleID;
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

    // Setter methods (if needed)

    // Additional methods
    public void displayLeaseDetails() {
        System.out.println("Lease ID: " + leaseID +
                ", Customer ID: " + customerID +
                ", Car ID: " + carID +
                ", Start Date: " + startDate +
                ", End Date: " + endDate);
    }

    public Customer getCustomer() {
        // TODO: Implement this method
        return null;
    }

    public String getModel() {
        // TODO: Implement this method
        return null;
    }

	public String getYear() {
		// TODO Auto-generated method stub
		return null;
	}

    // Add more methods as needed for specific functionality
}
