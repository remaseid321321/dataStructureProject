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
    
    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
     public Orders(LinkedList<Customer> input_customers,LinkedList<Order> input_orders_list) {
        customerLinker = new Customers(input_customers) ;
        this.orderListMaster = input_orders_list;
    }
    
    public Orders() {
        customerLinker = new Customers();
        orderListMaster = new LinkedList<>();      
    }
    
	 public LinkedList<Order>get_Orders()
	{
	return orderListMaster;
	}
	 
	 
	 public Order searchOrderById(int id) {
	        if (orderListMaster.isEmpty()) return null;
	
	        orderListMaster.findFirst();
	        while (true) { 
	            Order o = orderListMaster.retrieve();
	            if (o.getOrderID() == id) {
	                return o;
	            }
	            if (orderListMaster.last()) break;
	            orderListMaster.findNext();
	        }
            
            if (orderListMaster.retrieve().getOrderID() == id) {
                return orderListMaster.retrieve();
            }
	        return null;
	 }
	 
    public void addOrder(Order newOrder) {
        if (searchOrderById(newOrder.getOrderID())==null) {
            orderListMaster.addLast(newOrder);
        } else {
            System.out.println("Order ID " + newOrder.getOrderID() + " already exists!");
        }
    }
	 
	public void loadOrders(String filePath) {
	    try {
	        File file = new File(filePath);
	        Scanner reader = new Scanner(file);
	        System.out.println("Loading orders from: " + filePath);
	        
   
	        if (reader.hasNextLine()) reader.nextLine(); 

	        while (reader.hasNextLine()) {
	            String line = reader.nextLine().trim();
	            if (!line.isEmpty()) {
	                Order newOrder = parseOrderFromCSV(line); 
	                if (newOrder != null) {
                        orderListMaster.addLast(newOrder); 
	                }
	            }
	        }
	        reader.close();
	        System.out.println("Orders file loaded successfully.");
	    } catch (FileNotFoundException e) {
	        System.out.println(" File not found: " + filePath);
	    } catch (Exception e) {

	        System.out.println("An unexpected error occurred during order loading: " + e.getMessage());
	    }
	}
	 
  
	private Order parseOrderFromCSV(String line) {
        String[] parts = line.split(","); 

        if (parts.length < 6) {
            System.err.println("Skipping malformed order line: " + line);
            return null;
        }
        
        try {
            int orderId = Integer.parseInt(parts[0].trim());
            int customerId = Integer.parseInt(parts[1].trim());
            String productIds = parts[2].trim();
            double totalPrice = Double.parseDouble(parts[3].trim());
            
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
            System.out.println(" No orders found!"); 
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
    

  public static void test1() {
        Orders os = new Orders();
     

        os.orderListMaster.addLast(new Order(501, 101, "201;202;203", 4999.99,LocalDate.of(2024, 1, 1), "Delivered"));
        os.orderListMaster.addLast(new Order(502, 102, "301;302", 1899.50,LocalDate.of(2024, 1, 1), "Pending"));

        
        os.displayAllOrders();
    }
    public static void test2() {
        Orders os = new Orders();
       
        os.loadOrders("C:\\Users\\win\\Documents\\NetBeansProjects\\212project2025\\orders.csv");
        os.displayAllOrders();
        
    }  

    public static void main(String[] args) {
        //test1();
        test2();
        
    }
}

