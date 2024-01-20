package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    private int paymentID;
    private int leaseID;
    private Date paymentDate;
    private double amount;

    // Parameterized constructor
    public Payment(int paymentID, int leaseID, Date paymentDate, double amount) {
        this.paymentID = paymentID;
        this.leaseID = leaseID;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    // Getter methods
    public int getPaymentID() {
        return paymentID;
    }

    public int getLeaseID() {
        return leaseID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    // Setter methods
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setLeaseID(int leaseID) {
        this.leaseID = leaseID;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
    public void displayPaymentDetails() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Payment ID: " + paymentID +
                ", Lease ID: " + leaseID +
                ", Payment Date: " + dateFormat.format(paymentDate) +
                ", Amount: $" + amount);
    }
}
