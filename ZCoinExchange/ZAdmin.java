package ZUniverse;

import java.util.HashMap;

class ZAdmin {
    static int zrate = 2;
    // DB
    static HashMap<Integer, ZE> ZEmployees = new HashMap<>();
    static HashMap<String, User> Users = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    void zeSignUp() {
	System.out.print("Enter your name : ");
	String name = sc.next();
	System.out.print("Enter your email : ");
	String email = sc.next();
	System.out.print("Enter your password : ");
	String password = sc.next();

        // check ZE exists
        ZE ze = new ZE(name,email,password);
       	ZEmployees.put(ze.ze_id, ze);
        System.out.println(name + ", you are an employee in ZUniverse!!");
    }

    void userSignUp() {
	System.out.print("Enter your name : ");
	String name = sc.next();
	System.out.print("Enter your email : ");
	String email = sc.next();
	System.out.print("Enter your mobileNo : ");
	String mobileNo = sc.next();
	System.out.print("Enter your password : ");
	String password = sc.next();
	System.out.print("Enter your Initial deposit : ");
	int initialDeposit = sc.nextInt();

       	// check User exists

	User user = new User(name,email,mobileNo,password,InitialDeposit);
	ZE.UserRequest.put(user.initial_id, user);
	
       	System.out.println(name + ", your request sent successfully!!");
       	System.out.println("You can login after you got verified and you will be receiving Z-ID");
    }

    void zeLogIn() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter email : ");
	String email = sc.next();
	System.out.println("Enter password : ");
	String password = sc.next();
	for(int ze_id : ZAdmin.ZEmplyees.keySet()) {
	    ZE ze = ZAdmin.ZEmployees.get(ze_id);
	    if(ze.email.equals(email) && ze.password.equals(password)) {
		System.out.println("Welcome, " + ze.name);
		zeLoggedIn(ze);
		return;
	    }
	}
	System.out.println("Incorrect email or password");
    }

    void userLogIn() {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter email : ");
	String email = sc.next();
	System.out.println("Enter password : ");
	String password = sc.next();
	for(String zid : ZAdmin.Users.keySet()) {
	    User user = ZAdmin.Users.get(zid);
	    if(user.email.equals(email) && user.password.equals(password)) {
		if(user.Z_ID.equals("-1")) {
		    System.out.println("Your account got rejected,\nCreate another account");
		}
		else {
		    System.out.println("Welcome, " + user.name);
		    userLoggedIn(user);
		}
	        return;
	    }
	}
	System.out.println("Incorrect email or password");	
    }

    void zeLoggedIn(ZE ze) {
	Scanner sc = new Scanner(System.in);
	boolean back = false;
	while(!back) {
	    System.out.println("1. User Requests");
	    System.out.println("2. Fetch RC Transaction");
	    System.out.println("3. Fetch ZC Transaction");
	    System.out.println("4. Back");
	    System.out.print("Enter an option : ");
	    int option = sc.nextInt();
	    if(option == 1) ze.verifyUserRequest();
	    else if(option == 2) ze.fetchRCTransaction();
	    else if(option == 3) ze.fetchZCTransaction();
	    else {
		back = true;
		ZCoinExchange.homepage();
	    }
	}
    }

    void userLoggedIn(User user) {
	Scanner sc = new Scanner(System.in);
	boolean back = false;
	while(!back) {
	    System.out.println("1. Profile");
	    System.out.println("2. RC Transaction History");
	    System.out.println("3. ZC Transaction History");
	    System.out.println("4. Change Password");
	    System.out.println("5. Deposit money");
	    System.out.println("6. Withdraw money");
	    System.out.println("7. Convert RC to ZCoins");
	    System.out.println("8. Convert ZCoins to RC");
	    System.out.println("9. Transact Money");
	    System.out.println("10. Transact ZCoins");
	    System.out.println("11. Back");
	    System.out.print("Enter an option : ");
	    int option = sc.nextInt();
	    switch(option) {
		case 1:
		    user.getProfile();
		    break;
		case 2:
		    user.getRCTransactions();
		    break;
		break;
		case 3:
		    user.getZCTransactions();
		    break;
		case 4:
		    user.changePassword();
		    break;
		case 5:
		    user.depositMoney();
		    break;
		case 6:
		    user.withdrawMoney();
		    break;
		case 7:
		    user.convertRCtoZC();
		    break;
		case 8:
		    user.convertZCtoRC();
		    break;
		case 9:
		    user.sendMoney();
		    break;
		case 10:
		    user.sendZCoins();
		    break;
		case 11:
		    back = true;
		    break;
		default:
		    System.out.println("Enter a valid option");
	    }
	}	
    }
}