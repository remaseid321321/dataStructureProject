package datastructure;

import java.time.LocalDate;
import java.util.Date;

public class Order {
    private int orderID;
    private int customerID;
    private String productIDs; 
    private double totalPrice;
    private LocalDate orderDate;    
    private String status;
    private LinkedList<Integer>productIds;

    public Order(int OID, int CID, String PIDs, double t,LocalDate d, String s) {
        orderID = OID;
        customerID = CID;
        productIDs=PIDs;       
        totalPrice = t;
        orderDate =d;
        status = s;
        productIds= new LinkedList<>();
         addIDs(productIDs);
    }
    public void addIDs(String IDs){
        String [] part=IDs.split(";");
        for(int i=1;i<=a.length;i++){
            int id= Integer.parseInt(part[i].trim())
          productIds.insert(id);
        }
    } 
    
    public void addID(int id) {
    productIds.insert(id);
    }
    
    public void UpdateOrder (Order o) {
        orderID =o.orderID;
        customerID = o.customerID;
        productIDs = o.productIDs;
        totalPrice =o.totalPrice;
        orderDate = o.orderDate;
        status=  o.status;
        productIds =o.productIds;
    }

    public int getOrderID() {
        return orderID; }
    
    public int getCustomerID() {
        return customerID; }
    
    public String getProducIDs() { 
        return productIDs; }
    
    public LinkedList<Integer> getProductIds() { 
        return productIds; }
    
    public double getTotalPrice() {
        return totalPrice; }
    
    public  LocalDate getOrderDate() { 
        return orderDate; }
    
    public String getStatus() {
        return status; }

 
    public void setStatus(String s) { 
        status = s; }


    public void display() {
        System.out.println("Order ID: " +orderID);
        System.out.println("Customer ID: "+customerID);
        System.out.print("Product IDs: ");
        productIds.display();
        System.out.println("");
        System.out.println("Total Price:"+ totalPrice);
        System.out.println("Date: "+ orderDate);
        System.out.println("Status: "+status);
        System.out.println("*****************************");
    }  

    public static void main(String[] args) {
        Order ord1=new Order(501, 101, "201;202;203", 4999.99,LocalDate.of(2024, 1, 1), "Delivered");
        Order ord2=new Order(502, 102, "301;302", 1899.50,LocalDate.of(2024, 1, 1), "Pending");       
        ord1.display();
        ord2.display();
    }
}

