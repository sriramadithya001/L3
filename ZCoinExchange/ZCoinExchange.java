package ZUniverse;

import java.util.Scanner;

public class ZCoinExchange {

    public static void homepage() {
	ZAdmin admin = new ZAdmin();
	Scanner sc = new Scanner(System.in);
	System.out.println("MENU");
	boolean back = false;
	while(!back) {
	    System.out.println("1. SignUp");
	    System.out.println("2. LogIn");
	    System.out.println("3. Back");
	    System.out.println("Enter an option : ");
	    int option = sc.nextInt();
	    switch(option) {
		case 1:
		{
		    System.out.println(1. ZEmployee);
		    System.out.println(2. User);
		    System.out.println("Enter an option : ");
		    int option = sc.nextInt();
		    if(option == 1) admin.zeSignUp();
		    else if(option == 2) admin.userSignUp();
		}
		break;
		case 2:
		{
		    System.out.println(1. ZEmployee);
		    System.out.println(2. User);
		    System.out.println("Enter an option : ");
		    int option = sc.nextInt();
		    if(option == 1) admin.zeLogIn();
		    else if(option == 2) admin.userLogIn();
		}
		break;
		case 3:
		{
		    System.out.println("Exiting the Application");
		    back = true;
		}
		break;
		default:
		    System.out.println("Enter a valid option");
	    }
	}
    }

    public static void main(String[] args) {
	System.out.println("\t\tZCOIN EXCHANGE");
	homepage();
    }
}