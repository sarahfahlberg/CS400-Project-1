// --== CS400 File Header Information ==--
// Name: Matthew Wilson
// Email: mhwilson3@wisc.edu
// Team: CD
// TA: Yeping Wang
// Lecturer: Florian Heimerl
// Notes to Grader: 

import java.util.NoSuchElementException;
import java.util.Scanner;

public class BankInterface_Matt {
  
  static Scanner scnr = new Scanner(System.in);
  
  /*
   * @param account
   * @param user
   * @param atm
   * User interface for an account object.
   */
  public static void accountUI(Account account, User user, BankATM atm) {
    
    double amount;
    System.out.println(account.toString());
    System.out.println("Please select an option: \n"
        + "(1) Deposit\n"
        + "(2) Withdraw\n"
        + "(3) Return to user menu");
    if (scnr.hasNextInt()) {
      switch (scnr.nextInt()) {
        case 1:
          System.out.println("Please enter the amount to deposit: ");
          amount = scnr.nextDouble();
          try {
            account.deposit(amount);
            System.out.println("Balance is now: " + account.getAccountBalance());
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          accountUI(account, user, atm);

        case 2:
          System.out.println("Please enter the amount to withdraw: ");
          amount = scnr.nextDouble();
          try {
            account.withdraw(amount);
            System.out.println("Balance is now: " + account.getAccountBalance());
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          accountUI(account, user, atm);

        case 3:
          userUI(user, atm);

        default:
          System.out.println("Invalid input");
          accountUI(account, user, atm);
      }
    } else {
      scnr.next();
      System.out.println("Invalid input");
      accountUI(account, user, atm);
    }
  }
  
  /*
   * @param user
   * @param atm
   * User interface for accessing a user object.
   */
  public static void userUI(User user, BankATM atm) {
    String name = null;
    
    System.out.println("Please select an option: \n"
        + "(1) Log in to an account\n"
        + "(2) Create an account\n"
        + "(3) Delete an account\n"
        + "(4) Change password\n"
        + "(5) Return to main menu");
    if (scnr.hasNextInt()) {
      switch (scnr.nextInt()) {
        
        // Attempts to log into an account
        case 1:
          do {
            System.out.println("Please enter your account name: ");
            name = stringInput();
          } while (name == null);
          try {
            accountUI(user.getAccount(name), user, atm);
          } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            userUI(user, atm);
          }
          
        // Attempts to create a new account
        case 2:
          do {
            System.out.println("Please enter an account name: ");
            name = stringInput();
          } while (name == null);

          try {
            user.addAccount(name);
            System.out.println("Account created");
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
          userUI(user, atm);
          
        // Attempts to delete an account.
        case 3:
          do {
          System.out.println("Please enter the account you wish to delete: ");
          name = stringInput();
          } while (name == null);
          String confirm = null;
          do {
            System.out.println("You're about to delete: " + name + "\n Continue? [Y/N]");
            confirm = scnr.next().toUpperCase();
            scnr.nextLine();
          } while (!confirm.equals("Y") && !confirm.equals("N"));

            if (confirm.equals("Y")) {
              user.removeAccount(name);
              System.out.println(name + " deleted");
              userUI(user, atm);
            } else if (confirm.equals("N")) {
              userUI(user, atm);
            }

        // Attempts to change the users password.
        case 4:
          System.out.println("Please enter your current password: ");
          int hashedCurrentPass = scnr.next().hashCode();
          System.out.println("Please enter your new password: ");
          int hashedNewPass = scnr.next().hashCode();

          try {
            user.changePassword(hashedCurrentPass, hashedNewPass);
            System.out.println("Password changed");
            userUI(user, atm);
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            userUI(user, atm);
          }
        
        // Returns to the mainMenu() of the interface
        case 5:
          mainMenu(atm);

        default:
          System.out.println("Invalid input");
          userUI(user, atm);
      }
    } else {
      scnr.next();
      System.out.println("Invalid input");
      userUI(user, atm);
    }
  }

  /*
   * @param atm
   * Directs to the UserUI if a user successfully logs in
   */
  public static void login(BankATM atm) {
    String username = null;
    int password = 0;

    do {
      System.out.println("Please enter a username: ");
      username = stringInput();
    } while (username == null);

    do {
      System.out.println("Please enter a password: ");
      password = stringInput().hashCode();
    } while (password == 0);

    try {
      userUI(atm.getUser(username, password), atm);
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
      mainMenu(atm);
    }
  }

  /*
   * @param atm
   * Creates a user to be stored in the atm.
   */
  public static void createUser(BankATM atm) {
    String username = null;
    int password = 0;

    do {
      System.out.println("Please enter a username: ");
      username = stringInput();
    } while (username == null);

    do {
      System.out.println("Please enter a password: ");
      password = stringInput().hashCode();
    } while (password == 0);

    try {
      atm.addNewUser(username, password);
      System.out.println("User " + username + " created");
      mainMenu(atm);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      mainMenu(atm);
    }
  }

  /*
   * @param atm
   * The main menu of the user interface. Directs to either login() method or createUser() method.
   */
  public static void mainMenu(BankATM atm) {

    System.out.println("Please select an option: \n" 
    + "(1) Log in \n" 
    + "(2) Create account");
    
    if (scnr.hasNextInt()) {

      switch (scnr.nextInt()) {
        case 1:
          login(atm);

        case 2:
          createUser(atm);

        default:
          System.out.println("Invalid input.");
          mainMenu(atm);
      }
    }
    else {
      scnr.nextLine();
      System.out.println("Invalid input");
      mainMenu(atm);
    }
  }
  
  /*
   * Takes String input from user
   */
  public static String stringInput() {
    String input = null;
  
    if(scnr.hasNext()) {
    input = scnr.next();
    }
    return input;
  }
  
  public static void main(String args[]) {
    BankATM atm = new BankATM();
    mainMenu(atm);
  }

}
