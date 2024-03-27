package Banking;

import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        ATMop obj = new ATMop();
    }
}

class User {
    String userID;
    int pincode;
    float balance;
}
class ATMop {

    Scanner sc = new Scanner(System.in);
    HashMap<String, User> userMap = new HashMap<>();
    int maxAttempts = 2; // Maximum number of attempts for entering PIN

    ATMop() {
        System.out.println("***********************************************");
        System.out.println("Welcome to our ATM");
        op();
    }

    public void op() {
        System.out.println("***********************************************");
        System.out.println("Enter your user ID:");
        String userID = sc.next();
        System.out.println("Enter your pincode:");

        int pincode = sc.nextInt();
        String userKey = userID + pincode;

        if (!userMap.containsKey(userID)) {
            System.out.println("***********************************************");
            System.out.println("User does not exist. Would you like to create a new account? (yes/no)");
            String createAccountChoice = sc.next().toLowerCase();

            if (createAccountChoice.equals("yes")) {
                System.out.println("Set your pincode:");
                int pin = sc.nextInt();
                User user = new User();
                user.userID = userID;
                user.pincode = pin;
                userMap.put(userID, user);
                menu(user);
            } else {
                System.out.println("Exiting...");
    
            }
        } else {
            int attempts = 0;
            while (attempts < maxAttempts) {
                User user = userMap.get(userID);
                System.out.println("Incorrect PIN. Attempts left: " + (maxAttempts - attempts));
                int retryPin = sc.nextInt();
                if (retryPin == user.pincode) {
                    menu(user);
                    return;
                } else {
                    attempts++;
                    System.out.println("Incorrect PIN. Attempts left: " + (maxAttempts - attempts));
                }
            }
            System.out.println("Too many incorrect attempts. Exiting...");
        }
    }


    
    public void menu(User obj) {
        System.out.println("Please enter a number from below");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. Check another account");
        System.out.println("Exit");

        int x = sc.nextInt();
        //switch cases for user input selection
        switch (x) {
            case 1:
                check_balance(obj);
                break;
            case 2:
                deposit(obj);
                break;
            case 3:
                withdraw(obj);
                break;
            case 4:
                op();
                break;
            case 5:
                System.out.println("Exiting...");
                op();
                break;
            default:
                System.out.println("Enter a valid number");
                menu(obj);
        }
    }
    
    //to check the available bank balance 
    public void check_balance(User obj) {
        System.out.println("Your balance: " + obj.balance);
        menu(obj);
    }
    //to deposit money 
    public void deposit(User obj) {
        System.out.println("Enter your amount");
        float a = sc.nextFloat();

        obj.balance += a;
        System.out.println("Amount deposited successfully!");
        menu(obj);
    }
    //to withdraw money
    public void withdraw(User obj) {
        System.out.println("Enter your amount");
        float a = sc.nextFloat();
        if (obj.balance >= a) {
            obj.balance = obj.balance - a;
            System.out.println("Amount withdrawn successfully!");
        } else {
            System.out.println("Insufficient balance!");
        }
        menu(obj);
    }
}
