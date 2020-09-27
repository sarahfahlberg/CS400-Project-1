// --== CS400 File Header Information ==--
// Name: Benjamin Radosevich
// Email: bradosevich@wisc.edu
// Team: <your team name: CD>
// TA: Wang
// Lecturer: Florian Heimerl
// Notes to Grader: 

import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface_Ben{
    private HashTableMap<String, Integer> hashtable;
    private boolean loggedIn = false;   
        
    
    
        public void createAccount(Scanner scnr){
            
            String userName = "";
            

            boolean usernameFree = false;
            while(!usernameFree){
                System.out.println("Let's set up your account!");
                System.out.println("First let's setup a userName. Please type one below");

                userName = scnr.nextLine();

                if(hashtable.containsKey(userName)){
                usernameFree = true;
            }
            
            System.out.print("\n");
            
            }
            
            boolean passwordMatch = false;
            System.out.println("Ok " + userName + ", let's create a password: ");
            
            while(!passwordMatch){
            
            int password = scnr.nextLine().hashCode();

            System.out.print("\n");
            System.out.println("Please retype your password: ");

            int password2 = scnr.nextLine().hashCode();

            if(password == password2 ){
                System.out.println("These passwords don't match! Please type them again.");
            }
            else{
                passwordMatch = true;
            }

            User newUser = new User(userName, password);

            System.out.print("\n");
            System.out.println("Awesome! You're all set up. ");


        }

        

        }

        public void logIn(Scanner scnr){
            
            
            String userName = "";
            


            try{
                System.out.println("Username: ");
                userName = scnr.nextLine();
                hashtable.containsKey(userName);
            }
            catch(NoSuchElementException e){
                System.out.println(e);
            }
            
            
            System.out.print("\n");

            System.out.println("Password");

            
            int password = scnr.nextLine().hashCode();
            if(hashtable.get(userName) == password){
                loggedIn = true;
                loggedInScreen(scnr, userName);
            }
            else{
                logIn(scnr);
            }



        }


        public void addMoney(Account account){
            Scanner scnr = new Scanner(System.in);
            
            System.out.println("How much money would you like to deposit?");
            double amountToAdd = scnr.nextDouble();
            try{
                account.deposit(amountToAdd);
            }
            catch(IllegalArgumentException e){
                System.out.println(e);
                addMoney(account);
            }


            

            
        }

        public void removeMoney(Account account){
            Scanner scnr = new Scanner(System.in);
            
            System.out.println("How much money would you like to withdraw?");
            double amountToWithdraw = scnr.nextDouble();
            
            try{
                account.withdraw(amountToWithdraw);
            }
            catch(IllegalArgumentException e){
                System.out.println(e);
                removeMoney(account);
            }

        }

        public void logOut(Scanner scnr){
            
            System.out.println("Are you sure you would like to log out? ");
            System.out.println("1. Yes");
            System.out.println("2. No");

            String input = scnr.nextLine().trim();

            if(input.equals("1")){
                loggedIn = false;
                System.out.println("See you next time!");
                driver();

            }
            else if(input.equals("2")){
                
            }
            else{
                System.out.println("Please choose a valid option");
                logOut(scnr);
            }



        }


        public void loggedInScreen(Scanner scnr, String username){
            
            System.out.println("Welcome " + username);
            System.out.println();
            System.out.println("1. Open Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Log Out");

            String choice = scnr.nextLine().trim();

            if(choice.equals("1")){
                openAccount(scnr);
            }
            else if(choice.equals("2")){
                viewAccounts(scnr);
            }
            else if(choice.equals("3")){
                logOut(scnr);
            }
            else{
                System.out.println("Please choose a valid option.")
                loggedInScreen(scnr);
            }

            
        }

        public void viewAccounts(){
            
        }

        public void openAccount(Scanner scnr){
            System.out.println("What would you like the name of the account to be?: ");
            String accountName = scnr.nextLine().trim();

            System.out.println("You'd like to name this account " + accountName + "?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            String choice = scnr.nextLine().trim();
            
            if(choice.equals("1")){
                User.addAccount(accountName);
            }
            else if(choice.equals("2")){
                openAccount(scnr);
            }
            else{
                System.out.println("Please enter a valid response.");
                openAccount(scnr);
            }



        }


        public void printAccounts(User user){
            System.out.println(user.listAccounts());

        }

        
        public void driver(){
            Scanner scnr = new Scanner(System.in)
            System.out.println("Welcome");
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
            else if(choice.equals("3"){
                System.out.println("Have a good day!");
                //Save session
            }


            
        }

        

        




    
    




}