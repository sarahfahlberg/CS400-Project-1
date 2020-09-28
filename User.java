import java.util.ArrayList;
import java.util.NoSuchElementException;

public class User {
	private String username;
	private int hashedPassword;
	private HashTableMap<String, Account> accounts;
	
	/**
	 * Constructor for new user
	 * @param username to access user's accounts
	 * @param password to secure the user's accounts
	 */
	public User (String username, int hashedPassword) {
		this.username = username;
		this.hashedPassword = hashedPassword;
		accounts = new HashTableMap<>();
	}
	
	/**
	 * Checks if the password entered hashes to the same thing as the user's password
	 * @param password attempt
	 * @return true if user entered correct password to access the account
	 */
	public boolean correctPassword(int hashedPassword) {
		if (this.hashedPassword == hashedPassword)
			return true;
		return false;
	}
	
	/**
	 * Allows user to change password given that they know the old password
	 * @param oldPassword
	 * @param newPassword
	 * @throws IllegalArgumentException if oldPassword does not match current password
	 */
	public void changePassword (int hashedOldPassword, int hashedNewPassword) throws IllegalArgumentException {
		if (!correctPassword(hashedOldPassword))
			throw new IllegalArgumentException("Wrong password was entered");
		hashedPassword = hashedNewPassword;
	}
	
	/**
	 * Adds an account to the user's profile
	 * @param accountName
	 * @throws IllegalArgumentException if account with that name already exists
	 */
	public void addAccount(String accountName) throws IllegalArgumentException {
		if (accounts.put(accountName, new Account(accountName)))
			return;
		throw new IllegalArgumentException("An account with that name already exists, please enter"
				+ " a different name");
	}
	
	/**
	 * Removes an account under the user's profile
	 * @param accountName
	 * @throws NoSuchElementException if account with that name does not exist
	 */
	public void removeAccount(String accountName) throws NoSuchElementException {
		Account accountFound = accounts.remove(accountName);
		if (accountFound == null)
			throw new NoSuchElementException("No account with that name was found, please enter "
					+ "a different account name or create a new account.");
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
		try {
			return accounts.get(accountName);
		} catch (NoSuchElementException e){
			throw new NoSuchElementException("No account with that name was found, please enter "
					+ "a different account name or create a new account.");
		}
	}
	
	/*
	 * @author: Alex
	 * creates desired toString in the format:
	 * name
	 * hashedPassword
	 * accountName accountPassword
	 * accountName accountPassword
	 * accountName accountPassword
	 */
	public String toString() {
		String s = username + "\n";
		s += hashedPassword + "\n";
		
		s += accounts.toString();

		s += "\n";
		return s; 
	}
	
}
