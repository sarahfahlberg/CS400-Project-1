
public class Account {
	private String accountName;
	private double balance;
	
	/**
	 * Constructor method for creating a new account
	 * @param accountName
	 */
	public Account(String accountName) {
		this.accountName = accountName;
		balance = 0;
	}
	
	/**
	 * Checks the current balance in this account
	 * @return the current balance
	 */
	public double getAccountBalance () {
		return balance;
	}
	
	/**
	 * Deposits money into this account
	 * @param amount to be deposited
	 * @throws IllegalArgumentException if balance overflows
	 */
	public void deposit(double amount) throws IllegalArgumentException {
		
	}
	
	/**
	 * Withdraws money from this account
	 * @param amount to be withdrawn
	 * @throws IllegalArgumentException if amount being withdrawn is more than is currently in 
	 * the account
	 */
	public void withdraw(double amount) throws IllegalArgumentException {
		
	}
	
	/**
	 * Returns a string representation of the account name and its balance
	 */
	@Override
	public String toString() {
		return "Account Name: " + accountName + ", Balance: " +
				String.valueOf(((double)Math.round(balance * 100)) / 100);
	}
}
