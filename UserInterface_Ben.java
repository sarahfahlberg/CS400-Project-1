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
    private BankATM bank = new BankATM();
    private boolean loggedIn = false;   
    private User user;
    
    
        public void createAccount(Scanner scnr){
            
            String username = "";
            int password = 0;
            boolean passwordMatch = false;

            boolean usernameFree = false;
            while(!usernameFree){
                System.out.println("Let's set up your account!");
                System.out.println("First let's setup a userName. Please type one below");
                
                username = scnr.nextLine();
                System.out.println("Ok " + username + ", let's create a password: ");
                
                while(!passwordMatch){
            
                    password = scnr.nextLine().hashCode();
        
                    System.out.print("\n");
                    System.out.println("Please retype your password: ");
        
                    int password2 = scnr.nextLine().hashCode();
        
                    if(password == password2 ){
                        System.out.println("These passwords don't match! Please type them again.");
                    }
                    else{
                        passwordMatch = true;
                    }
                }

                try{
                    bank.getUser(username, password);
                
                }
                catch(NoSuchElementException e){
                    System.out.println(e);
                    createAccount(scnr);
                    return;
                }
                
            
            
                System.out.print("\n");
            
            }
            
            
            System.out.print("\n");
            System.out.println("Awesome! You're all set up. ");


        }

        


        public void logIn(Scanner scnr){
            
            
            String username = "";
        


            try{
                System.out.println("Username: ");
                username = scnr.nextLine();
                System.out.print("\n");

                System.out.println("Password");
                int password = scnr.nextLine().hashCode();
                user = bank.getUser(username, password);
                loggedIn = true;
            }
            
            catch(NoSuchElementException e){
                System.out.println(e);
                logIn(scnr);
            }
            
            
            
           
                loggedInScreen(scnr, username);


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
                openAccount(scnr, username);
            }
            else if(choice.equals("2")){
                viewAccounts(scnr);
            }
            else if(choice.equals("3")){
                logOut(scnr);
            }
            else{
                System.out.println("Please choose a valid option.");
                loggedInScreen(scnr, username);
            }

            
        }

        public void viewAccounts(Scanner scnr){
            
        }

        public void openAccount(Scanner scnr, String username){
            System.out.println("What would you like the name of the account to be?: ");
            String accountName = scnr.nextLine().trim();

            System.out.println("You'd like to name this account " + accountName + "?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            String choice = scnr.nextLine().trim();
            
            if(choice.equals("1")){
                user.addAccount(accountName);
            }
            else if(choice.equals("2")){
                openAccount(scnr, username);
            }
            else{
                System.out.println("Please enter a valid response.");
                openAccount(scnr, username);
            }



        }


        public void printAccounts(User user){
            System.out.println(user.listAccounts());

        }

        
        public void driver(){
            Scanner scnr = new Scanner(System.in);
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
            else if(choice.equals("3")){
                System.out.println("Have a good day!");
                //Save session
            }

         public static void main(String[] args) {
             driver();
         
         }

            
        }

        

        




    
    




}