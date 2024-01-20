package Model;

public class Vehicle {
    private int vehicleID;
    private String brand;
    private String model;
    private int year;
    private double dailyRate;
    private boolean isAvailable;
    private int passengerCapacity;
    private int engineCapacity;
    
    public Vehicle(int vehicleID, String brand, String model, int year, double dailyRate,boolean isAvailable, int passengerCapacity, int engineCapacity) {
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.isAvailable = isAvailable; 
        this.passengerCapacity = passengerCapacity;
        this.engineCapacity = engineCapacity;
    }


    public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public int getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public String displayVehicleDetails() {
	    return "Vehicle ID: " + vehicleID +
	            ", Brand: " + brand +
	            ", Model: " + model +
	            ", Year: " + year +
	            ", Daily Rate: $" + dailyRate +
	            ", Available: " + (isAvailable ? "Yes" : "No") +
	            ", Passenger Capacity: " + passengerCapacity +
	            ", Engine Capacity: " + engineCapacity;
	}
}
