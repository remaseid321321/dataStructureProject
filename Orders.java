/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructure;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import javafx.util.converter.LocalDateTimeStringConverter;

public class Orders {
	
    private LinkedList<Order> all_orders;
    //كل كوستمر عنده مجموعه من الاوردرز , ف هنا اوبجكت من نوع كوستمر يحتوي على كل الكوستمر الي بدورهم يملك كل واحد منهم عدة اوردرز
    private Customers all_Customers;  
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
     public Orders(LinkedList<Customer> input_customers,LinkedList<Order> all_orders) {
        all_Customers =new Customers(input_customers) ;
        this.all_orders=all_orders;
    }
    
    public Orders() {
        all_Customers=new Customers();
        all_orders = new LinkedList<>();      
    }
public LinkedList<Order>get_Orders()
{
return all_orders;
}
 public Order searchOrderById(int id) {
        if (all_orders.isEmpty()) return null;

        all_orders.findFirst();
        while (true) {
            Order o = all_orders.retrieve();
            if (o.getCustomerID() == id)
                return o;
            if (all_orders.last())
                break;
            all_orders.findNext();
        }
        return null;
    }
 public  void assign(Order ord){
       Customer p= all_Customers.findCustomer(ord.getCustomerID());
       if(p==null)
              System.out.println("not exist to assign review "+ord.getCustomerID()+"to it");
       else
           p.addOrder(ord);
     }
 public void addOrder(Order ord) {
        if (searchOrderById(ord.getCustomerID()) == null) { 
            all_orders.addLast(ord);
            assign(ord);           
           // System.out.println("Order added successfully: " + ord.getOrderId());
        } else {
            System.out.println("Order with ID " + ord.getCustomerID() + " already exists!");
        }
    }
 public static Order convert_String_to_product(String Line)
    {
          String a[]=Line.split(",");              
             int orderId = Integer.parseInt(a[0].trim().replace("\"", ""));            
            int customerId = Integer.parseInt(a[1].trim().replace("\"", ""));            
             String productIds = a[2].trim().replace("\"", "");            
            double totalPrice = Double.parseDouble(a[3]);             
            LocalDate date = LocalDate.parse(a[4], df);                
           String status = a[5].trim();

            Order ord = new Order(orderId, customerId, productIds, totalPrice, date, status);
          
    return ord;
    }
   public void loadOrders(String fileName) {
    try {
        File f = new File(fileName);
        Scanner read = new Scanner(f);        
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("📂 Reading file: " + fileName);
        System.out.println("-----------------------------------");
        read.nextLine();
        while (read.hasNextLine()) {
            String line = read.nextLine().trim();                               
           Order ord=convert_String_to_product(line);
           addOrder(ord);
        }

        read.close();
        System.out.println("File loaded successfully!\n");

    } catch (Exception e) {
        System.out.println("Error loading all_orders: " + e.getMessage());
    }
}

    
    public void displayAllOrders() {
        if (all_orders.isEmpty()) {
            System.out.println(" No all_orders found!");
            return;
        }

        System.out.println("OrderID\tCustomerID\tProductIDs\t\tTotalPrice\tDate\t\tStatus");
        System.out.println("--------------------------------------------------------------------------");

       // SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        all_orders.findFirst();
        while (true) {
            Order o = all_orders.retrieve();
            o.display();
            if (all_orders.last())
                break;
            all_orders.findNext();
        }

        System.out.println("--------------------------------------------------------------------------");
    }
 

  public static void test1() {
        Orders os = new Orders();
     

        os.all_orders.addLast(new Order(501, 101, "201;202;203", 4999.99,LocalDate.of(2024, 1, 1), "Delivered"));
        os.all_orders.addLast(new Order(502, 102, "301;302", 1899.50,LocalDate.of(2024, 1, 1), "Pending"));

        
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

