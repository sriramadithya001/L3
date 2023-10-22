import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashMap;
import java.util.Random;

class Contact {
    static int id;
    int contact_id;
    String name;
    String number;
    int contacted;
    

    Contact(String name, String number) {
	this.contact_id = ++id;
	this.name = name;
	this.number = number;
	this.contacted = 0;
    }
}

class CallManager {
    static void callHistory(HashMap<Integer,Contact> contacts) {
	for(int c_id : contacts.keySet()) {
	     if(contacts.get(c_id).contacted != 0) System.out.println(contacts.get(c_id).number + " " + contacts.get(c_id).contacted);
	}
    }

    static void call(Contact contact) {
	System.out.println("Speaking to " + contact.name);
	contact.contacted++;
	Random rand = new Random();
	int callTime = rand.nextInt(10)+1;
	try {Thread.sleep(callTime * 1000);} catch(InterruptedException e) {}
	System.out.println("Call Ended");
    }
}

class ContactManager {
    static HashMap<Integer, Contact> contacts = new HashMap<>();

    static void removeContact(int c_id) {
	System.out.println(contacts.get(c_id).name + " removed successfully");
	contacts.remove(c_id);
    }

    static void updateContact(int c_id) {
	    Scanner sc = new Scanner(System.in);	    
	    System.out.println("1. Edit name");
	    System.out.println("2. Edit number");
	    System.out.println("3. Edit name & number");	    
	    System.out.print("Enter an option : ");
	    int option = sc.nextInt();
	    switch(option) {
		case 1:
		      System.out.print("Enter new name : ");
	              String newName = sc.next();
	              contacts.get(c_id).name = newName;
		      System.out.println("New name successfully added as " + contacts.get(c_id).name);
		      break;
	        case 2:
	              System.out.print("Enter new number : ");
		      String newNumber = sc.next();
		      contacts.get(c_id).number = newNumber;
		      System.out.println("New number successfully added as " + contacts.get(c_id).number);
		      break; 
	   }
    }

    static void contactMenu() {
	System.out.println("\t\tCONTACTS");
	Scanner sc = new Scanner(System.in);
	displayContact();
	boolean back = false;
	while(!back) {
	    System.out.println("\t\tCONTACT OPTIONS");
	    System.out.println("1. Call");
	    System.out.println("2. Delete");
	    System.out.println("3. Update");
	    System.out.println("4. Back");
	    System.out.print("Enter an option : ");
	    int option = sc.nextInt();
	    switch(option) {
		case 1:
		{    
		    System.out.print("Enter contact id to call : ");
		    int c_id = sc.nextInt();
		    if(contacts.get(c_id).equals(null)) System.out.println("Invalid id");
		    else CallManager.call(contacts.get(c_id));
		}
		break;
		case 2:
		{
		    System.out.print("Enter contact id to delete : ");
		    int c_id = sc.nextInt();
		    if(contacts.get(c_id).equals(null)) System.out.println("Invalid id");
		    else removeContact(c_id);
		}
		    break;
		case 3:
		{
		    System.out.print("Enter contact id to update : ");
		    int c_id = sc.nextInt();
		    if(contacts.get(c_id).equals(null)) System.out.println("Invalid id");
		    else updateContact(c_id);
		}
		    break;
		case 4:
		{
		    back = true;
		    ContactManagementSystem.homepage();
		}
		    break;
		default:
		    System.out.println("Enter a valid option");
		    break;
	    }
	}
    }

    static void newContact() {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter name : ");
	String name = sc.next();
	System.out.print("Enter phone number : ");
	String number = sc.next();
	Contact contact = new Contact(name,number);
	contacts.put(contact.contact_id,contact);
	System.out.println(contact.name + " added successfully");
    }

    static void displayContact() {
	for(int c_id : contacts.keySet()) {
	    Contact contact = contacts.get(c_id);
	    System.out.println(c_id + " " + contact.name + " " + contact.number);
	}
    }
}

public class ContactManagementSystem { 
    public static void homepage() {
	Scanner sc = new Scanner(System.in);
	boolean off = false;
	System.out.println("\t\tCONTACT MENU");
	while(!off) {
	    System.out.println("1. New Account");
	    System.out.println("2. Contact Menu");
	    System.out.println("3. Contacts");
	    System.out.println("4. Call History");
	    System.out.println("5. Exit");
	    System.out.print("Enter an option : ");
	    int option = sc.nextInt();
	    switch(option) {
		case 1:
		    ContactManager.newContact();
		    break;
		case 2:
		    ContactManager.contactMenu();
		    break;
		case 3:
		    ContactManager.displayContact();
		    break;
		case 4:
		    CallManager.callHistory(ContactManager.contacts);
		    break;
		case 5:
		    System.out.println("Exiting the Application");
		    off = true;
		    break;
		default:
		    System.out.println("Enter a valid option");
		    break;
	    }
	}
    }

    public static void main(String[] args) {
	System.out.println("\t\tCONTACT MANAGEMENT SYSTEM");
	homepage();
    }
}