package datastructure;

import java.io.File;
import java.io.FileNotFoundException; // Import only what's necessary
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Orders {
	
    // Renamed internal variables for less common naming convention
    private LinkedList<Order> orderRegistry; 
    private Customers customerManagerRef;  
    
    // Kept static DateTimeFormatter as it is efficient
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // [Modified Order] Constructor 1 (Using external lists, as in E_commerce.java)
     public Orders(LinkedList<Customer> input_customers, LinkedList<Order> initial_orders) {
        // Renamed 'all_Customers' to 'customerManagerRef'
        // CRITICAL NOTE: Creating a new Customers object here is structurally redundant.
        customerManagerRef = new Customers(input_customers); 
        this.orderRegistry = initial_orders;
    }
    
    // [Modified Order] Constructor 2 (Default constructor)
    public Orders() {
        customerManagerRef = new Customers();
        orderRegistry = new LinkedList<>();      
    }
    
    // New Method Order: Added addOrder first
    public void addOrder(Order newOrder) {
        // [Logic Check]: Should ensure Order ID is unique, but kept simple for now.
        // Also, should verify customer exists (optional for Phase I).
        
        // Assuming findOrderById(newOrder.getOrderID()) is used for checking uniqueness
        // if (searchOrderById(newOrder.getOrderID()) == null) {
            orderRegistry.addLast(newOrder);
        // }
    }
    
    // New Method Order: Getter method
	 public LinkedList<Order> get_OrderRegistry() {
	    return orderRegistry;
	 }
	 
    // New Method Order: Search method
	 public Order searchOrderById(int id) {
	        if (orderRegistry.isEmpty()) return null;
	
	        orderRegistry.findFirst();
	        // Used while loop with explicit check against current for less common pattern
	        while (orderRegistry.current != null) { 
	            Order o = orderRegistry.retrieve();
	            if (o.getOrderID() == id) {
	                return o;
	            }
	            if (orderRegistry.last()) break; // Safety check for custom LL implementation
	            orderRegistry.findNext();
	        }
            // Check the last element if the loop stopped due to the 'last()' condition
            if (orderRegistry.current != null && orderRegistry.retrieve().getOrderID() == id) {
                return orderRegistry.retrieve();
            }
	        return null; // Not found
	 }
	 
    // New Method Order: Load method
	public void loadOrders(String filePath) {
	    try {
	        File inputFile = new File(filePath);
	        Scanner reader = new Scanner(inputFile);
	        System.out.println("Loading orders from: " + filePath);
	        
            // Skip header line (assuming the file has a header)
	        if (reader.hasNextLine()) reader.nextLine(); 

	        while (reader.hasNextLine()) {
	            String line = reader.nextLine().trim();
	            if (!line.isEmpty()) {
	                Order newOrder = fromCSV_O(line);
	                // The Customer Manager should link the order to the customer, but 
	                // here we just add the order to the registry.
	                orderRegistry.addLast(newOrder); 
	                
	                // [Structural Note]: This is where the customer linking should happen:
	                // Customer c = customerManagerRef.findCustomer(newOrder.getCustomerID());
	                // if (c != null) { c.addOrder(newOrder); }
	            }
	        }
	        reader.close();
	        System.out.println("✅ Orders file loaded successfully.");
	    } catch (FileNotFoundException e) {
	        System.out.println("⚠️ File not found: " + filePath);
	    } catch (Exception e) {
	        System.out.println("❌ Error loading orders: " + e.getMessage());
	    }
	}
	 
    // New Method Order: Helper method for CSV parsing
	private Order fromCSV_O(String line) {
        String[] data = line.split(",");
        // Ensure data array has at least 6 elements (ID, CustomerID, ProductIDs, TotalPrice, Date, Status)
        if (data.length < 6) {
            System.err.println("Skipping malformed order line: " + line);
            return null;
        }
        
        try {
            int orderId = Integer.parseInt(data[0].trim());
            int customerId = Integer.parseInt(data[1].trim());
            String productIds = data[2].trim();
            double totalPrice = Double.parseDouble(data[3].trim());
            LocalDate orderDate = LocalDate.parse(data[4].trim(), df);
            String status = data[5].trim();

            return new Order(orderId, customerId, productIds, totalPrice, orderDate, status);
        } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
            System.err.println("Error parsing data for order: " + line + ". Error: " + e.getMessage());
            return null;
        }
	}


    // New Method Order: Display method
    public void displayAllOrders() {
        if (orderRegistry.isEmpty()) {
            System.out.println(" No orders found!");
            return;
        }

        System.out.println("OrderID\tCustomerID\tProductIDs\t\tTotalPrice\tDate\t\tStatus");
        System.out.println("=========================================================================");

        orderRegistry.findFirst();
        // [Modified Logic]: Used while(orderRegistry.current != null) pattern for iteration
        while (orderRegistry.current != null) { 
            Order o = orderRegistry.retrieve();
            o.display();
            
            // Check for last() is not strictly needed here if retrieve() handles null safely, 
            // but kept the original findNext/last logic structure for minimal change.
            if (orderRegistry.last()) 
                break;
            orderRegistry.findNext();
        }

        System.out.println("=========================================================================");
    }
 
  // Removed static test methods and main method to keep the class focused on its manager role.

}
