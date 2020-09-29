import java.util.NoSuchElementException;

public class BankATM {

  private HashTableMap<String, User> userHashTable;
  private int hashedBankPassword;

  /**
   * Creates a new BankATM with a manager’s password that can be used to manage all users
   * 
   * @param bankPassword
   */
  public BankATM() {
    userHashTable = new HashTableMap<>();
  }
  
  // MIGHT NEED THIS METHOD
  public HashTableMap<String, User> getHashTable(){
    return userHashTable;
  }

  /**
   * Hashes the bank password
   * 
   * @param bankPassword
   * @return hashed version of the bankPassword for security
   */
  private int hashBankPassword(String bankPassword) {



    return -1;

  }

  /**
   * @throws IllegalArgumentException if user with that username already exists
   */
  public void addNewUser(String username, int hashedPassword) throws IllegalArgumentException {
    if (!userHashTable.put(username, new User(username, hashedPassword)))
      throw new IllegalArgumentException(
          "A user with that username already exists, please choose a new username");
  }

  /**
   * 
   * @throws NoSuchElementException if a user with that username is not found or if password is
   *                                incorrect
   */
  public User removeUser(String username, int hashedPassword) throws NoSuchElementException {
    // find user
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
   * 
   * @throws NoSuchElementException if a user with that username is not found or if password is
   *                                incorrect
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
   * 
   * @return true if there are no users in the bank system
   */
  public boolean isEmpty() {

    if (userHashTable.size() == 0) {
      return true;
    }
    return false;
  }

  /**
   * Number of users in the bank system
   * 
   * @return number of users in the bank system
   */
  public int size() throws IllegalArgumentException {

    return userHashTable.size();

  }

  @Override
  public String toString() {

    return userHashTable.toString();
  }

  /**
   * Clears all of the users currently in the bank
   * 
   * @param bankPassword
   * @throws IllegalArgumentException if bank password is incorrect
   */
  public void clearAllUsers(String bankPassword) throws IllegalArgumentException {

  }

}
