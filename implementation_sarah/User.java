
public class User {
	private String username;
	private int hashedPassword;
	public User (String username, String password) {
		
	}
	
	private int hashPassword(String password) {
		
	}
	
	public boolean correctPassword(String password) {
		if (hashedPassword == hashPassword(password))
			return true;
		return false;
	}
	
	public void addAccount(String accountName) {
		
	}
	
	public void changePassword (String oldPassword, String newPassword) {
		
	}
	
	public String listAccounts() {
		
	}
	
	public double getAccountBalance (String accountName) {
		
	}
	
	public void deposit(String accountName, double amount) {
		
	}
	
	public void withdraw(String accountName, double amount) {
		
	}
}
