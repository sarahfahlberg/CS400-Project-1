//--== CS400 File Header Information ==--
//Name: Alexander Wu
//Email: adwu2@wisc.edu
//Team: CD
//TA: Yeping
//Lecturer: Florian Heimerl  
//Notes to Grader: 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class has the methods createFile(), writeFile(), and readFile().
 * These files work with data of the User's name, hashed password, and 
 * account details for each account in the format:
 * 
 * name
 * hashedPassword
 * accountName accountPassword
 * accountName accountPassword
 * accountName accountPassword
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
		}	
		catch (IOException e) {
			System.out.println("An error occurred creating the file.");
			return false;
		}
	}
	
	/**
	 * c
	 * @throws FileNotFoundException
	 */
	public static void writeFile() throws FileNotFoundException{
		createFile();
		String fileName = "Users.txt";
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName);
			writer.print(bankString);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * reads from a file, takes the data into variables and returns a bank with corresponding data
	 * @return bank object
	 * @throws FileNotFoundException
	 */
	public static BankATM readFile() throws FileNotFoundException{

		BankATM newBank = new BankATM();
		Scanner scnr = new Scanner("Users.txt");
		ArrayList<String> currentUser = new ArrayList<>();
		int users = 0; // remove if not used later
		
		
		while(scnr.hasNextLine()) {
			currentUser.add(scnr.nextLine().trim()); // name
			currentUser.add(scnr.nextLine().trim()); // hashed password
			while (!scnr.nextLine().isBlank()) {
				int i = 0;
				currentUser.add(scnr.nextLine().trim());
				i++;
			}
			users++;
			scnr.nextLine(); // eats line in between one user and another
			
			// add username and hashedpassword to create a new user			
			newBank.addNewUser(currentUser.get(0), Integer.parseInt(currentUser.get(1)));			
			User user = newBank.getUser(currentUser.get(0), Integer.parseInt(currentUser.get(1)));
			
			// starts at 2 because 0 and 1 are name and hashed password, already been dealt with
			for (int i = 2; i < currentUser.size(); i++) { 
				String[] currAcc = currentUser.get(i).split(" "); 
				user.addAccount(currAcc[0]);
				user.getAccount(currAcc[0]).deposit(Integer.parseInt(currAcc[1]));
			}
		
			
		}
		return newBank;
		
	}
}

