package datastructure;

import java.time.LocalDate;
import java.util.Scanner;

public class E_commerce 
{

    private static LinkedList<Customer> customers_list;
    private static LinkedList<Order> orders_list;
    private static LinkedList<Product> products_list;
    private static LinkedList<Review> reviews_list; 
    
    private static Reviews all_Reviews;
    private static Customers all_Customers;
    private static Orders all_Orders;
    private static Products all_products;
    
    public E_commerce()
    {
    customers_list=new LinkedList<Customer>();
    orders_list=new LinkedList<Order>();
    products_list=new LinkedList<Product>();
    reviews_list=new LinkedList<Review>();
    
    
    all_products=new Products(products_list);
    all_Customers=new Customers(customers_list);
    all_Orders=new Orders(customers_list,orders_list);
    all_Reviews=new Reviews(reviews_list,products_list,customers_list);   
  
    }

     public static void Load_all()
    {    
    	 System.out.println(" Loading Products and Customers...");
        
         all_products.loadProducts("C:\\Users\\win\\Documents\\NetBeansProjects\\212project2025\\prodcuts.csv");
         all_Customers.loadCustomers("C:\\Users\\win\\Documents\\NetBeansProjects\\212project2025\\customers.csv");
         
         System.out.println(" Loading Reviews and Orders...");
         all_Reviews.load_revews("C:\\Users\\win\\Documents\\NetBeansProjects\\212project2025\\reviews.csv");
         all_Orders.loadOrders("C:\\Users\\win\\Documents\\NetBeansProjects\\212project2025\\Orders.csv");  
         System.out.println(" All data loaded successfully.");
      }
    
   
    
     public static void add_Customer(Customer c) { all_Customers.addCustomer(c); }
     public static void add_Product(Product p) { all_products.addProduct(p); }
     public static void add_Order(Order o) { all_Orders.addOrder(o); }
     public static void add_Review(Review r) { all_Reviews.addReview(r); }
       
     
     
     
       public void displayTop3Products() {
    if (products_list.isEmpty()) {
        System.out.println("No products available.");
        return;
    }

    Product max1 = null, max2 = null, max3 = null;
    
    products_list.findFirst();
    while (true) {
        Product cur = products_list.retrieve();

        if (max1 == null || cur.getAverageRate() > max1.getAverageRate()) {
            max3 = max2;
            max2 = max1;
            max1 = cur;
        } else if (max2 == null || cur.getAverageRate() > max2.getAverageRate()) {
            max3 = max2;
            max2 = cur;
        } else if (max3 == null || cur.getAverageRate() > max3.getAverageRate()) {
            max3 = cur;
        }

        if (products_list.last()) break;
        products_list.findNext();
    }

    System.out.println("\n Top Products by Average Rating:");

    int rank = 1;
    if (max1 != null) {
        System.out.println(rank++ + ". Product ID: " + max1.getProductID()
                + " | Name: " + max1.getName()
                + " | Avg Rating: " + String.format("%.2f", max1.getAverageRate()));
    }
    if (max2 != null) {
        System.out.println(rank++ + ". Product ID: " + max2.getProductID()
                + " | Name: " + max2.getName()
                + " | Avg Rating: " + String.format("%.2f", max2.getAverageRate()));
    }
    if (max3 != null) {
        System.out.println(rank++ + ". Product ID: " + max3.getProductID()
                + " | Name: " + max3.getName()
                + " | Avg Rating: " + String.format("%.2f", max3.getAverageRate()));
    }


    System.out.println("-----------------------------------");
}

       

    public static  void displayAllOrders_between2dates(LocalDate d1,LocalDate d2) {
        if (orders_list.isEmpty()) {
            System.out.println(" No all_orders found!");
            return;
        }

        System.out.println("--------------------------------------------------------------------------");

        orders_list.findFirst();
        
        while (true) {
            Order o = orders_list.retrieve();
            if (!o.getOrderDate().isBefore(d1) && !o.getOrderDate().isAfter(d2)) {
                   System.out.println(o.getOrderID());
       
             
            }
            if (orders_list.last())
                break;
            orders_list.findNext();
        }

        System.out.println("--------------------------------------------------------------------------");
    }

    public void showCommonHighRatedProducts(int customerId1, int customerId2) {
    System.out.println("===  Common Products Reviewed by Both Customers (Avg > 4) ===");

    if (products_list == null || products_list.isEmpty()) {
        System.out.println("⚠ No products available.");
        return;
    }

    if (reviews_list == null || reviews_list.isEmpty()) {
        System.out.println(" No reviews available.");
        return;
    }

    boolean found = false;

    products_list.findFirst();
    while (true) {
        Product p = products_list.retrieve();

        boolean reviewedByFirst = false;
        boolean reviewedBySecond = false;

        reviews_list.findFirst();
        while (true) {
            Review r = reviews_list.retrieve();

            if (r.getProductID() == p.getProductID()) {
                if (r.getCustomerID() == customerId1)
                    reviewedByFirst = true;
                if (r.getCustomerID() == customerId2)
                    reviewedBySecond = true;
            }

            if (reviews_list.last())
                break;
            else
                reviews_list.findNext();
        }

        if (reviewedByFirst && reviewedBySecond && p.getAverageRate() > 4.0) {
            System.out.println("✅ Product: " + p.getName()
                    + " | Avg Rating: " + String.format("%.2f", p.getAverageRate()));
            found = true;
        }

        if (products_list.last())
            break;
        else
            products_list.findNext();
    }

    if (!found)
        System.out.println("No common high-rated products found.");
}

    public static void main(String[] args) 
    {
        E_commerce e1 = new E_commerce();
        Scanner input = new Scanner(System.in);
        int choice = 0;
        
        do {          
            System.out.println("\n--- E-Commerce System Menu ---");
            System.out.println("1). Load All Data (CSV)");
            System.out.println("2). Add Product");
            System.out.println("3). Add Customer");
            System.out.println("4). Add Order");
            System.out.println("5). Add Review");
            System.out.println("6). Display All Customers");
            System.out.println("7). Display Top 3 Products");
            System.out.println("8). Display All Orders");
            System.out.println("9). Display Orders Between 2 Dates ");
            System.out.println("10). Show Common High-Rated Products ");
            System.out.println("100). Exit");
            
            System.out.print("enter your choice: ");
            
            if (!input.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); 
                continue;
            }
            choice = input.nextInt();
            input.nextLine(); 
            switch (choice) {
                case 1:
                     Load_all();
                    break;                   
                case 2:
                  
                    System.out.print("Enter Product ID (int): ");
                    int p_id = input.nextInt(); input.nextLine(); 
                    System.out.print("Enter Name (String): ");
                    String p_name = input.nextLine();
                    System.out.print("Enter Price (double): ");
                    double p_price = input.nextDouble(); input.nextLine(); 
                    System.out.print("Enter Stock (int): ");
                    int p_stock = input.nextInt(); input.nextLine(); 
                    Product p=new Product(p_id, p_name, p_price, p_stock);
                    add_Product(p);                   
                    break;
                case 3:                    
                  
                    System.out.print("Enter Customer ID (int): ");
                    int c_id = input.nextInt(); input.nextLine(); 
                    System.out.print("Enter Name (String): ");
                    String c_name = input.nextLine();
                    System.out.print("Enter Email (String): ");
                    String c_email = input.nextLine();
                    Customer c=new Customer(c_id, c_name, c_email);
                   add_Customer(c);
                    break;
                case 4:
                  
                    System.out.print("Enter Order ID (int): ");
                    int o_id = input.nextInt(); input.nextLine();
                    System.out.print("Enter Customer ID (int): ");
                    int o_cid = input.nextInt(); input.nextLine();
                    System.out.print("Enter Product IDs (e.g., 101;102): ");
                    String o_pids = input.nextLine();
                    System.out.print("Enter Total Price (double): ");
                    double o_price = input.nextDouble(); input.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String o_date_str = input.nextLine();
                    System.out.print("Enter Status (e.g., Pending): ");
                    String o_status = input.nextLine();
                    Order o=new Order(o_id, o_cid, o_pids, o_price, LocalDate.parse(o_date_str), o_status);
                   add_Order(o);
                    break;
                case 5:
             
                    System.out.print("Enter Review ID (int): ");
                    int r_rid = input.nextInt(); input.nextLine();
                    System.out.print("Enter Product ID (int): ");
                    int r_pid = input.nextInt(); input.nextLine();
                    System.out.print("Enter Customer ID (int): ");
                    int r_cid = input.nextInt(); input.nextLine();
                    System.out.print("Enter Rating (1-5): ");
                    int r_rating = input.nextInt(); input.nextLine();
                    System.out.print("Enter Comment: ");
                    String r_comment = input.nextLine();
                    Review r=new Review(r_rid, r_pid, r_cid, r_rating, r_comment);
                    add_Review(r);
                    break;
                case 6:
                 all_Customers.displayAll();
                    break;
                case 7:
                 e1.displayTop3Products();
                    break;
                case 8:
                    all_Orders.displayAllOrders();
                    break;
                case 9:
                    
                    System.out.print("Enter Start Date (YYYY-MM-DD): ");
                    LocalDate d1 = LocalDate.parse(input.nextLine());
                    System.out.print("Enter End Date (YYYY-MM-DD): ");
                    LocalDate d2 = LocalDate.parse(input.nextLine());
                    displayAllOrders_between2dates(d1, d2);
                    break; 
                case 10:
                    System.out.print("Enter first customer ID: ");
                    int c1 = input.nextInt(); input.nextLine();
                    System.out.print("Enter second customer ID: ");
                    int c2 = input.nextInt(); input.nextLine();
                    e1.showCommonHighRatedProducts(c1, c2);
                    break;
                
                case 100:
                    System.out.println("goodbye");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice!= 100);
    }
    
}
