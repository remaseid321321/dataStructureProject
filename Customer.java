
package datastructure;

import java.io.File;
import java.util.Scanner;

public class Customer {
    private int customerID;
    private String name;
    private String email;
    private LinkedList<Order> orders;
    private LinkedList<Review> reviews;
    
    public Customer(int CID, String n, String e) {
        customerID=CID;
        name= n;
        email =e;
        orders=new LinkedList<>();
        reviews=new LinkedList<>();
    }

    public int getCustomerID() {
        return customerID; }
    
    public String getName() { 
        return name; }
    
    public String getEmail() {
        return email; }

    public void addOrder(Order o) { 
        orders.addLast(o); 
    }
    
    public void addReview(Review r) { 
       reviews.addLast(r); 
    }
    
public void displayReviews() {
        System.out.println( name + "  Reviews is :");
        if (reviews.isEmpty()) {
            System.out.println(" there is No reviews ");
        } else {
            reviews.findFirst();
            while (!reviews.last()) {
                reviews.retrieve().display();
                reviews.findNext();
            }
                            reviews.retrieve().display();
        }

    }

    public void display() {
        System.out.println("Customer ID: " + customerID);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("******************************");
        displayOrders();
        displayReviews();
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No order was found for" + name);
            return;
        }
        System.out.println("Orders for " + name + " is :");
        orders.findFirst();
        while (!orders.last()) {
            orders.retrieve().display();
            orders.findNext();
        }
                    orders.retrieve().display();

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



