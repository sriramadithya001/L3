package ZUniverse;

import java.util.HashMap;

class User {
    static int id;
    int initial_id;
    String name;
    String email;
    String mobileNo;
    String Z_ID;
    String password;
    int balance;
    int ZCoin;

    HashMap<Integer,RCTransaction> RCTransactions = new HashMap<>();
    HashMap<Integer,ZCTransaction> ZCTransactions = new HashMap<>();

    User(String name,String email,String mobileNo,String password,int initialDeposit) {
        initial_id = ++id;
	this.name = name;
	this.email = email;
	this.mobileNo = mobileNo;
	this.passsword = password;
	this.balance = initialDeposit;
	this.ZCoin = 0;
    }

    void getProfile() {
	System.out.println("name : " + this.name);
	System.out.println("email : " + this.email);
	System.out.println("mobile no: " + this.mobileNo);
	System.out.println("ZID : " + this.Z_ID);
	System.out.println("balance : " + this.balance);
	System.out.println("ZCoins : " + this.ZCoin);
    }


    void getRCTransactions() {
	for(int t_id : this.RCTransactions.keySet()) {
	    RCTransaction transaction = this.RCTransactions.get(t_id);
	    System.out.println("receiver name : " + transaction.receiverName);
	    System.out.println("reeiver ZID : " + transaction.receiver_ZID);
	    System.out.println("Amount transferred : " + transaction.amount);
	}
    }



    void getZCTransactions() {
	for(int t_id : this.ZCTransactions.keySet()) {
	    ZCTransaction transaction = this.ZCTransactions.get(t_id);
	    System.out.println("receiver name : " + transaction.receiverName);
	    System.out.println("reeiver ZID : " + transaction.receiver_ZID);
	    System.out.println("ZCoins transferred : " + transaction.zcoins);
	}
    }

    void changePassword() {
	System.out.print("Enter new Password : ");
	String newPassword = new Scanner(System.in).next();
	this.password = newPassword;
    }

    void depositMoney() {
	System.out.print("Enter money to deposit : ");
	balance += new Scanner(System.in).nextInt();
    }

    void withdrawMoney() {
	System.out.print("Enter money to withdraw : ");
	int money = new Scanner(System.in).nextInt();
	if(money > balance) System.out.println("Insufficient balance");
	else balance -= money;
    }

    void sendMoney() {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter receiver ZID : ");
	String receiver_zid = sc.nextInt();
	if(ZAdmin.Users.get(receiver_zid)) {
	    System.out.print("Enter amount : ");
	    int money = sc.nextInt();
	    if(money > balance) System.out.println("Insufficient balance");
	    else {
		ZAdmin.Users.get(receiver_zid).balance += money;
	        this.balance -= money;
		RCTransaction rct = new RCTransaction(this.name,ZAdmin.Users.get(receiver_zid).name,this.Z_ID,receiver_zid,money);
		this.RCTransactions.put(rct.t_id, rct);
	    }
	}
	else System.out.println("Incorrect ZID");
    }

    void sendZCoins() {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter receiver ZID : ");
	String receiver_zid = sc.nextInt();
	if(ZAdmin.Users.get(receiver_zid)) {
	    System.out.print("Enter zcoins : ");
	    int zcoins = sc.nextInt();
	    if(zcoins > ZCoin) System.out.println("Insufficient ZCoin");
	    else {
		ZAdmin.Users.get(receiver_zid).ZCoin += zcoins;
	        this.ZCoin -= zcoins;
		ZCTransaction zct = new ZCTransaction(this.name,ZAdmin.Users.get(receiver_zid).name,this.Z_ID,receiver_zid,zcoins);
		this.ZCTransactions.put(zct.t_id, zct);
	    }
	}
	else System.out.println("Incorrect ZID");
    }

    void convertRCtoZC() {
	System.out.print("Enter ZCoin count to buy : ");
	int zcnt = new Scanner(System.in).nextInt();
	if(this.balance < zcnt * ZAdmin.zrate) {
	    System.out.println("Insufficient balance");
        }
	else {
	    this.ZCoin += zcnt;
	    this.balance -= zcnt * ZAdmin.zrate;
        }
    }

    void convertZCtoRC() {
	System.out.print("Enter ZCoin count : ");
	int zcnt = new Scanner(System.in).nextInt();

	if(this.ZCoin < zcnt) {
	    System.out.println("Insufficient balance");
        }
	else {
	     int money = zcnt * zrate;
	     money -= (money * 3)/20;
	     this.ZCoin -= zcnt;
	     this.balance += money;
        }
    }
}