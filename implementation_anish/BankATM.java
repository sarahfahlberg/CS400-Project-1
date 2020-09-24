import java.util.Scanner;

public class BankATM {

  private HashTableMap hashtable;
  private User master;
  
  // creates a new master user
  public void initialize() {

    Scanner sc = new Scanner(System.in);
    hashtable = new HashTableMap(10);
        
    System.out.println("Create master username");
    String masterUsername = sc.next();
    System.out.println("Create master password");
    int masterPassword = sc.next().hashCode();
    master = new User( masterUsername, masterPassword);
    hashtable.put(masterUsername, master);


    System.out.println("Welcome to our atm sim");
  }
  
  /**
   * The entire login process. 
   * 
   * @return the user that you logged in as
   */
  public User login() {

    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter your username: ");
      String key = sc.next();
            
      // check first if the user is the master
      if ( master.checkUsername(key) == true) {
        System.out.println("enter your password: ");
        int masterPassword = sc.next().hashCode();
        if ( master.checkPassword(masterPassword) == true) {
          // master access granted
          System.out.println("hello master");
          return master;
        } else {
          continue;
        }
      }
      
      if (hashtable.containsKey(key)) {
        System.out.println("enter your password: ");
        int password = sc.next().hashCode();
        if (hashtable.get(key).checkPassword(password) == true) {
          // access granted
          return hashtable.get(key); // return the user they logged in as
        } else {
          System.out.println("incorrect password/username");
          continue;
        }
      } else {
        System.out.println("no such user exists");
        continue;
      }
    }

  }

  public User createUser() {

    Scanner sc = new Scanner(System.in);
    String key;

    while (true) {
      System.out.println("Create your username: ");
      key = sc.next();

      if (hashtable.containsKey(key)) {
        System.out.println("srry that username is taken :(");
        continue;
      } else {
        break;
      }
    }
    System.out.println("Create your password: ");
    int password = sc.next().hashCode();

    User you = new User(key, password);

    hashtable.put(key, you);
    return you;

  }

  public void interact(User you) {
    
    System.out.println("press 1 to log out");
    System.out.println("\nview accounts\ncreate new accounts\nwhatever");
    Scanner sc = new Scanner(System.in);
    
    if ( you == master) {
      while(true) {
        if ( sc.nextInt() == 1) {
          return;
        }
      // master privileges
      }
      // return;
    }

    while (true) {
      
      if ( sc.nextInt() == 1) {
        return;
      }
      // 2. create new accounts
      // 3. select account
      // 4. view balance,
      // 5. withdraw/deposit money
      // 6. maybe transfer money to other accounts idk
    }
  }

}
