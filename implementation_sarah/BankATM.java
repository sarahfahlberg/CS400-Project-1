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
	public void addNewUser(String username, String userPassword) throws IllegalArgumentException {
		
	}
	
	/**
	 * Removes a user from the bank
	 * @param username
	 * @param userPassword
	 * @return User object
	 * @throws NoSuchElementException if a user with that username is not found or if password is
	 * incorrect
	 */
	public User removeUser(String username, String userPassword) throws NoSuchElementException {
		
	}
	
	/**
	 * Gets a users account which can be used for login
	 * @param username
	 * @param userPassword
	 * @return the User object that matches the username
	 * @throws NoSuchElementException if a user with that username is not found or if password is
	 * incorrect
	 */
	public User getUser(String username, String userPassword) throws NoSuchElementException {
		
	}
	
	/**
	 * Returns if the bank system contains any users or not
	 * @param bankPassword
	 * @return true if there are no users in the bank system
	 */
	public boolean isEmpty() {
		
	}
	/**
	 * Number of users in the bank system
	 * @param bankPassword
	 * @return number of users in the bank system
	 */
	public int size() {
		
	}
	
}
