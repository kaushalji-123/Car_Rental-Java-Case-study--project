package Model;

import java.sql.*;


public class Vehicle {
    private int vehicleID;
    private String make;
    private String model;
    private int year;
    private double dailyRate;
    private boolean isAvailable;
    private int passengerCapacity;
    private int engineCapacity;

    public Vehicle(int vehicleID, String make, String model, int year, double dailyRate, boolean isAvailable, int passengerCapacity, int engineCapacity) {
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.isAvailable = true;
        this.passengerCapacity = passengerCapacity;
        this.engineCapacity = engineCapacity;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
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

    public boolean getStatus() {
        return isAvailable;
    }

    public void setStatus(boolean isAvailable) {
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
                ", Make: " + make +
                ", Model: " + model +
                ", Year: " + year +
                ", Daily Rate: $" + dailyRate +
                ", Available: " + (isAvailable ? "Yes" : "No") +
                ", Passenger Capacity: " + passengerCapacity +
                ", Engine Capacity: " + engineCapacity;
    }

}







//public class Vehicle {
//    private int vehicleID;
//    private String make;
//    private String model;
//    private int year;
//    private double dailyRate;
//    private boolean isAvailable;
//    private int passengerCapacity;
//    private int engineCapacity;
//    
//    public Vehicle(int vehicleID, String make, String model, int year, double dailyRate,String string, int passengerCapacity, double d) {
//        this.vehicleID = vehicleID;
//        this.make = make;
//        this.model = model;
//        this.year = year;
//        this.dailyRate = dailyRate;
//        this.passengerCapacity = passengerCapacity;
//    }
//
//
//    public int getVehicleID() {
//		return vehicleID;
//	}
//
//	public void setVehicleID(int vehicleID) {
//		this.vehicleID = vehicleID;
//	}
//
//	public String getMake1() {
//		return make;
//	}
//
//	public void setMake(String make) {
//		this.make = make;
//	}
//
//	public String getModel() {
//		return model;
//	}
//
//	public void setModel(String model) {
//		this.model = model;
//	}
//
//	public int getYear() {
//		return year;
//	}
//
//	public void setYear(int year) {
//		this.year = year;
//	}
//
//	public double getDailyRate() {
//		return dailyRate;
//	}
//
//	public void setDailyRate(double dailyRate) {
//		this.dailyRate = dailyRate;
//	}
//
//	public boolean status() {
//		return isAvailable;
//	}
//
//	public void setStatus(boolean isAvailable) {
//		this.isAvailable = isAvailable;
//	}
//
//	public int getPassengerCapacity() {
//		return passengerCapacity;
//	}
//
//	public void setPassengerCapacity(int passengerCapacity) {
//		this.passengerCapacity = passengerCapacity;
//	}
//
//	public int getEngineCapacity() {
//		return engineCapacity;
//	}
//
//	public void setEngineCapacity(int engineCapacity) {
//		this.engineCapacity = engineCapacity;
//	}
//
//	public String displayVehicleDetails() {
//	    return "Vehicle ID: " + vehicleID +
//	            ", make: " + make +
//	            ", Model: " + model +
//	            ", Year: " + year +
//	            ", Daily Rate: $" + dailyRate +
//	            ", Available: " + (isAvailable ? "Yes" : "No") +
//	            ", Passenger Capacity: " + passengerCapacity +
//	            ", Engine Capacity: " + engineCapacity;
//	}
//
//
//	public String getMake(){
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
