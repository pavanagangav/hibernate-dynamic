import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.example.hibernate.hibernate;



public class customercontroller {

	public static void main(String[] args) {
		Scanner src = new Scanner(System.in);
		int ch;
		do {
			displaymenu();
			System.out.println("Enter your choice");
			ch = Integer.parseInt(src.nextLine());
			switch (ch) {
			case 1:
				insertion();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				getall();
				break;
			case 5:
				getbyid();
				break;
			case 6:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid operation");
				break;
			}
		} while (ch > 0);
	}

	private static void getbyid() {
		 Scanner sc=new Scanner(System.in);
		 StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
	        SessionFactory sf = md.getSessionFactoryBuilder().build();
	        Session s = sf.openSession();
	        System.out.println("enter id");
	        int id=sc.nextInt();
	        Transaction t = s.beginTransaction();
	        customer cu=s.get(customer.class,id);
	        if(cu!=null) {
	        	System.out.println("id :"+cu.getId());
	        	System.out.println("name :"+cu.getName());
	        	System.out.println("email :"+cu.getEmail());
	        	System.out.println("password :"+cu.getPassword());
	        	System.out.println("phonenumber :"+cu.getPhonenumber());
	        	
	        }
	        else {
	        	System.out.println("data not found");
	        }
	        t.commit();
	        
		 
	}

	private static void getall() {
		 Scanner sc=new Scanner(System.in);
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = md.getSessionFactoryBuilder().build();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        List<customer>li=s.createQuery("from customer",customer.class).list();
        t.commit();
        for(customer cu:li) {
        	System.out.println("id:"+cu.getId());
        	System.out.println("name:"+cu.getName());
        	System.out.println("email:"+cu.getEmail());
        	System.out.println("password:"+cu.getPassword());
        	System.out.println("phonenumber:"+cu.getPhonenumber());
        	
        }

	}

	private static void update() {
		Scanner sc=new Scanner(System.in);
		 StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
	        SessionFactory sf = md.getSessionFactoryBuilder().build();
	        Session s = sf.openSession();
	        System.out.println("enter id");
	        int id=sc.nextInt();
	        Transaction t = s.beginTransaction();
	        customer cu=s.get(customer.class,id);
	        if(cu!=null) {
	        	System.out.println("enter new name");
	        	String name=sc.next();
	        	System.out.println("enter new email");
	        	String email=sc.next();
	        	System.out.println("enter new password");
	        	String password=sc.next();
	        	System.out.println("enter new phonenumber");
	        	long phonenumber=sc.nextLong();
	        	cu.setName(name);
	        	cu.setEmail(email);
	        	cu.setPassword(password);
	        	cu.setPassword(password);
	        	cu.setPhonenumber(phonenumber);
	        	s.update(cu);
	        	  t.commit();
	        	  System.out.println("data successfully updated");
	        }
	        else {
	        	System.out.println("data not found");
	        }
	      
	        
		 
		

	}

	private static void delete() {
		   Scanner sc=new Scanner(System.in);
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = md.getSessionFactoryBuilder().build();
        Session s = sf.openSession();
        System.out.println("enter  id");
        int id=sc.nextInt();
        Transaction t = s.beginTransaction();
        customer c =s.get(customer.class, id);
        s.delete(c);
        t.commit();
        System.out.println("successfully deleted");
     
        
        
	}

	@SuppressWarnings("deprecation")
	private static void insertion() {
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = md.getSessionFactoryBuilder().build();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        customer c = new customer();
        
        Scanner sc = new Scanner(System.in);
		System.out.println("enter the name");
		c.setName(sc.next());
		System.out.println("enter the email");
		c.setEmail(sc.next());
		System.out.println("enter the password");
		c.setPassword(sc.next());
		System.out.println("enter the phonenumber");
		c.setPhonenumber(sc.nextLong());
		s.save(c);
		t.commit();
		System.out.println("successfully inserted");
	}

	private static void displaymenu() {
		
		System.out.println("****************");
		System.out.println("\t1.insertion");
		System.out.println("\t2.delete");
		System.out.println("\t3.update");
		System.out.println("\t4.getall");
		System.out.println("\t5.getbyid");
		System.out.println("\t6.exit");
		System.out.println("****************");

	}
}