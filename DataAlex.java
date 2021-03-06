
// --== CS400 File Header Information ==--
// Name: Alexander Wu
// Email: adwu2@wisc.edu
// Team: CD
// TA: Yeping
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class has the methods createFile(), writeFile(), and readFile(). These files work with data
 * of the User's name, hashed password, and account details for each account in the format:
 * 
 * name hashedPassword accountName accountPassword accountName accountPassword accountName
 * accountPassword
 * 
 * @author Alex
 *
 */
public class DataAlex {
    public static BankATM bank = UserInterface_Ben.bank;
    // uses inner toString methods to get the string in format shown in class header
    public static String bankString = bank.toString();

    /**
     * Creates a file called Users.txt
     * @return
     */
    public static boolean createFile() {
        File file = new File("Users.txt");
        try {
            if (file.createNewFile())
                return true;
            else
                return false;
        } catch (IOException e) {
            System.out.println("An error occurred creating the file.");
            return false;
        }
    }

    /**
     * c
     * @throws FileNotFoundException
     */
    public static void writeFile() throws FileNotFoundException {
        bank = UserInterface_Ben.bank;
        bankString = bank.toString();
        createFile();
        String fileName = "Users.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(bankString);

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * reads from a file, takes the data into variables and returns a bank with corresponding data
     * @return bank object
     * @throws FileNotFoundException
     */
    public static BankATM readFile() {
        BankATM newBank = new BankATM();
        File file = new File("Users.txt");
        if (!file.exists()) {
            createFile();
        }
        Scanner scnr;
        try {
            scnr = new Scanner(file);

            ArrayList<String> currentUser = new ArrayList<>();
            int users = 0; // remove if not used later


            while (scnr.hasNextLine()) {
                currentUser.add(scnr.nextLine().trim()); // name
                currentUser.add(scnr.nextLine().trim()); // hashed password
                // add username and hashedpassword to create a new user
                newBank.addNewUser(currentUser.get(0), Integer.parseInt(currentUser.get(1)));
                User user =
                    newBank.getUser(currentUser.get(0), Integer.parseInt(currentUser.get(1)));

                if (!scnr.hasNextLine())
                    break;

                while (scnr.hasNextLine()) {
                    String line = scnr.nextLine().trim();
                    if (line.isBlank())
                        break;
                    currentUser.add(line);
                }
                users++;




                // starts at 2 because 0 and 1 are name and hashed password, already been dealt with
                for (int i = 2; i < currentUser.size(); i++) {
                    String[] currAcc = currentUser.get(i).split(" ");
                    user.addAccount(currAcc[0]);
                    user.getAccount(currAcc[0]).deposit(Double.parseDouble(currAcc[1]));
                }
                
                currentUser.clear();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newBank;

    }
}
