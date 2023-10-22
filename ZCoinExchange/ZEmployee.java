package ZUniverse;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;

class ZE {
    static int id;
    int ze_id;
    String name;
    String email;
    String password;

    static HashMap<Integer, User> UserRequest = new HashMap<>();

    ZE(String name,String email,String password) {
	this.ze_id = ++id;
	this.name = name;
	this.email = email;
	this.password = password;
    }

    void displayUserRequest() {
	for(Integer user_id : UserRequest.keySet()) {
	    User user = UserRequest.get(user_id);
	    System.out.println(user_id + " " + user.name + " " + user.email + " " + user.mobileNo + " " + user.InitialDeposit);
	}
    }
    
    void displayUsers(HashMap<String, User> Users) {
	for(String user_zid : Users.keySet()) {
	    User user = Users.get(user_zid);
	    System.out.println(user_id + " " + user.name + " " + user.balance + " " + user.ZCoin);
	}
    }

    void verifyUserRequest() {
	displayUserRequest();
	System.out.println("It is advised to verify user based on the Initial deposit");
	System.out.println("1. Approve");
	System.out.println("2. Reject");
	System.out.println("3. Back");
	System.out.print("Enter an option : ");
	int option = new Scanner(System.in).nextInt();
	if(option == 1) approveUser();
	else if(option == 2) rejectUser();
	//else if(option == 3) {zadmin}
    }

    void approveUser() {
	System.out.print("Enter user id : ");
	int user_id = new Scanner(System.in).nextInt();
	User user = UserRequest.get(user_id);
	String ZID = (new Random.nextInt(10) + 1)*12678 + " ";
	user.Z_ID = ZID;
	user.ZCoin = 20;
	ZAdmin.Users.put(Z_ID, user);
	UserRequest.remove(user_id);
	System.out.println("User approved successfully");
    }

    void rejectUser() {
	System.out.print("Enter user id : ");
	int user_id = new Scanner(System.in).nextInt();
	User user = UserRequest.get(user_id);
	user.Z_ID = "-1";
	UserRequest.remove(user_id);
	System.out.println("User rejected successfully");
    }

    void fetchRCTransaction() {
	displayUsers(ZAdmin.Users);
	System.out.print("Enter user id to see RCTransactions : ");
	String user_zid = new Scanner(System.in).next();
	User user = ZAdmin.Users.get(user_zid);
	for(int t_id : user.RCTransactions.keySet()) {
	    RCTransaction transaction = user.RCTransactions.get(t_id);
	    System.out.println("sender name : " + transaction.senderName);
	    System.out.println("receiver name : " + transaction.receiverName);
	    System.out.println("sender ZID : " + transaction.sender_ZID);
	    System.out.println("reeiver ZID : " + transaction.receiver_ZID);
	    System.out.println("Amount transferred : " + transaction.amount);
	}
    }

    void fetchZCTransaction() {
	displayUsers(ZAdmin.Users);
	System.out.print("Enter user id to see ZCTransactions : ");
	String user_zid = new Scanner(System.in).next();
	User user = ZAdmin.Users.get(user_zid);
	for(int t_id : user.ZCTransactions.keySet()) {
	    ZCTransaction transaction = user.ZCTransactions.get(t_id);
	    System.out.println("sender name : " + transaction.senderName);
	    System.out.println("receiver name : " + transaction.receiverName);
	    System.out.println("sender ZID : " + transaction.sender_ZID);
	    System.out.println("reeiver ZID : " + transaction.receiver_ZID);
	    System.out.println("ZCoins transferred : " + transaction.zcoins);
	}
    }
}