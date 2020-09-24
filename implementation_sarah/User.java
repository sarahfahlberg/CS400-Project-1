import java.util.ArrayList;
import java.util.NoSuchElementException;

public class User {
	private String username;
	private int hashedPassword;
	private HashTableMap<String, Account> accountHashTable;
	private ArrayList<Account> accounts;
	
	/**
	 * Constructor for new user
	 * @param username to access user's accounts
	 * @param password to secure the user's accounts
	 */
	public User (String username, String password) {
		this.username = username;
		hashedPassword = hashPassword(password);
		accounts = new HashTableMap<>();
	}
	/**
	 * Hash function for the passwords/attempts to access account
	 * @param password
	 * @return hashed password
	 */
	private int hashPassword(String password) {
		
	}
	
	/**
	 * Checks if the password entered hashes to the same thing as the user's password
	 * @param password attempt
	 * @return true if user entered correct password to access the account
	 */
	public boolean correctPassword(String password) {
		if (hashedPassword == hashPassword(password))
			return true;
		return false;
	}
	
	/**
	 * Allows user to change password given that they know the old password
	 * @param oldPassword
	 * @param newPassword
	 * @throws IllegalArgumentException if oldPassword does not match current password
	 */
	public void changePassword (String oldPassword, String newPassword) throws IllegalArgumentException {
		if (!correctPassword(oldPassword))
			throw new IllegalArgumentException("Wrong password was entered");
		hashedPassword = hashPassword(newPassword);
	}
	
	/**
	 * Adds an account to the user's profile
	 * @param accountName
	 * @throws IllegalArgumentException if account with that name already exists
	 */
	public void addAccount(String accountName) throws IllegalArgumentException {
		
	}
	
	/**
	 * Removes an account under the user's profile
	 * @param accountName
	 * @throws NoSuchElementException if account with that name does not exist
	 */
	public void removeAccount(String accountName) throws NoSuchElementException {
		
	}
	
	/**
	 * To string to list all of the accounts
	 * @return a string representation of all of the account names and balances
	 */
	public String listAccounts() {
		
	}
	
	/**
	 * Returns the account wanting to be accessed
	 * @param accountName
	 * @return
	 * @throws NoSuchElementException if account with that name does not exist
	 */
	public Account getAccount(String accountName) throws NoSuchElementException {
		
	}
	
	/**
	 * Returns a string of the users current accounts and their balances
	 */
	public String toString() {
		
	}
	
}
