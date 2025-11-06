package datastructure;


import java.io.File;
import java.util.Scanner;

public class Reviews {
	
	
    private static LinkedList<Review> reviews;    
    private Products all_products;
    private Customers all_Customers;
     public Reviews(LinkedList<Review> reviews,LinkedList<Product> input_products
                                          ,LinkedList<Customer> input_customers) {
        this.reviews = reviews;
        all_products=new Products(input_products);
        all_Customers =new Customers(input_customers) ;
    }
     
     
    public Reviews() {
        reviews = new LinkedList<>();
        all_products=new Products();
        all_Customers=new Customers();
    }
    
    public LinkedList<Review>get_all_Reviews()
    {
    return reviews;
    }
    
    
    public Products get_all_Products()
    {
    return all_products;
    }
    
    
 public Review Search_Review_by_id(int id){
     if (reviews.isEmpty()){
         return null;
     }
     else{
         reviews.findFirst();
           while(true){
              if (reviews.retrieve().getReviewID()==id)
              {
                  return reviews.retrieve();
              }
               if (reviews.last())
                   break;
               else
                   reviews.findNext();
         }
       }
     return null;
 }
 
 
    public  void assign_to_product(Review r){
       Product p= all_products.findProduct(r.getProductID());

        if(p!=null)
           p.addReview(r);
     } 
    
    
      public  void assign_to_customer(Review r){
       Customer p= all_Customers.findCustomer(r.getCustomerID());

       if(p!=null)
           p.addReview(r);
     } 
      
      
    public void addReview(Review r) {
        if (Search_Review_by_id(r.getReviewID())==null) { 
            reviews.addLast(r);
            assign_to_product(r);
            assign_to_customer(r);
        } else {
            System.out.println("Review with ID " + r.getReviewID() + " already exists!");
        }
    }    
    
    
 public void updateReview(int id, Review p) {
       Review old=Search_Review_by_id(id);
       if(old==null)
            System.out.println("not exist to make update");
       else
           old.updateReview(p);
    }
  

    public void displayAllReviews() {
        System.out.println("=== All Reviews ===");
       if (reviews.isEmpty()){
           System.out.println("no reviews exist");
         return ;
     }
     else{
         reviews.findFirst();
           while(true){
               Review p=reviews.retrieve();
                   p.display();                  
                    System.out.println("=============================");
                     System.out.println("============================");
               if (reviews.last())
                   break;
               else
                   reviews.findNext();
         }
       }
    
    }
     
    
     public void test1() {
    Review r1 = new Review(401, 101,201,5,"Excellent Excellent laptop,super fast!");
    Review r2 = new Review(402, 102, 202, 4, "Nice");
    Review r3 = new Review(403, 103, 203, 5, "great keyboard for typing");      
    Review r4 = new Review(203, 102, 302, 5, "best iPhone ever");
    Review r5 = new Review(204, 102, 303, 3, " expensive");
    addReview(r1);
    addReview(r2);
    addReview(r3);
    addReview(r4);
    addReview(r5);
    
    //////////////////////////// 
   
    displayAllReviews();
}
  

   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Reviews all=new Reviews();
    System.out.println("============Test1===============");
     //all.test1();
     System.out.println("===========Test3=================");
    all.test3();
    }
    
    
  public static Review convert_String_to_Review(String Line) {
    String[] a =Line.split(",", 5);
    Review r = new Review(
        Integer.parseInt(a[0].trim()), 
        Integer.parseInt(a[1].trim()),  
        Integer.parseInt(a[2].trim()), 
        Integer.parseInt(a[3].trim()),  
            a[4]
        
    );
    return r;
}
      public  void load_revews(String fileName) {
        try {
            File f = new File(fileName);
            Scanner read = new Scanner(f);

            System.out.println(" Reading file: " + fileName);
            System.out.println("========================================");
           read.nextLine().trim();
            while (read.hasNextLine()) {
                String line = read.nextLine().trim();
                if (!line.isEmpty()) {                   
                 Review  r=convert_String_to_Review(line);
                 addReview(r);
                               
                }
            }
            read.close();
            System.out.println("=================================================");
            System.out.println("File loaded successfully!\n");
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
     public static void test3()
    {
  Reviews p=new Reviews(); 
  p.all_products.loadProducts("C:\\Users\\win\\Documents\\NetBeansProjects\\212project2025\\prodcuts.csv");
  p.all_Customers.loadCustomers("C:\\Users\\win\\Documents\\NetBeansProjects\\212project2025\\Customers.csv");
  p.load_revews("C:\\Users\\win\\Documents\\NetBeansProjects\\212project2025\\reviews.csv");
  
  p.all_Customers.displayAll();
    } 
   

}
