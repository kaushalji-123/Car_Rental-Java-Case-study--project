package Model;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;

    // Parameterized constructor
    public Customer(int customerID, String firstName, String lastName, String email, int phoneNumber) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getter methods
    public int getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    // Setter methods
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String displayCustomerDetails() {
        return "Customer ID: " + customerID +
                ", First Name: " + firstName +
                ", Last Name: " + lastName +
                ", Email: " + email +
                ", Phone Number: " + phoneNumber;
    }
}
