import java.util.Scanner;


// the one with the main method
public class Driver {

  public static void main(String[] args) {
    
    BankATM bank = new BankATM();
    bank.initialize();

    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println("press a number");
      System.out.println("1)Login   2)Create a new user");
      int input = sc.nextInt();

      if (input == 1) {
        User you = bank.login();
        bank.interact(you); // interact with the account you just logged in to.

      } else if (input == 2) {
        User you = bank.createUser();
        bank.interact(you); // interact with the account you just made.

      }
    }
  }

}
