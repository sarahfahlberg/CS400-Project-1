// --== CS400 File Header Information ==--
// Name: Benjamin Radosevich
// Email: bradosevich@wisc.edu
// Team: <your team name: CD>
// TA: Wang
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface_Ben {
    public static BankATM bank = new BankATM();
    private static boolean loggedIn = false;
    private static User user;
    private static String username = "";


    public static void createAccount(Scanner scnr) {


        int password = 0;
        boolean passwordMatch = false;

        boolean usernameFree = false;
        while (!usernameFree) {
            System.out.println("Let's set up your account!");
            System.out.println("First let's setup a username. Please type one below");

            username = scnr.nextLine();
            System.out.println("Ok " + username + ", let's create a password: ");

            while (!passwordMatch) {

                password = scnr.nextLine().trim().hashCode();


                System.out.println("Please retype your password: ");

                int password2 = scnr.nextLine().trim().hashCode();

                if (password != password2) {
                    System.out.println("These passwords don't match! Please type them again.");
                } else {
                    passwordMatch = true;
                }
            }

            try {

                bank.addNewUser(username, password);
                user = bank.getUser(username, password);


            } catch (NoSuchElementException e) {
                System.out.println(e);
                createAccount(scnr);
                return;
            }


            usernameFree = true;
            System.out.print("\n");

        }


        System.out.print("\n");
        System.out.println("Awesome! You're all set up. ");
        loggedInScreen(scnr, user);

    }



    public static void logIn(Scanner scnr) {



        try {
            System.out.println("Username: ");
            username = scnr.nextLine();
            System.out.print("\n");

            System.out.println("Password");
            int password = scnr.nextLine().hashCode();
            user = bank.getUser(username, password);
            loggedIn = true;
        }

        catch (NoSuchElementException e) {
            System.out.println(e);
            driver();
        }



        loggedInScreen(scnr, user);


    }



    public static void addMoney(Account account) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("How much money would you like to deposit?");
        double amountToAdd = scnr.nextDouble();
        try {
            account.deposit(amountToAdd);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            addMoney(account);
        }



    }

    public static void removeMoney(Account account) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("How much money would you like to withdraw?");
        double amountToWithdraw = scnr.nextDouble();

        try {
            account.withdraw(amountToWithdraw);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            removeMoney(account);
        }

    }

    public static void logOut(Scanner scnr) {

        System.out.println("Are you sure you would like to log out? ");
        System.out.println("1. Yes");
        System.out.println("2. No");

        String input = scnr.nextLine().trim();

        if (input.equals("1")) {
            loggedIn = false;
            System.out.println("See you next time!");
            driver();

        } else if (input.equals("2")) {
            loggedIn = false;
            driver();
        } else {
            System.out.println("Please choose a valid option");
            logOut(scnr);
        }



    }


    public static void loggedInScreen(Scanner scnr, User user) {

        System.out.println("Welcome " + username);
        System.out.println();
        System.out.println("1. Open Account");
        System.out.println("2. View Accounts");
        System.out.println("3. Change Password");
        System.out.println("4. Log Out");

        String choice = scnr.nextLine().trim();

        if (choice.equals("1")) {
            openAccount(scnr, user);
        } else if (choice.equals("2")) {
            viewAccounts(scnr, user);
        } else if (choice.contentEquals("3")) {
            changePassword(scnr, user);
        } else if (choice.equals("4")) {
            logOut(scnr);
        } else {
            System.out.println("Please choose a valid option.");
            loggedInScreen(scnr, user);
        }


    }


    public static void viewAccounts(Scanner scnr, User user) {

    }

    public static void openAccount(Scanner scnr, User user) {
        System.out.println("What would you like the name of the account to be?: ");
        String accountName = scnr.nextLine().trim();

        System.out.println("You'd like to name this account " + accountName + "?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        String choice = scnr.nextLine().trim();

        if (choice.equals("1")) {
            user.addAccount(accountName);
            System.out.println(accountName + "account has been opened.");
            loggedInScreen(scnr, user);
        } else if (choice.equals("2")) {
            openAccount(scnr, user);
        } else {
            System.out.println("Please enter a valid response.");
            openAccount(scnr, user);
        }



    }


    public static void printAccounts(User user) {
        System.out.println(user.listAccounts());

    }

    public static void changePassword(Scanner scnr, User user) {
		
		System.out.println("Please type in your current password: ");
		
		int currPass = scnr.nextLine().hashCode();
		
		try{
		    bank.getUser(username, currPass);
		}
		 catch(NoSuchElementException e) {
		     System.out.println("Password does not match. Please try again.");
	         changePassword(scnr, user);
		 }
		    
	        
	        
		    
		
		System.out.println();
        boolean newPassMatch = false;
        int newPass = 0;
		while(!newPassMatch) {
		    System.out.println("Please type in your new password: ");
        
		    newPass = scnr.nextLine().trim().hashCode();
        
		    System.out.println("Re-type your new password: ");
        
		    int newPass2 = scnr.nextLine().trim().hashCode();
		    if(newPass == newPass2) {
		        
		        newPassMatch = true;
		    }
		
		}
		
		user.changePassword(currPass, newPass);
		
		System.out.println("Password has been changed.");
		loggedInScreen(scnr, user);
		
	}


    public static void driver(){
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to Java ATM");
		System.out.println();
		System.out.println("1. Login");
		System.out.println("2. Create an Account");
		System.out.println("3. Quit");

		String choice = scnr.nextLine().trim();

		if(choice.equals("1")){
			logIn(scnr);
		}
		else if(choice.equals("2")){
			createAccount(scnr);
		}
		else if(choice.equals("3")){
			System.out.println("Have a good day!");
			//Save session
			
			saveBank();

		}




	}

    public static BankATM saveBank() {
        return bank;

    }

    public static void main(String[] args) {
        driver();

    }



}
