// --== CS400 File Header Information ==--
// Name: Benjamin Radosevich
// Email: bradosevich@wisc.edu
// Team: <your team name: CD>
// TA: Wang
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface_Ben {
    public static BankATM bank = new BankATM();
    private static boolean loggedIn = false;
    private static User user;
    private static String username = "";

    /**
     * Helps the user to create an account and store it in BankATM
     * 
     * @param scnr - used to read user input
     */
    public static void createAccount(Scanner scnr) {


        int password = 0;
        String tempPass = "";
        boolean passwordMatch = false;

        boolean usernameFree = false;
        while (!usernameFree) {
            System.out.println("Let's set up your account!");
            System.out.println("First let's setup a username. Please type one below (no spaces): ");

            boolean usernameValid = false;
            while (!usernameValid) {
                username = scnr.nextLine().trim();
                if (username.contains(" ")) {
                    System.out.println("Please make sure your username has no spaces: ");
                    continue;

                }
                usernameValid = true;
            }
            while (!passwordMatch) {
                System.out.println(
                    "Ok " + username + ", let's create a password (at least 4 characters): ");

                tempPass = scnr.nextLine().trim();

                if (tempPass.length() < 4) {

                    System.out.println("Please enter a password with at least 4" + "characters");
                    continue;

                }

                password = tempPass.hashCode();


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
                System.out.println("That username is taken.");
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


    /**
     * Lets the user log-in to their account, and pulls up their personalized
     * login screen
     * @param scnr
     */
    public static void logIn(Scanner scnr) {



        try {
            System.out.println("Username: ");
            username = scnr.nextLine();
            System.out.print("\n");

            System.out.println("Password: ");
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


    /**
     * Lets the user add money to the account of
     * their choice
     * 
     * @param scnr - reads user input
     * @param account - account to add money to
     */
    public static void addMoney(Scanner scnr, Account account) {


        System.out.println("How much money would you like to deposit?");
        double amountToAdd = scnr.nextDouble();
        try {
            account.deposit(amountToAdd);
            viewAccounts(scnr, user);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            addMoney(scnr, account);
        }


    }
    /**
     * Lets the user remove money from the account
     * of their choice
     * 
     * @param scnr - reads user input
     * @param account - account to withdraw money from
     */
    public static void removeMoney(Scanner scnr, Account account) {
        

        System.out.println("How much money would you like to withdraw?");
        double amountToWithdraw = scnr.nextDouble();

        try {
            account.withdraw(amountToWithdraw);
            viewAccounts(scnr, user);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            removeMoney(scnr, account);
        }
        
        

    }
    /**
     * Logs out the user from their account and takes them back
     * to the home screen.
     * 
     * @param scnr - reads user input
     */
    public static void logOut(Scanner scnr) {

        System.out.println("Are you sure you would like to log out? ");
        System.out.println("1. Yes");
        System.out.println("2. No");

        String input = scnr.nextLine().trim();

        if (input.equals("1")) {
            loggedIn = false;
            System.out.println("See you next time!");
            saveBank();
            driver();

        } else if (input.equals("2")) {
            loggedIn = false;
            loggedInScreen(scnr, user);
        } else {
            System.out.println("Please choose a valid option");
            logOut(scnr);
        }


        
    }

    /**
     * Personal screen for users to interact
     * with their account. Users can only access this
     * with the correct username and password
     * 
     * @param scnr
     * @param user
     */
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
        } else if (choice.equals("3")) {
            changePassword(scnr, user);
        } else if (choice.equals("4")) {
            logOut(scnr);
        } else {
            System.out.println("Please choose a valid option.");
            loggedInScreen(scnr, user);
        }


    }

    /**
     * Lets the user interact with their bank accounts
     * and add or withdraw money from an account, or remove an account
     * entirely
     * @param scnr - reads user input
     * @param user - User which is logged in and interacting with the account
     */
    public static void viewAccounts(Scanner scnr, User user) {
        System.out.println();
        printAccounts(user);
        System.out.println();

        System.out.println("1. Deposit money to an account");
        System.out.println("2. Withdraw money from an account");
        System.out.println("3. Remove an account");
        System.out.println("4. Home");

        String choice = scnr.nextLine().trim();

        if (choice.equals("1")) {
            System.out.println("Which account would you like to deposit money to?");
            String accountName = scnr.nextLine().trim();
            try {
                Account userAccount = user.getAccount(accountName);
                addMoney(scnr, userAccount);
                
            } catch (NoSuchElementException e) {
                System.out.println(e);
                viewAccounts(scnr, user);
            }

        }
        else if (choice.equals("2")) {
            System.out.println("Which account would you like to withdraw money from?");
            String accountName = scnr.nextLine().trim();
            try {
                Account userAccount = user.getAccount(accountName);
                removeMoney(scnr, userAccount);
                
            } catch (NoSuchElementException e) {
                System.out.println(e);
                viewAccounts(scnr, user);
            }

        }
        else if(choice.equals("3")) {
            System.out.println("Which account would you like to remove?");
            String accountName = scnr.nextLine().trim();
            try {
                Account userAccount = user.getAccount(accountName);
                System.out.println("Are you sure you'd like to remove " + userAccount + "?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                String removeChoice = scnr.nextLine().trim();
                if(removeChoice.equals("1")) {
                    user.removeAccount(accountName);
                    viewAccounts(scnr, user);
                }
                else if(removeChoice.equals("2")) {
                    viewAccounts(scnr, user);
                }
                
                else {
                    System.out.println("Please enter a valid option.");
                }
                
            } catch (NoSuchElementException e) {
                System.out.println(e);
                viewAccounts(scnr, user);
            }
        }
        
        else if(choice.equals("4")) {
            loggedInScreen(scnr, user);
        }
        else {
            System.out.println("Please enter a valid response.");
            viewAccounts(scnr, user);
        }
        


    }
    /**
     * Lets the user open a bank account in which they can name,
     * then add or remove money from.
     * 
     * @param scnr - reads user input
     * @param user - user which the account will be added to
     */
    public static void openAccount(Scanner scnr, User user) {
        System.out.println("What would you like the name of the account to be?: ");
        
        String accountName = "";
        boolean accountNameValid = false;
        while (!accountNameValid) {
           accountName = scnr.nextLine().trim();
            if (accountName.contains(" ")) {
                System.out.println("Please make sure your account name has no spaces: ");
                continue;

            }
            accountNameValid = true;
        }
        
        
        
        
        System.out.println("You'd like to name this account " + accountName + "?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        String choice = scnr.nextLine().trim();
        try {


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
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            openAccount(scnr, user);
        }


    }

    /**
     * Prints out the users account names and balances
     * 
     * @param user - user whose accounts will be printed
     */
    public static void printAccounts(User user) {
        System.out.println(user.toString2());

    }

    /**
     * Lets the user change their password
     * 
     * @param scnr - reads user input
     * @param user - user changing their password
     */
    public static void changePassword(Scanner scnr, User user) {

        System.out.println("Please type in your current password: ");

        int currPass = scnr.nextLine().hashCode();

        try {
            bank.getUser(username, currPass);
        } catch (NoSuchElementException e) {
            System.out.println("Password does not match. Please try again.");
            System.out.println();
            changePassword(scnr, user);
        }



        System.out.println();
        boolean newPassMatch = false;
        int newPass = 0;
        while (!newPassMatch) {
            System.out.println("Please type in your new password: ");

            newPass = scnr.nextLine().trim().hashCode();

            System.out.println("Re-type your new password: ");

            int newPass2 = scnr.nextLine().trim().hashCode();
            if (newPass == newPass2) {

                newPassMatch = true;
            }

        }

        user.changePassword(currPass, newPass);

        System.out.println("Password has been changed.");
        System.out.println();
        loggedInScreen(scnr, user);

    }

    /**
     * Home screen and driver of the program.
     * Where the user starts and ends.
     */
    public static void driver() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to Java ATM");
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Create an Account");
        System.out.println("3. Quit");

        String choice = scnr.nextLine().trim();

        if (choice.equals("1")) {
            logIn(scnr);
        } else if (choice.equals("2")) {
            createAccount(scnr);
        } else if (choice.equals("3")) {
            System.out.println("Have a good day!");
            // Save session

            saveBank();

        } else {

            System.out.println("Please enter a valid input");
            driver();

        }



    }
    /**
     * Saves the bank to a file
     */
    public static void saveBank() {

        try{
            DataAlex.writeFile();
        }
        catch(Exception e) {
            System.out.println(e);
        }

    }
    /**
     * Loads a previous bank into the program
     */
    public static void loadBank() {
        try{
            bank = DataAlex.readFile();
        }
        catch(Exception e) {
            e.printStackTrace();
            bank = new BankATM();
        }
    }

    /**
     * Calls the driver
     * @param args
     */
    public static void main(String[] args) {
        loadBank();


        driver();
        
    }



}
