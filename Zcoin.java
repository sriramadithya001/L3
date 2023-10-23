import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Zcoin{

    public static void createAccount(){
        Scanne sc = new Scanner(System.in);
        System.out.println("Enter your full name :");
        String name = sc.nextLine();
        sc.next();
        System.out.println("Enter your valid mail id : ");
        String email = sc.next();
        String password =  getPassword();
        System.out.println("Enter your mobile number : ");
        String mobile = sc.next();
        System.out.println("Enter your H_ID");
        String H_ID = sc.next();
    }

    public static String getPassword(String name, String email, String mobile){
        boolean b = false;
        Scanner sc = new Scanner(System.in);
        while(!b){
           String pw = sc.next();

           if(!pw.contains(name) && !pw.contains(email.split("@")[0]) && !pw.contains(mobile) && pw.length()==8){
            String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9!#%><?&*]+$";
            Matcher matcher = Pattern.compile(pattern).matcher(pw);
            if(matcher.matches()){
                b = true;
                System.out.prinltn("Valid password");
            }else{
                System.out.println("Password strenth is low");
                System.out.println("Password should not contain (full name)/(username part of the email)/(mobile number)\n"+
                "length of the password should not be less than 8\n"+
                "It should be Alphanumeric with Mixed case and limited special character (!#%><?&*) support.)");
            }
           }

        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        System.out.println("Hey...Welcome to Zverse!");
        while(true){
        System.out.println("Press key to select the service");
        System.out.println("Press 1 --> Create Acoount\n" + "Press 2 --> Login as ZE\n"+ "Press 3 --> Login as User");
        int n = sc.nextInt();

        if(n==1){
            accountManager.createAccount();
        }else if(n==2){
            accountManager.loginAsZE();
        }else if(n==3){
            if(accountManager.loginAsUser()){
                System.out.println("Login successful");
            }else{
                System.out.println("invalid user inuts");
            };
        }else if(n==0){
            System.out.println("Thank You!..Exitting..");
            break;
        }
        }
         
    }
}


class AccountManager {
     List<User> users;
    private static final String USER_DATA_FILE = "user_data.txt";

    public AccountManager() {
        // Load existing user data from a file or create an empty list if the file doesn't exist.
        users = loadUserData();
    }

    public void createAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your full name :");
        String name = nextLine();
        sc.next();
        System.out.println("Enter your email id :");
        String email = sc.next();
        System.out.println("Enter your password :");
        String password = getPassword();
        System.out.println("Enter your mobile :");
        String mobile = sc.next();
        

        User newUser = new User(name, email, password, mobile);
        users.add(newUser);
        saveUserData();

    }
    public static String getPassword(String name, String email, String mobile){
        boolean b = false;
        Scanner sc = new Scanner(System.in);
        String vpw ="";
        while(!b){
           String pw = sc.next();

           if(!pw.contains(name) && !pw.contains(email.split("@")[0]) && !pw.contains(mobile) && pw.length()==8){
            String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9!#%><?&*]+$";
            Matcher matcher = Pattern.compile(pattern).matcher(pw);
            if(matcher.matches()){
                b = true;
                vpw = pw;
                System.out.prinltn("Valid password");
                break;
            }else{
                System.out.println("Password strenth is low");
                System.out.println("Password should not contain (full name)/(username part of the email)/(mobile number)\n"+
                "length of the password should not be less than 8\n"+
                "It should be Alphanumeric with Mixed case and limited special character (!#%><?&*) support.)");
            }
           }

        }
        return vpw;

    }

    public boolean login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password) && user.approval) {
                return true; // User found and login successful
            }
        }
        return false; // User not found or password doesn't match
    }

    private void saveUserData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_DATA_FILE, true))) {
            User newUser = users.get(users.size() - 1);
            writer.println(newUser.toDataString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loginAsZE(){
        String ze1_email = "nithish@zcoin.com";
        String ze1_password = "Nithish111@";

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your work emial: ");
        String email = sc.next();
        System.out.println("Enter your password: ");
        String password =  sc.next();
        if(email.equals(ze1_email) && password.equals(ze1_password)){
            System.out.println("Login Successful");
        }
        else{
            System.out.println("Invalid user inputs");
        }

    }

    private List<User> loadUserData() {
        List<User> loadedUsers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    loadedUsers.add(new User(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedUsers;
    }
}
class User {
     List<History> trans_history = new ArrayList<>(); 
     String name;
     String email;
     String password;
     String mobile;
     static int Z_id = 0;
     boolean approval = false;
     int RC = 1000;
     int Zcoin = 0;
     

    public User(String name, String email, String password, String mobile) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        
    }

    public String toDataString() {
        return name + "," + email + "," + password + "," + mobile;
    }

    // Getters for user properties
}

class ZEportal{

     public void intitalize(){
        System.out.println("Hey..Welcome to ZE portal!");
        System.out.println("1. User accout approval\n"+"2. updating Zcoin value");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n==1){
            accountapproval();
        }else if(n==2){
            zcoinUpdate();
        }else if(n==0){
            System.out.println("Exitting from ZE portal");
        }
    }
       public void accountapproval(){
        AccountManager accountManager = new AccountManager();
        Scanner sc = new Scanner(System.in);
        for(User u : accountManager.users){
           System.out.println("Name" + u.name + "  "+"Mail_id"+ u.email  );
           System.out.println("1. Approve\n"+"2.Reject");
           int n = sc.nextInt();
           if(n==1){
            u.approval = true;
            User.Z_id++;
           }else if (n==2){
            u.approval = false;
           }
           
        }
       }

       public void zcoinUpdate(){
        
    }
           
}

class UserPortal{

     public void intialize(){
        System.out.println("Wlecome to user management portal!");
        AccountManager accountManager = new AccountManager();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Z_id");
        int n = sc.nextInt();

        System.out.println("1. Account Details\n"+"2. Transaction history\n"+"3. change password\n" + "4. RC transaction\n"+"5. Zcoin Transaction");
        int m = sc.nextInt();
        while(true){
        switch(m){
            case 1: printAccDetails(n, accountManager.users);
                    break;
            case 2: printTransHistory(n, accountManager.users);
                    break;
            case 3: changePassword(n);
                    break;
            case 4: RCTrans(n);
                    break;
            case 5: ZCTrans(n);
                    break;
            case 0: break;
        }
      }

     }

     public void RCTrans(int Z_id, ArrayList<User> users){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("1. Deposit\n" + "2.Withdraw");
        if(n==1){
           System.out.println("Enter the amount of deposit");
           int d = new Scanner(System.in);
           for(User user: users){
            if(user.id == Z_id){
               user.RC +=d;
               History h = new History(d, user.Zcoin, formattedDateTime, "RCdeposited");
               user.trans_history.add(h);
               System.out.println("Deposit sucessfull! current RC " +user.RC );
               break;
            }
           }
        }else if(n==2){
            
            System.out.println("Enter the amount to withdraw");
            int d = new Scanner(System.in);
            for(User user: users){
             if(user.id == Z_id){
                if(user.RC >d){
                user.RC -=d;

                History h = new History(d, user.Zcoin, formattedDateTime, "RCwithdrawed");
                user.trans_history.add(h);
                System.out.println("Withdraw sucessfull! current RC " +user.RC );
                break;
                }else{
                    System.out.println("Entered more than the balance");
                }
            }
        }
       }
     }

     public void ZCTrans(int ZID, ArrayList<User> users){

        
     }

     public void printTransHistory(int Zid, ArrayList<User> users){
        for(User user : users){
            if(user.Z_id == Zid ){
                for(History h:  user.trans_history){
                    System.out.println(h.RC + "   "+ h.Zcoin+"   "+ h.DateTime+ "   "+h.mode);
                }
            }
        }
     }


}

class History{

    int RC;
    int Zcoin;
    String DateTime;
    String mode;

    public history(int RC, int Zcoin, String DateTime, String mode){
        this.RC = RC;
        this.Zcoin = Zcoin;
        this.DateTime = DateTime;
        this.mode = mode;
    }
    
}
    


