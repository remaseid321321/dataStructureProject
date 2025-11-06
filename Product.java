
package datastructure;

import java.io.File;
import java.util.Scanner;


public class Product {
    private String name;
    private int productID;
    private double price;
    private int stock;
    private LinkedList<Review> reviews;
    
    public Product(int PID, String n , double p, int s) {
        productID = PID;
        name = n;
        price = p;
        stock = s;
        reviews = new LinkedList<>();
    }
    
    
    public String getName() {
        return name; }
    
    public int getProductID() {
        return productID; }
    
    public double getPrice() {
        return price; }
    
    public int getStock() {
        return stock; }

    public void setPrice(double p) { 
        price = p; }
    public void setStock(int s) { 
        stock = s; }

    public void addReview(Review r) {        
        reviews.insert(r);
    }

    public void updateProduct(Product p) {
        productID= p.productID;
        name = p.name;
        price = p.price;
        stock = p.stock;
        reviews = p.reviews;
    }
    
    public double getAverageRate() {
        if (reviews.isEmpty()) 
            return 0;
        
        reviews.findFirst();
        double sum=0;
        int count=0;
        
        while (!reviews.last()) {
            sum= sum + reviews.retrieve().getRating();
            count++;
            reviews.findNext();
        }
        sum= sum + reviews.retrieve().getRating();
            count++;
        return sum / count;
    }

    public void displayReviews() {
        System.out.println( name + "  Reviews is :");
        if (reviews.isEmpty()) {
            System.out.println(" there is No reviews ");
        } 
        else {
            reviews.findFirst();
            while (!reviews.last()) {
                reviews.retrieve().display();
                reviews.findNext();
            }
                            reviews.retrieve().display();

        }
    }

    public void display() {
        System.out.println("Product ID=" + productID);
        System.out.println("Name=" + name);
        System.out.println("Price=" + price);
        System.out.println("Stock=" + stock);
        System.out.println("Average Rate=" + getAverageRate());
        System.out.println("********************************");
        System.out.println("********************************");

    } 
    
    
   
    public static void test1()
    {
    Review r1=new Review(220, 200, 300, 4, "good product");
    Review r2=new Review(221, 201, 301, 1, "bad product");
    Review r3=new Review(220, 200, 300, 4, "good product");
    Product p1=new Product(101,"Laptop Pro 15", 1499.99, 20);
    p1.addReview(r1);
    p1.addReview(r2);
    p1.addReview(r3);
    p1.display();
    p1.displayReviews();
    }
    
  
    public static void main(String[] args) {
       test1();
    }
    
}

