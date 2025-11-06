
package datastructure;

import java.io.File;
import java.util.Scanner;

public class Customer {
    private int customerID;
    private String name;
    private String email;
    private LinkedList<Review> reviews;
    private LinkedList<Order> orders;
        
    public Customer(int CID, String n, String e) {
        customerID=CID;
        name= n;
        email =e;
        reviews=new LinkedList<>();
        orders=new LinkedList<>();
    }

    public int getCustomerID() {
        return customerID; }
    
    public String getName() { 
        return name; }
    
    public String getEmail() {
        return email; }

    public void addOrder(Order o) { 
        orders.insert(o); 
    }
    
    public void addReview(Review r) { 
       reviews.insert(r); 
    }
    
public void displayReviews() {
        if (reviews.isEmpty()) {
            System.out.println(" there is No reviews for" + name);
        }
        else {
            System.out.println( name + " Reviews is :");
            reviews.findFirst();
            while (!reviews.last()) {
                Reviews r =reviews.retrieve();
                r.display();
                reviews.findNext();
            }
                 Reviews r =reviews.retrieve();
                 r.display();      
        }

    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No order was found for" + name);
        }
        else{
        System.out.println( name + "Orders is:");
        orders.findFirst();
        while (!orders.last()) {
            Order o=orders.retrieve();
            o.display();
            orders.findNext();
        }
               Order o=orders.retrieve();
               o.display();
    }
    }

    public void display() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("******************************");
        System.out.println("******************************");

        displayOrders();
        displayReviews();
    }

    
    public static void test2() {
        
     Customer c1 = new Customer(201, "Omar Hassan", "omar.hassan@gmail.com");
     Customer c2 = new Customer(202, "Nour Adel", "nour.adel@yahoo.com");
        c1.display();
        c2.display();
    }

    public static void main(String[] args) {       
       test2();
    }
}






