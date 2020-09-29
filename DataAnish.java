import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Contains methods to save the contents of the hashtable to a .txt file and load the data from it
 * into the hashtable when the program runs again.
 * 
 * @author anish
 *
 */
public class DataAnish {

  /**
   * reads the file data and loads it into the bank
   * 
   * @param bank
   * @return true if the data was successfully loaded, false otherwise
   */
  public static boolean loadFromFile(BankATM bank) {

    String username = null;
    int hashedPassword = 0;
    ArrayList<String> accountNames = new ArrayList<String>();
    ArrayList<Double> accountBalances = new ArrayList<Double>();
    Scanner sc; // used to read the data
    Scanner countersc; // used to count the lines each user takes

    try {
      File myFile = new File("data.txt");
      sc = new Scanner(myFile);
      countersc = new Scanner(myFile);
      int numLines = 0;

      while (sc.hasNextLine()) {

        accountNames = new ArrayList<String>();
        accountBalances = new ArrayList<Double>();
        numLines = 0;
        // count how many lines the current user takes up
        do {
          String nextLine = countersc.nextLine();
          if (nextLine.equals("")) {
            break;
          }
          numLines++;
        } while (countersc.hasNextLine());


        username = sc.nextLine();
        hashedPassword = sc.nextInt();
        sc.nextLine();

        // read the accounts
        for (int i = 0; i < numLines - 2; i++) {
          String[] accountInfo = sc.nextLine().trim().split(" ");
          accountNames.add(accountInfo[0]);
          accountBalances.add(Double.parseDouble(accountInfo[1]));
        }

        // load the current user data into the hashtable
        bank.addNewUser(username, hashedPassword);
        for (int i = 0; i < accountNames.size(); i++) {
          bank.getUser(username, hashedPassword).addAccount(accountNames.get(i));
          bank.getUser(username, hashedPassword).getAccount(accountNames.get(i))
              .deposit(accountBalances.get(i));
        }

        if (sc.hasNextLine()) {
          sc.nextLine();
        }
      }
      sc.close();
    } catch (FileNotFoundException e) {
      return false;
    }
    return true;
  }

  /**
   * Saves all the user data from the hashtable into data.txt
   * 
   * @param bank
   */
  public static void saveToFile(BankATM bank) {

    // creates a new file
    try {
      File myFile = new File("data.txt");
      myFile.createNewFile();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    String data = bank.toString();

    // writes the data to a file
    try {
      FileWriter myWriter = new FileWriter("data.txt");

      myWriter.write(data);
      myWriter.close();
      System.out.println("Data saved.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

}
