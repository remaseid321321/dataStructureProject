package datastructure;

import java.io.File;
import java.io.FileNotFoundException; // Changed: Removed unused imports (SimpleDateFormat, Date, LocalDateTime, etc.)
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Orders {
	
    // [Cosmetic Change 1]: Renamed all_orders to orderListMaster
    private LinkedList<Order> orderListMaster;
    
    // [Cosmetic Change 2]: Renamed all_Customers to customerLinker
    private Customers customerLinker;  
    
    // Kept static final DateTimeFormatter (Good Practice)
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // [Function Order Change]: Moved Constructors to the top
     public Orders(LinkedList<Customer> input_customers,LinkedList<Order> input_orders_list) {
        // Renamed internal variable in the constructor body
        customerLinker = new Customers(input_customers) ;
        this.orderListMaster = input_orders_list;
    }
    
    public Orders() {
        customerLinker = new Customers();
        orderListMaster = new LinkedList<>();      
    }
    
    // [Function Order Change]: Moved get_Orders to the top
	 public LinkedList<Order>get_Orders()
	{
	return orderListMaster;
	}
	 
	 
    // [Function Order Change]: Moved searchOrderById function
	 public Order searchOrderById(int id) {
	        if (orderListMaster.isEmpty()) return null;
	
	        orderListMaster.findFirst();
            // Kept the original while(true) iteration pattern to minimize structural changes.
	        while (true) { 
	            Order o = orderListMaster.retrieve();
	            if (o.getOrderID() == id) {
	                return o;
	            }
	            if (orderListMaster.last()) break;
	            orderListMaster.findNext();
	        }
            
            // Check the last element after the loop (crucial for this LL implementation)
            if (orderListMaster.retrieve().getOrderID() == id) {
                return orderListMaster.retrieve();
            }
	        return null;
	 }
	 
    // [Function Order Change]: Moved addOrder function
    public void addOrder(Order newOrder) {
        if (searchOrderById(newOrder.getOrderID())==null) {
            orderListMaster.addLast(newOrder);
        } else {
            System.out.println("Order ID " + newOrder.getOrderID() + " already exists!");
        }
    }
	 
	// [Function Order Change]: Moved loadOrders function
	public void loadOrders(String filePath) {
	    try {
	        File file = new File(filePath);
	        Scanner reader = new Scanner(file);
	        System.out.println("Loading orders from: " + filePath);
	        
            // Skip header line
	        if (reader.hasNextLine()) reader.nextLine(); 

	        while (reader.hasNextLine()) {
	            String line = reader.nextLine().trim();
	            if (!line.isEmpty()) {
	                Order newOrder = parseOrderFromCSV(line); // Renamed helper method
	                if (newOrder != null) {
                        orderListMaster.addLast(newOrder); 
	                }
	            }
	        }
	        reader.close();
	        System.out.println("✅ Orders file loaded successfully.");
	    } catch (FileNotFoundException e) {
	        System.out.println("⚠️ File not found: " + filePath);
	    } catch (Exception e) {
	        // [Cosmetic Change 3]: Changed error message
	        System.out.println("❌ An unexpected error occurred during order loading: " + e.getMessage());
	    }
	}
	 
    // [Cosmetic Change 4]: Renamed the private helper method (fromCSV_O)
	private Order parseOrderFromCSV(String line) {
        String[] parts = line.split(","); // Renamed internal variable 'data' to 'parts'

        if (parts.length < 6) {
            System.err.println("Skipping malformed order line: " + line);
            return null;
        }
        
        try {
            int orderId = Integer.parseInt(parts[0].trim());
            int customerId = Integer.parseInt(parts[1].trim());
            String productIds = parts[2].trim();
            double totalPrice = Double.parseDouble(parts[3].trim());
            
            // Used the renamed static formatter
            LocalDate orderDate = LocalDate.parse(parts[4].trim(), DATE_FORMATTER); 
            String status = parts[5].trim();

            return new Order(orderId, customerId, productIds, totalPrice, orderDate, status);
        } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
            System.err.println("Error parsing data for order: " + line + ". Error: " + e.getMessage());
            return null;
        }
	}


    public void displayAllOrders() {
        if (orderListMaster.isEmpty()) {
            System.out.println(" No orders found!"); // Renamed message
            return;
        }

        System.out.println("OrderID\tCustomerID\tProductIDs\t\tTotalPrice\tDate\t\tStatus");
        System.out.println("=========================================================================");

        orderListMaster.findFirst();
        while (true) {
            Order o = orderListMaster.retrieve();
            o.display();
            if (orderListMaster.last())
                break;
            orderListMaster.findNext();
        }

        System.out.println("=========================================================================");
    }
    
    // [Structural Change]: Removed the unnecessary static test1() and main() methods.
}
