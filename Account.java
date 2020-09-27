
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
		if (balance + amount >= Double.MAX_VALUE)
			throw new IllegalArgumentException("Account balance overflow: please deposit "
					+ "your money to a different account");
		balance += amount;
	}
	
	/**
	 * Withdraws money from this account
	 * @param amount to be withdrawn
	 * @throws IllegalArgumentException if amount being withdrawn is more than is currently in 
	 * the account
	 */
	public double withdraw(double amount) throws IllegalArgumentException {
		if (amount > balance)
			throw new IllegalArgumentException("The amount you are trying to withdraw exceeds your "
					+ "balance, please withdraw a smaller amount or from a different account");
		balance -= amount;
		return amount;
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
