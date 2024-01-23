package dao;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Model.*;
import Model.Lease.LeaseType;
import Exception.CarNotFoundException;
import Exception.CustomerNotFoundException;
import Exception.LeaseNotFoundException;
import Utils.*;
public class ICarLeaseImpl implements ICarLeaseRepository 
{


	@Override
	public void addCar(Vehicle car) {
		// TODO Auto-generated method stub
		DBConnection c=new DBConnection();
		int vehicleID=car.getVehicleID();
		String make=car.getMake();
		String model=car.getModel();
		int year=car.getYear();
		double Rate=car.getDailyRate();
		boolean isAvailable = true;
		int passengers=car.getPassengerCapacity();
		int engine=car.getEngineCapacity();
		String query="insert into carrental.vehicle values('"+vehicleID+"','"+make+"','"+model+"','"+year+"','"+Rate+"','"+(isAvailable ? 1 : 0)+"','"+passengers+"','"+engine+"')";
		try
		{
			c.s.executeUpdate(query);
			System.out.println("Record inserted successfully");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void removeCar(int VehicleID) 
	{
		// TODO Auto-generated method stub
		DBConnection c=new DBConnection();
		String query1="delete from vehicle where VehicleID='"+VehicleID+"'";
		try
		{
		   c.s.executeUpdate(query1);
		   System.out.println("Car Deleted");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public List<Vehicle> listAvailableCars() {
		
			List<Vehicle> v=new ArrayList<>();
		DBConnection c=new DBConnection();
		String query="select * from carrental.vehicle where status='available'";
		try
		{
		ResultSet rs=c.s.executeQuery(query);
			while(rs.next())
			{
				v.add(new Vehicle(rs.getInt("VehicleID"),rs.getString("Make"),rs.getString("Model"),rs.getInt("Year"),rs.getDouble("DailyRate"),rs.getBoolean("status"),rs.getInt("passengerCapacity"),rs.getInt("engineCapacity")));
			}
			System.out.println("After connection"); 
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return v;
	}

	@Override
	public List<Vehicle> listRentedCars() {
		// TODO Auto-generated method stub
		List<Vehicle> ve=new ArrayList<>();
		DBConnection c=new DBConnection();
		String query="select * from Vehicle where status='not_available'";
		try
		{
		ResultSet rs=c.s.executeQuery(query);
			while(rs.next())
			{
				ve.add(new Vehicle(rs.getInt("VehicleID"),rs.getString("Make"),rs.getString("Model"),rs.getInt("Year"),rs.getDouble("DailyRate"),rs.getBoolean("status"),rs.getInt("passengerCapacity"),rs.getInt("engineCapacity")));
			}
			System.out.println("After connection");
		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ve;
	}

	@Override
	public Vehicle findCarById(int vehicleID){
		// TODO Auto-generated method stub
		Vehicle v = null;
		DBConnection c=new DBConnection();
		String query="select * from vehicle where vehicleID='"+vehicleID+"'";
		try
		{
			ResultSet rs=c.s.executeQuery(query);
			if(rs.next())
			{
				v=new Vehicle(rs.getInt("VehicleID"),rs.getString("Make"),rs.getString("Model"),rs.getInt("Year"),rs.getDouble("DailyRate"),rs.getBoolean("status"),rs.getInt("passengerCapacity"),rs.getInt("engineCapacity"));
			}
			else
			{
			throw new CarNotFoundException("No car with specific id");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return v;
	}

	@Override
	public void addCustomer(Customer customer)
	{
		// TODO Auto-generated method stub
	  DBConnection c=new DBConnection();
		int CID=customer.getCustomerID();
		String FName=customer.getFirstName();
		String LName=customer.getLastName();
		String Email=customer.getEmail();
		long PNumber=customer.getPhoneNumber();
		String query="insert into Customer values('"+CID+"','"+FName+"','"+LName+"','"+Email+"','"+PNumber+"')";
		try
		{
			c.s.executeUpdate(query);
			System.out.println("Record inserted successfully");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void removeCustomer(int CustomerID) 
	{
		// TODO Auto-generated method stub
		DBConnection c=new DBConnection();
		String query="delete from Lease where CustomerID='"+CustomerID+"'";
		String query1="delete from Customer where CustomerID='"+CustomerID+"'";
		try
		{
			c.s.executeUpdate(query);
			c.s.executeUpdate(query1);
			System.out.println("Record deleted successfully");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public List<Customer> listCustomers() 
	{
		DBConnection c=new DBConnection();
		String query="select * from Customer";
		List<Customer>cust=new ArrayList<>();
		try
		{
			ResultSet rs=c.s.executeQuery(query);
				while(rs.next())
				{
				cust.add(new Customer(rs.getInt("CustomerID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("email"),rs.getLong("PhoneNumber")));
				}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		// TODO Auto-generated method stub
		return cust;
	}

	@Override
	public Customer findCustomerById(int customerid){
		// TODO Auto-generated method stub
		Customer cust1=null;
		DBConnection c=new DBConnection();
		String query="select * from customer where customerID='"+customerid+"'";
		try
		{
			ResultSet rs=c.s.executeQuery(query);
			if(rs.next())
			{
				System.out.println("valid user");
				cust1=new Customer(rs.getInt("CustomerID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("email"),rs.getLong("PhoneNumber"));
			}
			else
			{
				throw new CustomerNotFoundException("Customer not available");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return cust1;
	}

	@Override
	public Lease createLease(int VehicleID,int CustomerID,String startDate,String endDate)
	{
		DBConnection c=new DBConnection();
		Lease lease=null;
		LocalDate sDate=LocalDate.parse(startDate);
		LocalDate eDate=LocalDate.parse(endDate);
		long diff=ChronoUnit.DAYS.between(sDate, eDate);
//		String type=null;
//		if(diff>30)
//		{
//			type="Monthly";
//		}
//		else
//		{
//			type="Daily";
//		}
//		System.out.println(type);
		String query = "INSERT INTO Lease (vehicleID,CustomerID, startDate, endDate) VALUES ('" +VehicleID + "','" +CustomerID + "','" + sDate + "','" + endDate + "')";
		String query1="select * from Lease l inner join Customer c ON l.CustomerID=c.CustomerID Inner Join Vehicle v ON l.VehicleID=v.VehicleID where l.customerID='"+CustomerID+"' AND l.vehicleID='"+VehicleID+"'";
		try
		{
			c.s.executeUpdate(query);
			ResultSet rs=c.s.executeQuery(query1);
			while(rs.next())
			{
			   Customer customer = new Customer(rs.getInt("CustomerID"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getLong("PhoneNumber"));
			Vehicle vehicle = new Vehicle(rs.getInt("VehicleID"),rs.getString("Make"),rs.getString("Model"),rs.getInt("year"),rs.getDouble("dailyRate"),rs.getBoolean("status"),rs.getInt("PassengerCapacity"),rs.getInt("engineCapacity"));
				lease = new Lease(rs.getInt("LeaseID"),rs.getInt("CustomerID"),rs.getInt("VehicleID"),rs.getDate("startDate"),rs.getDate("EndDate"));
			}
			System.out.println("Lease Created");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return lease;
	}

	@Override
	public void returnCar(int leaseID) {
		// TODO Auto-generated method stub
		DBConnection c=new DBConnection();
		String query="select * from Lease l INNER JOIN Vehicle v ON l.VehicleID=v.VehicleID where l.LeaseID='"+leaseID+"'";
		try
		{
			ResultSet rs=c.s.executeQuery(query);
			if(rs.next())
			{
				System.out.println("LeaseID:"+rs.getInt("LeaseID"));
				System.out.println("VehicleID:"+rs.getInt("VehicleID"));
				System.out.println("Make:"+rs.getString("Make"));
				System.out.println("Model:"+rs.getString("Model"));	
				System.out.println("StartDate:"+rs.getString("startDate"));		
				System.out.println("EndDate:"+rs.getString("EndDate"));			
				}
			else
			{
				throw new LeaseNotFoundException("No lease found this lease ID");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public List<Lease> listActiveLease() {
		DBConnection c=new DBConnection();
		String query="select * from Lease l Inner join Vehicle v ON l.VehicleID=v.VehicleID Inner Join Customer c ON l.CustomerID=c.CustomerID where startDate<=CURDATE() AND endDate>=CURDATE()";
		List<Lease> lease=new ArrayList<>();
		try
		{
			ResultSet rs=c.s.executeQuery(query);
			while(rs.next())
			{
				Customer customer=new Customer(rs.getInt("CustomerID"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getLong("PhoneNumber"));
				Vehicle vehicle=new Vehicle(rs.getInt("VehicleID"),rs.getString("Make"),rs.getString("Model"),rs.getInt("year"),rs.getDouble("dailyRate"),rs.getBoolean("status"),rs.getInt("PassengerCapacity"),rs.getInt("engineCapacity"));
				lease.add(new Lease(rs.getInt("LeaseID"),rs.getInt("CustomerID"),rs.getInt("VehicleID"),rs.getDate("startDate"),rs.getDate("EndDate")));
			}
			
			System.out.println("Retrival success");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return lease;
	}

	@Override
	public List<Lease> listLeaseHistory() {
		List<Lease> lease=new ArrayList<>();
		DBConnection c=new DBConnection();
		String query="select * from Lease l Inner join Vehicle v ON l.VehicleID=v.VehicleID Inner Join Customer c ON l.CustomerID=c.CustomerID";
		try
		{
			ResultSet rs=c.s.executeQuery(query);
			while(rs.next())
			{
			Customer customer=	new Customer(rs.getInt("CustomerID"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getLong("PhoneNumber"));
			Vehicle vehicle =new Vehicle(rs.getInt("VehicleID"),rs.getString("Make"),rs.getString("Model"),rs.getInt("year and h"),rs.getDouble("dailyRate"),rs.getBoolean("status"),rs.getInt("PassengerCapacity"),rs.getInt("engineCapacity"));
				lease.add(new Lease(rs.getInt("LeaseID"),rs.getInt("CustomerID"),rs.getInt("VehicleID"),rs.getDate("startDate"),rs.getDate("EndDate")));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return lease;
	}

	@Override
	public void recordPayment(int LeaseID) {
		DBConnection c=new DBConnection();
		String query = "INSERT INTO payment (leaseID) VALUES ("+LeaseID +")";
		String query1="UPDATE payment AS p INNER JOIN lease AS l ON p.LeaseID = l.LeaseID INNER JOIN vehicle AS v ON l.VehicleID = v.VehicleID SET p.amount = (DATEDIFF(CURDATE(), l.startDate) + 1) * v.DailyRate,p.PaymentDate=CURDATE() WHERE p.LeaseID ='"+LeaseID+"'";
		try
		{
			c.s.executeUpdate(query);
			c.s.executeUpdate(query1);
			System.out.println("Payment created");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void addVehicle(Vehicle vehicle) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVehicle(int vehicleID) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vehicle> listAvailableVehicles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> listRentedVehicles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle findVehicleById(int vehicleID) throws CarNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lease createLease(int customerID, int vehicleID, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void returnVehicle(int leaseID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recordPayment(Lease lease, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Lease> listActiveLeases() {
		// TODO Auto-generated method stub
		return null;
	}

}
































//package dao;
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import Utils.DBConnection;
//
//import Model.Customer;
//import Model.Lease;
//import Model.Vehicle;
//import Exception.CarNotFoundException;
//import Exception.CustomerNotFoundException;
//
//public class ICarLeaseImpl implements ICarLease {
////    private List<Vehicle> vehicleList = new ArrayList<>();
//    Connection connection; 
//    
//
//    public ICarLeaseImpl() throws SQLException {
//    	connection = DBConnection.getConnection();
//
//    }
//  
//    @Override
//    public void addCar(Vehicle car) throws SQLException {
//        String query = "INSERT INTO vehicle (vehicleID, brand, model, year, dailyRate, status, passengerCapacity, engineCapacity) " +
//                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, car.getVehicleID());
//            preparedStatement.setString(2, car.getBrand());
//            preparedStatement.setString(3, car.getModel());
//            preparedStatement.setInt(4, car.getYear());
//            preparedStatement.setDouble(5, car.getDailyRate());
//            preparedStatement.setBoolean(6, car.isAvailable());
//            preparedStatement.setInt(7, car.getPassengerCapacity());
//            preparedStatement.setDouble(8, car.getEngineCapacity());
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Car added successfully.");
//            } else {
//                System.out.println("Failed to add car.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error adding car to the database: " + e.getMessage());
//            throw e; // Re-throw the exception to indicate the error to the calling code
//        }
//    }
//
//
//    @Override
//    public void removeVehicle(int vehicleID) throws SQLException {
//    	Statement statement = connection.createStatement();
//        String query = "DELETE FROM Vehicle WHERE vehicleID = '" + vehicleID + "'";
//
//        try {
//            int rowsAffected = statement.executeUpdate(query);
//
//            if (rowsAffected > 0) {
//                System.out.println("Vehicle with ID " + vehicleID + " removed successfully.");
//            } else {
//                System.out.println("Vehicle with ID " + vehicleID + " not found.");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public List<Vehicle> listAvailableVehicles() {
//        List<Vehicle> availableVehicles = new ArrayList<>();
//        try {
//            String query = "SELECT * FROM Vehicle WHERE isAvailable = true";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//                 ResultSet resultSet = preparedStatement.executeQuery()) {
//
//                while (resultSet.next()) {
//                    Vehicle vehicle = new Vehicle(
//                            resultSet.getInt("vehicleID"),
//                            resultSet.getString("brand"),
//                            resultSet.getString("model"),
//                            resultSet.getInt("year"),
//                            resultSet.getDouble("dailyRate"),
//                            resultSet.getBoolean("isAvailable"),
//                            resultSet.getInt("passengerCapacity"),
//                            resultSet.getInt("engineCapacity")
//                    );
//
//                    availableVehicles.add(vehicle);
//                }
//            }
//        } catch (SQLException e) {
//            // Handle SQL exception (e.g., log it or throw a custom exception)
//            e.printStackTrace();
//        }
//
//        return availableVehicles;
//    }
//
//
//    @Override
//    public List<Vehicle> listRentedVehicles() {
//        List<Vehicle> rentedVehicles = new ArrayList<>();
//
//        try {
//            // Assuming you have a 'Lease' table with a column 'vehicleID' indicating the rented vehicles
//            String query = "SELECT v.* FROM Vehicle v JOIN Lease l ON v.vehicleID = l.vehicleID";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//                 ResultSet resultSet = preparedStatement.executeQuery()) {
//
//                while (resultSet.next()) {
//                    // Assuming you have a constructor in Vehicle class to create an instance from ResultSet
//                    Vehicle rentedVehicle = new Vehicle(
//                            resultSet.getInt("vehicleID"),
//                            resultSet.getString("brand"),
//                            resultSet.getString("model"),
//                            resultSet.getInt("year"),
//                            resultSet.getDouble("dailyRate"),
//                            resultSet.getBoolean("isAvailable"),
//                            resultSet.getInt("passengerCapacity"),
//                            resultSet.getInt("engineCapacity")
//                    );
//
//                    rentedVehicles.add(rentedVehicle);
//                }
//            }
//        } catch (SQLException e) {
//            // Handle SQL exception (e.g., log it or throw a custom exception)
//            e.printStackTrace();
//        }
//
//        return rentedVehicles;
//    }
//
//    @Override
//    public Vehicle findVehicleById(int vehicleID) throws CarNotFoundException {
//        String query = "SELECT * FROM Vehicle WHERE vehicleID = '" + vehicleID + "'";
//        Vehicle foundVehicle = null;
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            if (resultSet.next()) {
//                foundVehicle = new Vehicle(
//                        resultSet.getInt("vehicleID"),
//                        resultSet.getString("brand"),
//                        resultSet.getString("model"),
//                        resultSet.getInt("year"),
//                        resultSet.getDouble("dailyRate"),
//                        resultSet.getBoolean("isAvailable"),
//                        resultSet.getInt("passengerCapacity"),
//                        resultSet.getInt("engineCapacity")
//                );
//            } else {
//                // If no results found, throw CarNotFoundException
//                throw new CarNotFoundException("Car with ID " + vehicleID + " not found.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error finding vehicle by ID: " + e.getMessage());
//        }
//
//        return foundVehicle;
//    }
//
//
//
//    @Override
//    public void addCustomer(Customer customer) throws SQLException {
//        String query = "INSERT INTO Customer (customerID, firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, customer.getCustomerID());
//            preparedStatement.setString(2, customer.getFirstName());
//            preparedStatement.setString(3, customer.getLastName());
//            preparedStatement.setString(4, customer.getEmail());
//            preparedStatement.setInt(5, customer.getPhoneNumber());
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Customer added successfully: " + customer.displayCustomerDetails());
//            } else {
//                System.out.println("Failed to add customer: " + customer.displayCustomerDetails());
//            }
//        } catch (SQLException e) {
//            System.out.println("Error adding customer: " + e.getMessage());
//        }
//    }
//    @Override
//    public void removeCustomer(int customerID) {
//        String query = "DELETE FROM Customer WHERE customerID = ?";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, customerID);
//
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            if (rowsAffected > 0) {
//                System.out.println("Customer with ID " + customerID + " removed successfully.");
//            } else {
//                System.out.println("Customer with ID " + customerID + " not found.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error removing customer: " + e.getMessage());
//        }
//    }
//
//
//    @Override
//    public List<Customer> listCustomers() {
//        List<Customer> customerList = new ArrayList<>();
//        String query = "SELECT * FROM Customer";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            while (resultSet.next()) {
//                int customerID = resultSet.getInt("customerID");
//                String firstName = resultSet.getString("firstName");
//                String lastName = resultSet.getString("lastName");
//                String email = resultSet.getString("email");
//                int phoneNumber = resultSet.getInt("phoneNumber");
//
//                Customer customer = new Customer(customerID, firstName, lastName, email, phoneNumber);
//                customerList.add(customer);
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error listing customers: " + e.getMessage());
//        }
//
//        return customerList;
//    }
//
//
//    @Override
//    public Customer findCustomerById(int customerID) throws CustomerNotFoundException {
//        String query = "SELECT * FROM Customer WHERE customerID = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, customerID);
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    String firstName = resultSet.getString("firstName");
//                    String lastName = resultSet.getString("lastName");
//                    String email = resultSet.getString("email");
//                    int phoneNumber = resultSet.getInt("phoneNumber");
//
//                    return new Customer(customerID, firstName, lastName, email, phoneNumber);
//                }else {
//                    // If no results found, throw CarNotFoundException
//                    throw new CustomerNotFoundException("Car with ID " + customerID + " not found.");
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error finding customer by ID: " + e.getMessage());
//        }
//
//        return null; // Return null if customer is not found or an error occurs
//    }
//
//
//    @Override
//    public Lease createLease(int customerID, int vehicleID, Date startDate, Date endDate) {
//        try {
//            String query = "INSERT INTO Lease (customerID, vehicleID, startDate, endDate) VALUES (" +
//                    customerID + ", " +
//                    vehicleID + ", " +
//                    "'" + startDate + "', " + 
//                    "'" + endDate + "')";
//
//            Statement statement = connection.createStatement();
//            int rowsAffected = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
//
//            if (rowsAffected > 0) {
//                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                    if (generatedKeys.next()) {
//                        int leaseID = generatedKeys.getInt(1);
//                        return new Lease(leaseID, customerID, vehicleID, startDate, endDate);
//                    }
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error creating lease: " + e.getMessage());
//        }
//
//        return null;
//    }
//
//
//
//    @Override
//    public void returnVehicle(int leaseID) {
//        // TODO: Implement logic to return the leased vehicle
//
//        try {
//            // Assuming you have a Lease table with a column 'isReturned' to track the return status
//            String updateQuery = "UPDATE Lease SET isReturned = true WHERE leaseID = ?";
//            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
//                updateStatement.setInt(1, leaseID);
//                int rowsAffected = updateStatement.executeUpdate();
//
//                if (rowsAffected > 0) {
//                    System.out.println("Vehicle with Lease ID " + leaseID + " has been successfully returned.");
//                } else {
//                    System.out.println("Failed to return vehicle. Lease ID " + leaseID + " not found.");
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error returning vehicle: " + e.getMessage());
//        }
//    }
//
//
//    @Override
//    public List<Lease> listActiveLeases() {
//        List<Lease> activeLeases = new ArrayList<>();
//
//        try {
//            String query = "SELECT * FROM Lease WHERE isReturned = false";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//                 ResultSet resultSet = preparedStatement.executeQuery()) {
//
//                while (resultSet.next()) {
//                    Lease lease = new Lease(
//                            resultSet.getInt("leaseID"),
//                            resultSet.getInt("customerID"),
//                            resultSet.getInt("carID"),
//                            resultSet.getDate("startDate").toLocalDate(),
//                            resultSet.getDate("endDate").toLocalDate(),
//                            resultSet.getDouble("totalCost")
//                    );
//
//                    activeLeases.add(lease);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error listing active leases: " + e.getMessage());
//        }
//
//        return activeLeases;
//    }
//
//    @Override
//    public List<Lease> listLeaseHistory() {
//        List<Lease> leaseHistory = new ArrayList<>();
//        
//        try {
//            // Assuming you have a LeaseHistory table with appropriate columns
//            String query = "SELECT * FROM LeaseHistory";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//                 ResultSet resultSet = preparedStatement.executeQuery()) {
//
//                while (resultSet.next()) {
//                    int leaseID = resultSet.getInt("leaseID");
//                    int customerID = resultSet.getInt("customerID");
//                    int vehicleID = resultSet.getInt("vehicleID");
//                    LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
//                    LocalDate endDate = resultSet.getDate("endDate").toLocalDate();
//                    double totalCost = resultSet.getDouble("totalCost");
//
//                    Lease lease = new Lease(leaseID, customerID, vehicleID, startDate, endDate, totalCost);
//                    leaseHistory.add(lease);
//                }
//            }
//        } catch (SQLException e) {
//            // Handle SQL exception (e.g., log it or throw a custom exception)
//            e.printStackTrace();
//        }
//
//        return leaseHistory;
//    }
//
//
//    @Override
//    public void recordPayment(Lease lease, double amount) {
//        try {
//            // Assuming you have a Payment table with appropriate columns
//            String query = "INSERT INTO Payment (leaseID, paymentAmount, paymentDate) VALUES (?, ?, NOW())";
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//                preparedStatement.setInt(1, lease.getLeaseID());
//                preparedStatement.setDouble(2, amount);
//
//                int rowsAffected = preparedStatement.executeUpdate();
//
//                if (rowsAffected > 0) {
//                    System.out.println("Payment recorded successfully for Lease ID: " + lease.getLeaseID());
//                } else {
//                    System.out.println("Failed to record payment for Lease ID: " + lease.getLeaseID());
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error recording payment: " + e.getMessage());
//        }
//    }
//	@Override
//	public void addVehicle(Vehicle vehicle) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//}
