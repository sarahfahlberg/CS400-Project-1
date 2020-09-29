// --== CS400 File Header Information ==--
// Name: Sarah Fahlberg
// Email: sfahlberg@wisc.edu
// Team: CD
// Role: Backend developer
// TA: Yeping
// Lecturer: Gary Dahl
// Notes to Grader: written by Sarah Fahlberg

import java.util.NoSuchElementException;

public class BankATM {
	private HashTableMap<String, User> userHashTable;
	
	/**
	 * Creates a new BankATM
	 * @param bankPassword
	 */
	public BankATM() {
		userHashTable = new HashTableMap<>();
	}
	
	/**
	 * Adds a new user to the bank
	 * @param username
	 * @param userPassword
	 * @throws IllegalArgumentException if user with that username already exists
	 */
	public void addNewUser(String username, int hashedPassword) throws IllegalArgumentException {
		if (!userHashTable.put(username, new User(username, hashedPassword)))
			throw new IllegalArgumentException("A user with that username already exists, please choose a new username");
	}
	
	/**
	 * Removes a user from the bank
	 * @param username
	 * @param userPassword
	 * @return User object
	 * @throws NoSuchElementException if a user with that username is not found or if password is
	 * incorrect
	 */
	public User removeUser(String username, int hashedPassword) throws NoSuchElementException {
		//find user
		try {
		User usernameMatch = userHashTable.get(username);
		if (usernameMatch.correctPassword(hashedPassword))
			return userHashTable.remove(username);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Username or password does not match");
		}
		throw new NoSuchElementException("Username or password does not match");
	}
	
	/**
	 * Gets a users account which can be used for login
	 * @param username
	 * @param userPassword
	 * @return the User object that matches the username
	 * @throws NoSuchElementException if a user with that username is not found or if password is
	 * incorrect
	 */
	public User getUser(String username, int hashedPassword) throws NoSuchElementException {
		try {
			User usernameMatch = userHashTable.get(username);
			if (usernameMatch.correctPassword(hashedPassword))
				return usernameMatch;
			} catch (NoSuchElementException e) {
			}
		throw new NoSuchElementException("Username or password does not match");
	}
	
	/**
	 * Returns if the bank system contains any users or not
	 * @param bankPassword
	 * @return true if there are no users in the bank system
	 */
	public boolean isEmpty() {
		if (userHashTable.size() == 0)
			return true;
		return false;
	}
	/**
	 * Number of users in the bank system
	 * @param bankPassword
	 * @return number of users in the bank system
	 */
	public int size() {
		return userHashTable.size();
	}

	@Override
	public String toString(){
        	return userHashTable.toString();
	}        	
}
