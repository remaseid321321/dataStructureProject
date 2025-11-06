package datastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Customers {
    private LinkedList<Customer> customers;
   
   
    Customers(LinkedList<Customer> listC) {
      customers=listC;
    }
    
    public Customers() {
        customers = new LinkedList<>();       
    }
    
public LinkedList<Customer> getCustomers(){
return customers;
}

    public Customer findCustomer(int id) {
        if (customers.isEmpty()){
         return null;
     }
     else{
         customers.findFirst();
           while(!customers.last()){
               Customer c=customers.retrieve();
              if (customers.retrieve().getCustomerID()==id)
                  return c;
                   customers.findNext();
         }
                          Customer c=customers.retrieve();
              if (customers.retrieve().getCustomerID()==id)
                  return c;
       }
     return null;

    }

  
    public void addCustomer(Customer c) {
        if (findCustomer(c.getCustomerID())==null) { 
            customers.insert(c);
            
            System.out.println("added successfully" + c.getName());
        } 
        else {
            System.out.println("Customer ID " + c.getCustomerID() + " already exists!");
        }

    }

    public void displayAll() {
       if (customers.isEmpty()){
           System.out.println("no Customer found");
         return ;
     }
             System.out.println("--all Customer--");
         customers.findFirst();
           while(!customers.last()){
               Customer c =customers.retrieve();
                   c.display(); 
                    System.out.println("***************************");
                    System.out.println("***************************");

                   customers.findNext();
       }
    Customer c=customers.retrieve();
                   c.display(); 
                    System.out.println("***************************");
                    System.out.println("***************************");
    }
    
public static Customer fromCSV_C(String Line)
    {
    String part[]=Line.split(",");
     Customer c=new Customer(Integer.parseInt(part[0].trim()),part[1].trim(),part[2].trim());
    return c;
    }
  
    public void loadCustomers(String file) {
        try {
            File f = new File(file);
            Scanner read = new Scanner(f);
            System.out.println("loading..: " + file);
            System.out.println("**********************");
            if (read.hasNextLine()) read.nextLine(); 
            while (read.hasNextLine()) {
                String line = read.nextLine().trim();
                if (line.isEmpty()) continue;  
                Customer c = fromCSV_C(line);
                customers.addLast(c);
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

public static void test1() {
        Customers all = new Customers();
        Customer c1 = new Customer(201, "Omar Hassan", "omar.hassan@gmail.com");
        Customer c2 = new Customer(202, "Nour Adel", "nour.adel@yahoo.com");

        all.addCustomer(c1);
        all.addCustomer(c2);

        System.out.println("\nAfter adding manually:");
        all.displayAll();
    }
    public static void test2() {
        Customers all = new Customers();
        all.loadCustomers("C:\\\\Users\\\\jjjjo\\\\OneDrive\\\\سطح المكتب\\\\DataStructure Phase 1\\\\DataStructure-main\\\\DataStructure-main\\\\مرفقات");
        all.displayAll();
    }

    

    public static void main(String[] args) {       
        test1();      
        test2();
    }
}


