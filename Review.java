
package datastructure;

import java.io.File;
import java.util.Scanner;

public class Review {

    private int reviewID;
    private int productID;
    private int rating; 
     private int customerID; 
    private String comment;
   

    public Review(int RID,int PID,  int CID,int r,String c) {
        reviewID= RID;
        productID=PID;
        customerID =CID;
        rating= r;
        comment =c;
    }
    
public void updateReview(Review p) {
        reviewID = p.reviewID;
        productID = p. productID;
        customerID = p.customerID;
        rating = p.rating;
        comment = p.comment;
    }

    public int getReviewID() {
        return reviewID; }
    
     public int getProductID() { 
         return productID; }
     
     public int getCustomerID() {
         return customerID; }
     
    public int getRating() { 
        return rating; }
    
    public String getComment() { 
        return comment; }
    

    public void setRating(int r) { 
        this.rating = r; }
    public void setComment(String c) {
        this.comment = c; }

    public void display() {
        System.out.println("Review ID: " + reviewID);
         System.out.println("product ID: " + productID);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Rating: " + rating + "/5");
        System.out.println("Comment: " + comment);
        System.out.println("****************************");
        System.out.println("****************************");

    }
   
}

