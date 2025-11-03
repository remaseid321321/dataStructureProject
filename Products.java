
package datastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Products {
    private LinkedList<Product> products; 
    
    public Products(LinkedList<Product> listP) {
        products = listP;
    }
    
    public Products() {
        products = new LinkedList<>();
    }
    
    public LinkedList<Product> getProducts(){
    return products;
    }
    
 public Product findProduct(int id){
     if (products.isEmpty()){
         return null;
     }
     else{
         products.findFirst();
           while(!products.last()){
              if (products.retrieve().getProductID()==id)
              {
                  return products.retrieve();
              }
                   products.findNext();
         }
           if (products.retrieve().getProductID()==id)
              {
                  return products.retrieve();
              }  
       }
     return null;
 }
    
    public void addProduct(Product p) {
        if (findProduct(p.getProductID())==null) { 
            products.addLast(p);
            
            System.out.println("added successfully" + p.getName());
        } 
        else {
            System.out.println("Product ID " + p.getProductID() + " already exists!");
        }
    }

    public void removeProduct(int id) {
             Product target=findProduct(id);
        
             if (target!=null) {
                products.remove();
                System.out.println("Product removed successfully: " + id);
            }
           else
        System.out.println("Product ID not found");
    }

    
    public void updateProduct(int id, Product p) {
       Product existing =findProduct(id);
       if(existing==null)
            System.out.println("product not found");
       else
           existing.updateProduct(p);
                   System.out.println("product update successfully");

    }

  

    public void displayOutOfStock() {      
        System.out.println("Out of stock products:"); 
        
         if (products.isEmpty()){
             System.out.println("no products found");  
             return;
         }
        
             boolean found = false;
             products.findFirst();
           while(!products.last()){
              if (products.retrieve().getStock()==0) {
                   products.retrieve().display();
                   found=true;
              }                   
                   products.findNext();
         }
               if (products.retrieve().getStock()==0) {
                   products.retrieve().display();
                   found=true;
}

            if (!found) System.out.println("✅ All products in stock!");
       
     
    }

    public void displayAllProducts() {
       if (products.isEmpty()){
           System.out.println("no products found");
         return ;
     }
             System.out.println("--all products--");
         products.findFirst();
           while(!products.last()){
               Product p=products.retrieve();
                   p.display(); 
                   p.displayReviews();
                    System.out.println("***************************");
                   products.findNext();
       }
    Product p=products.retrieve();
                   p.display(); 
                   p.displayReviews();
                    System.out.println("***************************");
                    
    }
     
    public  void assign(Review r){
       Product p= findProduct(r.getProductID());
       if(p==null)
              System.out.println(" Review "+r.getReviewID()+"ignored , product not found");
       else
           p.addReview(r);
                    System.out.println(" Review add to product: "+r.getReviewID());

     }
     public void test1() {
    Product p1 = new Product(101, "Laptop Pro 15", 1499.99, 20);
    Review r1 = new Review(201, 101, 300, 5, "Excellent laptop");
    Review r2 = new Review(202, 101, 301, 4, "good");
    p1.addReview(r1);
    p1.addReview(r2);
    ////////////////////////////
    Product p2 = new Product(102, "iPhone 15 Pro", 3999.99, 15);
    Review r3 = new Review(203, 102, 302, 5, "best iPhone ever");
    Review r4 = new Review(204, 102, 303, 3, " expensive");
    p2.addReview(r3);
    p2.addReview(r4);
    Product p3 = new Product(103, "Samsung Galaxy a15", 3499.99, 0);
    Review r5 = new Review(205, 103, 304, 2, "bad battery");
    Review r6 = new Review(205, 103, 305, 4, "Excellent battery");
    p3.addReview(r5);
    p3.addReview(r6);
    addProduct(p1);
    addProduct(p2);
    addProduct(p3);   
 
    displayAllProducts();
}
  public void test2() {
    Product p1 = new Product(101, "Laptop Pro 15", 1499.99, 20);
    Product p2 = new Product(102, "iPhone 15 Pro", 3999.99, 15);
    Product p3 = new Product(103, "Samsung Galaxy a15", 3499.99, 0); // out of stock
    addProduct(p1);
    addProduct(p2);
    addProduct(p3);
    Review r1 = new Review(201, 101, 300, 5, "Excellent laptop");
    Review r2 = new Review(202, 101, 301, 4, "good");
    Review r3 = new Review(203, 102, 302, 5, "best iPhone ever");
    Review r4 = new Review(204, 102, 303, 3, " expensive");
    Review r5 = new Review(205, 103, 304, 2, "bad battery");
    Review r6 = new Review(205, 103, 305, 4, "Excellent battery");

    assign(r1);
    assign(r2);
    assign(r3);
    assign(r4);
    assign(r5);
    assign(r6);
    displayAllProducts();
} 
  
public static Product fromCSV_P(String line) {
    String part[]=line.split(",");
    Product p=new Product(Integer.parseInt(part[0]), part[1],Double.parseDouble(part[2]),Integer.parseInt(part[3]));
    return p;
    }

 public  void loadProducts(String file) {
        try {
            File f = new File(file);
            Scanner read = new Scanner(f);
            System.out.println("loading..: " + file);
            System.out.println("*********************");
            read.nextLine().trim();
            while (read.hasNextLine()) {
                String line = read.nextLine().trim();
                if (!line.isEmpty()) {                  
                  Product p=fromCSV_P(line);
                  products.addLast(p);
                 
                }
            }

            read.close();
            System.out.println("*************************");
            System.out.println("file loaded successfully\n");
        } 
        catch (FileNotFoundException e) {
            System.out.println("file not found:" +file);
        }
        catch (Exception e) {
            System.out.println("error while reading file:" + e.getMessage());
        }
    }
  public static void test3()
    {
  Products p=new Products();
  p.loadProducts("C:\\Users\\jjjjo\\OneDrive\\سطح المكتب\\DataStructure Phase 1\\DataStructure-main\\DataStructure-main\\مرفقات");
  p.displayAllProducts();
    } 
 public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Products all=new Products();
     System.out.println("*********Test1***********");
     all.test1();
     System.out.println("*********Test2***********");
     all.test2();
     System.out.println("*********Test3***********");
    all.test3();
    }  
    
}