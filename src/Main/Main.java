package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dao.*;
import Model.*;
import Model.Vehicle;
import dao.ICarLeaseImpl;

public class Main {
	public static void main(String[] args)
	{ 
		
	     Scanner sc=new Scanner(System.in);
	  System.out.println("1.Vehicle");
      System.out.println("2.Customer");
      System.out.println("3.Lease");
      System.out.println("4.Payments");
      System.out.println("5.Exit");
      System.out.println("Enter your Choice:");
      int ch=sc.nextInt();
      switch(ch)
      {
      case 1:
    	  System.out.println("Vehicle Table");
    	  vehicleManagement();
    	  break;
      case 2:
    	  System.out.println("Customer Table");
    	  customerManagement();
    	  break;
      case 3:
    	  System.out.println("Lease");
    	  LeaseManagement();
    	  break;
      case 4:
    	  System.out.println("Payments");
    	  paymentManagement();
    	  break;
      case 5:
    	  System.out.println("Exiting.....");
    	  
    	  break;
    	  default :
    		  System.out.println("Invalid choice");
    		  break;
      }
	}
	
	public static void vehicleManagement()
	{
		Scanner sc=new Scanner(System.in);
		
		 ICarLeaseImpl icl = new ICarLeaseImpl();
		System.out.println("Enter Vehicle Details");
		System.out.println("1.Enter New Car into Vehicles Table");
		System.out.println("2.Remove the Car");
		System.out.println("3.List Available Cars");
		System.out.println("4.ListRented Cars");
		System.out.println("5.Find Vehicle BY ID"); 
        System.out.println("6.Exit");
		System.out.println("Enter your choice");
		int ch=sc.nextInt();
		switch(ch)
		{
		case 1:
			System.out.println("Enter VehicleID");
			int vehicleID=sc.nextInt();
			System.out.println("Enter VehicleMake");
            String make=sc.next();
			System.out.println("Enter VehicleModel");
            String model=sc.next();
			System.out.println("Enter VehicleYear");
            int year=sc.nextInt();
            System.out.println("Enter DailyRate");
            double DailyRate=sc.nextDouble();
            System.out.println("Enter status of vehicle");
            boolean isAvailable = sc.nextBoolean();
            System.out.println("Enter passengerCapacity");
            int passengerCapacity=sc.nextInt();
            System.out.println("Enter engine capacity");
            int engineCapacity=sc.nextInt();
            Vehicle car;
            car = new Vehicle(vehicleID, make, model, year, DailyRate, isAvailable, passengerCapacity, engineCapacity);
            icl.addCar(car);
			break;
		case 2:
			System.out.println("Enter vehicle ID to remove the car:");
			System.out.println("Enter VehivleID");
			int VehicleID=sc.nextInt();
			icl.removeCar(VehicleID);
			break;
		case 3:
			System.out.println("Listing all available cars");
			icl.listAvailableCars();
			List<Vehicle> v=icl.listAvailableCars();
			System.out.println(v);
			for(Vehicle veh: v)
			{
				    System.out.println("Vehicle ID: " + veh.getVehicleID());
			        System.out.println("Make: " + veh.getMake());
			        System.out.println("Model: " + veh.getModel());
			        System.out.println("Year: " + veh.getYear());
			        System.out.println("DailyRate: " + veh.getDailyRate());
			        System.out.println("status: " + veh.getStatus());
			        System.out.println("passengerCapacity: " + veh.getPassengerCapacity());
			        System.out.println("engineCapacity: " + veh.getEngineCapacity());
			        System.out.println("------------------------");
			}
			break;
		
		case 4:
			System.out.println("Listing all Rented cars");
			icl.listCustomers();
			List<Customer> cust = icl.listCustomers();
			icl.listAvailableCars();
			List<Vehicle> vehicleList=icl.listAvailableCars();
			for(Vehicle veh: vehicleList)
			{
				    System.out.println("Vehicle ID: " + veh.getVehicleID());
			        System.out.println("Make: " + veh.getMake());
			        System.out.println("Model: " + veh.getModel());
			        System.out.println("Year: " + veh.getYear());
			        System.out.println("DailyRate: " + veh.getDailyRate());
			        System.out.println("status: " + veh.getStatus());
			        System.out.println("passengerCapacity: " + veh.getPassengerCapacity());
			        System.out.println("engineCapacity: " + veh.getEngineCapacity());
			        System.out.println("------------------------");
			}
			break;
		case 5:
			System.out.println("Finding the car by id");
			System.out.println("Enter VehicleID");
			int vehicleid=sc.nextInt();
			Vehicle vo=icl.findCarById(vehicleid);
				    System.out.println("Vehicle ID: " + vo.getVehicleID());
			        System.out.println("Make: " + vo.getMake());
			        System.out.println("Model: " + vo.getModel());
			        System.out.println("Year: " + vo.getYear());
			        System.out.println("DailyRate: " + vo.getDailyRate());
			        System.out.println("passengerCapacity: " + vo.getPassengerCapacity());
			        System.out.println("engineCapacity: " + vo.getEngineCapacity());
			        System.out.println("------------------------");
			        break;
		case 6:
			System.out.println("Exiting.....");
			break;
			default:
				System.out.println("Invalid choice");
				break;
		}
		
	}
	public static void customerManagement()
	{
		Scanner sc=new Scanner(System.in);
		ICarLeaseImpl icl=new ICarLeaseImpl();
	
		System.out.println("Enter Customer Details");
		System.out.println("1.Enter customer");
		System.out.println("2.remove customer");
		System.out.println("3.List customers");
		System.out.println("4.Find Customer BY ID");
		System.out.println("5.Exit");
		System.out.println("Enter Choice");
		int ch=sc.nextInt();
		switch(ch)
		{
		case 1:
			System.out.println("Enter CustomerID:");
			int customerID=sc.nextInt();
			System.out.println("Enter FirstName:");
			String firstName=sc.next();
			System.out.println("Enter LastName:");
			String lastName=sc.next();
			System.out.println("Enter email:");
			String email=sc.next();
			System.out.println("Enter PhoneNumber:");
			long phoneNumber=sc.nextLong();
			Customer customer;
			customer=new Customer(customerID,firstName,lastName,email,phoneNumber);
			icl.addCustomer(customer);
			break;
		case 2:
			System.out.println("remove Customer");
			System.out.println("Enter CustomerID");
			int CustomerID=sc.nextInt();
			icl.removeCustomer(CustomerID);
			break;
		case 3:
			System.out.println("Listing Customers");
			icl.listCustomers();
			List<Customer> cust = icl.listCustomers();

		    for (Customer custo : cust) {
		        System.out.println("Customer ID: " + custo.getCustomerID());
		        System.out.println("First Name: " + custo.getFirstName());
		        System.out.println("Last Name: " + custo.getLastName());
		        System.out.println("Email: " + custo.getEmail());
		        System.out.println("Phone Number: " + custo.getPhoneNumber());
		        System.out.println("------------------------");
		    }
			break;
		case 4:
			System.out.println("finding customer");
			System.out.println("Enter CustomerID");
			int customerid=sc.nextInt();
			Customer cust1=icl.findCustomerById(customerid);
			System.out.println("Customer ID: " + cust1.getCustomerID());
	        System.out.println("First Name: " + cust1.getFirstName());
	        System.out.println("Last Name: " + cust1.getLastName());
	        System.out.println("Email: " + cust1.getEmail());
	        System.out.println("Phone Number: " + cust1.getPhoneNumber());
	        System.out.println("------------------------");
			break;
		case 5:
			System.out.println("Exiting....");
			break;
			default :
				System.out.println("Invalid choice");
				break;
	}
	}
	
	
		public static void  LeaseManagement() 
		{
			Scanner sc=new Scanner(System.in);
			ICarLeaseImpl icl=new ICarLeaseImpl();
			System.out.println("1.Create Lease");
			System.out.println("2.Return Car");
			System.out.println("3.List Active Leases");
			System.out.println("4.List Lease History");
			System.out.println("5.Exit");
			System.out.println("Enter Your Choice:");
			int ch=sc.nextInt();
			switch(ch)
			{
			case 1:
				
				System.out.println("Enter CustomerID:");
				int CustomerID=sc.nextInt();
				System.out.println("Enter CarID");
				int VehicleID=sc.nextInt();
				System.out.println("Enter statt Date");
				String startDate=sc.next();
				System.out.println("Enter End Date");
			    String endDate=sc.next();
				Lease lease=icl.createLease(VehicleID,CustomerID,startDate,endDate);
				System.out.println("LEASEID:"+lease.getLeaseID());
				System.out.println("Customer ID:"+lease.getCustomer().getCustomerID());
				System.out.println("Customer Name:"+lease.getCustomer().getFirstName()+" "+lease.getCustomer().getLastName());
				System.out.println("Customer Email:"+lease.getCustomer().getEmail());
				System.out.println("Customer Phone:"+lease.getCustomer().getPhoneNumber());
				System.out.println("VehicleID:  "+lease.getCarID());

				break;
			case 2:
				System.out.println("Enter leaseID");
				int leaseID=sc.nextInt();
				icl.returnCar(leaseID);
				break;
			case 3:
				System.out.println("List Active Leases");
				List<Lease>le=icl.listActiveLease();
				for(Lease l:le)
				{
					System.out.println("LEASEID:"+l.getLeaseID());
					System.out.println("Customer ID:"+l.getCustomer().getCustomerID());
					System.out.println("Customer Name:"+l.getCustomer().getFirstName()+" "+l.getCustomer().getLastName());
					System.out.println("Customer Email:"+l.getCustomer().getEmail());
					System.out.println("Customer Phone:"+l.getCustomer().getPhoneNumber());
					System.out.println("VehicleID:  "+l.getCarID());
//					System.out.println("Vehiclec Make:  "+l.getVehicle().getMake());
//					System.out.println("Vehicle Model:  "+l.getVehicle().getModel());
//					System.out.println("Vehicle Year: "+l.getVehicle().getYear());
//					System.out.println("Vehicle DailyRate:  "+l.getVehicle().getDailyRate());
//					System.out.println(l.getVehicle().getStatus());
//					System.out.println("Vehicle Capacity: "+l.getVehicle().getPassengerCapacity());
//					System.out.println(l.getVehicle().getEngineCapacity());
					System.out.println("StartDate: "+l.getStartDate());
					System.out.println("EndDate: "+l.getEndDate());
					System.out.println("----------------------------------");
				}
				break;
			case 4:
				//icl.listLeaseHistory();
				List<Lease>lea=icl.listLeaseHistory();
				for(Lease l:lea)
				{
					System.out.println("LEASEID:"+l.getLeaseID());
					System.out.println("Customer ID:"+l.getCustomer().getCustomerID());
					System.out.println("Customer Name:"+l.getCustomer().getFirstName()+" "+l.getCustomer().getLastName());
					System.out.println("Customer Email:"+l.getCustomer().getEmail());
					System.out.println("Customer Phone:"+l.getCustomer().getPhoneNumber());
					System.out.println("VehicleID:  "+l.getCarID());
//					System.out.println("Vehiclec Make:  "+l.vehicle().getMake());
//					System.out.println("Vehicle Model:  "+l.getVehicle().getModel());
//					System.out.println("Vehicle Year: "+l.getVehicle().getYear());
//					System.out.println("Vehicle DailyRate:  "+l.getVehicle().getDailyRate());
//					System.out.println(l.getVehicle().getStatus());
//					System.out.println("Vehicle Capacity: "+l.getVehicle().getPassengerCapacity());
//					System.out.println(l.getVehicle().getEngineCapacity());
					System.out.println("StartDate: "+l.getStartDate());
					System.out.println("EndDate: "+l.getEndDate());
					System.out.println("----------------------------------");				}
				break;
			case 5:
				System.out.println("Invalid choice");
				break;
			default :
				break;
			}
		}
			
			
		public static void paymentManagement()
		{
			Scanner sc=new Scanner(System.in);
			ICarLeaseImpl icl=new ICarLeaseImpl();
			System.out.println("Enter LeaseID:");
			int LeaseID=sc.nextInt();
			//double amount=sc.nextDouble();
            icl.recordPayment(LeaseID);
		}
}























//package Main;
//
//import java.sql.SQLException;
//
//import java.util.Scanner;
//import Utils.DBConnection;
//import dao.ICarLeaseRepository;
//import dao.ICarLeaseRepositoryImpl;
//import Model.Vehicle;
//
//public class MainModule {
//    static Scanner sc = new Scanner(System.in);
//    static ICarLeaseRepository repo;
//
//    static {
//        try {
//        	repo = new ICarLeaseRepositoryImpl();
//
//        } catch (SQLException e) {
//            // Handle the exception (e.g., print an error message)
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws SQLException {
//        System.out.println("Welcome to Kirubanantham Car Rental System");
//        checkDatabaseConnection();
//        try {
//            addCar();
//        }catch(Exception e) {
//        	e.getStackTrace();
//        }
//    }
//
//    public static void addCar() {
//        System.out.println("Enter the following details:");
//        System.out.println("Enter vehicleID");
//        int vehicleId = sc.nextInt();
//        sc.nextLine(); // consume the newline left by nextInt()
//
//        System.out.println("Enter make");
//        String make = sc.nextLine();
//
//        System.out.println("Enter model");
//        String model = sc.nextLine();
//
//        System.out.println("Enter year");
//        int year = sc.nextInt();
//
//        System.out.println("Enter dailyRate");
//        double dailyRate = sc.nextInt();
//
//        System.out.println("Enter status: AVAILABLE (true) OR UNAVAILABLE (false)");
//        boolean isAvailable = Boolean.valueOf(sc.next().toUpperCase());
//
//        System.out.println("Enter Passenger capacity");
//        int passengerCapacity = sc.nextInt();
//
//        System.out.println("Enter engine Capacity");
//        int engineCapacity = sc.nextInt();
//
//        Vehicle car = new Vehicle(vehicleId, make, model, year, dailyRate, isAvailable, passengerCapacity, engineCapacity);
//
//        try{
//            repo.addVehicle(car);
//            System.out.println("Car added successfully.");
//        }catch (SQLException e) {
//            System.out.println("Error adding car to the database: " + e.getMessage());
//        }
//    }
//}
