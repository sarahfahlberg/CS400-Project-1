import java.util.NoSuchElementException;

// --== CS400 File Header Information ==--
// Name: Gabriel Friederichs
// Email: ghfriederich@wisc.edu
// Team: CD
// Role: Test Engineer
// TA: Yeping
// Lecturer: Gary Dahl
// Notes to Grader: "// ERROR #" comments written in column 1 are placed there to make debug points
//					drastically easier to find. These are not accidental breaches of coding
//					guidelines.

public class TestFunctions {
	static final String ERROR_1 = ">> Error [1] in ";
	static final String ERROR_2 = ">> Error [2] in ";
	static final String ERROR_3 = ">> Error [3] in ";
	static final String ERROR_4 = ">> Error [4] in ";
//	final String ERROR_5 = ">> Error [5] in "; // currently unused
	
	
	
	/*
	 * ------------------
	 * Account.java Tests
	 * ------------------
	 */
	
	static Account zeroBalanceAccount = new Account("Test1");
	static Account depositAndWithdrawAccount = new Account("Test2");
	static Account badDepositAccount = new Account("Test3");
	static Account badWithdrawAccount = new Account("Test4");
	
	/**
	 * Asserts Account.java constructor functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean Account_constructor() {
		boolean passing = true;
		
		// asserts instance of Account
		if (!(zeroBalanceAccount instanceof Account)) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts Account.java deposit() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean Account_deposit() {
		boolean passing = true;
		
		// asserts valid deposit does not throw exception
		try {
			depositAndWithdrawAccount.deposit(500);
		} catch (IllegalArgumentException e) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// asserts invalid deposit throws exception
		try {
			badDepositAccount.deposit(Double.MAX_VALUE + 1.00);
			
// ERROR 2
			// these lines execute if an exception is NOT thrown
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		} catch (IllegalArgumentException e) {
			// exception is expected
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts Account.java withdraw() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean Account_withdraw() {
		boolean passing = true;
		
		// asserts a valid withdraw() does NOT throw exceptions and returns the correct value
		try {
			double amount = 250.00;
			double returnedAmount = depositAndWithdrawAccount.withdraw(amount);
			
			if (returnedAmount != returnedAmount) {
// ERROR 1
				passing = false;
				System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
			}
		} catch (IllegalArgumentException e) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// asserts an invalid withdraw() throws an exception
		try {
			double amount = 250.00;
			zeroBalanceAccount.withdraw(amount);
			
// ERROR 3
			// these lines execute if an exception is NOT thrown
			passing = false;
			System.out.println(ERROR_3 + new Throwable().getStackTrace()[0].getMethodName());
		} catch (IllegalArgumentException e) {
			// exception is expected
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts Account.java getAccountBalance() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean Account_getAccountBalance() {
		boolean passing = true;
		
		double expectedBal1 = 0.00;
		double expectedBal2 = 250.00;
		
		// Test Accounts List
		// ------------------
		// Account zeroBalanceAccount = new Account("Test1");
		// Account depositAndWithdrawAccount = new Account("Test2");
		// Account badDepositAccount = new Account("Test3");
		// Account badWithdrawAccount = new Account("Test4");
				
		// asserts all accounts have the expected balances
		if (!(zeroBalanceAccount.getAccountBalance() == expectedBal1)) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		if (!(depositAndWithdrawAccount.getAccountBalance() == expectedBal2)) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		if (!(badDepositAccount.getAccountBalance() == expectedBal1)) {
// ERROR 3
			passing = false;
			System.out.println(ERROR_3 + new Throwable().getStackTrace()[0].getMethodName());
		}
		if (!(badWithdrawAccount.getAccountBalance() == expectedBal1)) {
// ERROR 4
			passing = false;
			System.out.println(ERROR_4 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts Account.java toString() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean Account_toString() {
		boolean passing = true;
		
		String expected1 = "Account Name: Test1, Balance: 0.0";
		String expected2 = "Account Name: Test2, Balance: 250.0";
		String expected3 = "Account Name: Test3, Balance: 0.0";
		String expected4 = "Account Name: Test4, Balance: 0.0";
		
		// Test Accounts List
		// ------------------
		// Account zeroBalanceAccount = new Account("Test1");
		// Account depositAndWithdrawAccount = new Account("Test2");
		// Account badDepositAccount = new Account("Test3");
		// Account badWithdrawAccount = new Account("Test4");
				
		// asserts all accounts' toString representations have the expected values
		if (!(zeroBalanceAccount.toString().equals(expected1))) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
			System.out.println(zeroBalanceAccount.toString());
		}
		if (!(depositAndWithdrawAccount.toString().equals(expected2))) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
			System.out.println(depositAndWithdrawAccount.toString());
		}
		if (!(badDepositAccount.toString().equals(expected3))) {
// ERROR 3
			passing = false;
			System.out.println(ERROR_3 + new Throwable().getStackTrace()[0].getMethodName());
			System.out.println(badDepositAccount.toString());
		}
		if (!(badWithdrawAccount.toString().equals(expected4))) {
// ERROR 4
			passing = false;
			System.out.println(ERROR_4 + new Throwable().getStackTrace()[0].getMethodName());
			System.out.println(badWithdrawAccount.toString());
		}
		
		// method testing finished
		return passing;
	}
	
	
	
	/*
	 * ---------------
	 * User.java Tests
	 * ---------------
	 */
	
	static String username = "Username";
	static int hashedPassword = 5;
	static User user1 = new User(username, hashedPassword);
	
	/**
	 * Asserts User.java constructor functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean User_constructor() {
		boolean passing = true;
		
		// asserts instance of User
		if (!(user1 instanceof User)) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts User.java correctPassword() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean User_correctPassword() {
		boolean passing = true;
		
		// asserts the correct hashed password returns true
		if (!user1.correctPassword(hashedPassword)) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// asserts an incorrect hashed password returns false
		if (user1.correctPassword(8)) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts User.java changePassword() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean User_changePassword() {
		boolean passing = true;
		
		// asserts an invalid changePassword() throws an exception
		try {
			user1.changePassword(hashedPassword + 1, 15);
			
// ERROR 1
			// these lines execute if an exception is NOT thrown
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		} catch (IllegalArgumentException e) {
			// exception is expected
		}
		
		// asserts a valid changePassword() does NOT throw an exception
		try {
			user1.changePassword(hashedPassword, 15); // changes hashed password from 5 to 15
			user1.changePassword(15, hashedPassword); // changes hashed password from 15 to 5
		} catch (IllegalArgumentException e) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts User.java addAccount() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean User_addAccount() {
		boolean passing = true;
		
		// asserts an account with a unique name can be added
		try {
			user1.addAccount("Test Account");
			user1.addAccount("Test Account 2");
			user1.addAccount("Test Account 3");
		} catch (IllegalArgumentException e) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// asserts an account with a non-unique name cannot be added
		try {
			user1.addAccount("Test Account");
			
// ERROR 2
			// these lines execute if an exception is NOT thrown
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		} catch (IllegalArgumentException e) {
			// exception is expected
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts User.java removeAccount() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean User_removeAccount() {
		boolean passing = true;
		
		// asserts an invalid removeAccount() throws an exception
		try {
			user1.removeAccount("Test Account 33");
			
// ERROR 1
			// these lines execute if an exception is NOT thrown
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		} catch (NoSuchElementException e) {
			// exception is expected
		}
		
		// asserts a valid removeAccount() does NOT throw an exception
		try {
			user1.removeAccount("Test Account 3");
		} catch (NoSuchElementException e) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
//	/**
//	 * Asserts User.java listAccounts() functions properly.
//	 * 
//	 * @return passing - true if all tests passed, false otherwise
//	 */
//	public static boolean User_listAccounts() {
//		boolean passing = true;
//		
//		// NO SOURCE METHOD WRITTEN
//		passing = false;
//		// NO SOURCE METHOD WRITTEN
//		
//		// method testing finished
//		return passing;
//	}
	
	/**
	 * Asserts User.java getAccount() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean User_getAccount() {
		boolean passing = true;
		
		// asserts an invalid getAccount() throws an exception
		try {
			user1.getAccount("Invalid Account");
			
// ERROR 1
			// these lines execute if an exception is NOT thrown
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		} catch (NoSuchElementException e) {
			// exception is expected
		}
		
		// asserts a valid getAccount() does NOT throw an exception
		try {
			user1.getAccount("Test Account 2");
		} catch (NoSuchElementException e) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts User.java toString() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean User_toString() {
		boolean passing = true;
		
		// asserts toString()'s return value contains the username and hashed password
		if (!user1.toString().contains(username) || !user1.toString().contains("" + hashedPassword)) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts User.java toString2() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean User_toString2() {
		boolean passing = true;
		
		// asserts toString2()'s return value does NOT contain the username
		if (user1.toString2().contains(username)) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
	
	
	/*
	 * ------------------
	 * BankATM.java Tests
	 * ------------------
	 */
	
	static BankATM emptyBank = new BankATM();
	static BankATM normalBank = new BankATM();
//	static String username = "Username";
//	static int hashedPassword = 5;
//	static User user1 = new User(username, hashedPassword);
	static String username2 = "Username2";
	static int hashedPassword2 = 10;
	static User user2 = new User(username2, hashedPassword2);
	
	/**
	 * Asserts BankATM.java constructor functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean BankATM_constructor() {
		boolean passing = true;
		
		// asserts instance of BankATM
		if (!(normalBank instanceof BankATM)) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts BankATM.java addNewUser() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean BankATM_addNewUser() {
		boolean passing = true;
		
		// asserts account creation with a unique username does NOT throw an exception
		try {
			normalBank.addNewUser(username, hashedPassword);
			normalBank.addNewUser(username2, hashedPassword2);
		} catch (IllegalArgumentException e) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// asserts account creation with a non-unique username throws an exception
		try {
			normalBank.addNewUser(username, hashedPassword);
			
// ERROR 2
			// these lines execute if an exception is NOT thrown
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		} catch (IllegalArgumentException e) {
			// exception is expected
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts BankATM.java removeUser() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean BankATM_removeUser() {
		boolean passing = true;
		
		// assert a valid removeUser() does NOT throw an exception and returns the correct user
		try {
			if (!normalBank.removeUser(username, hashedPassword).equals(user1)) {
// ERROR 1
				passing = false;
				System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
			}
		} catch (NoSuchElementException e) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// assert an invalid removeUser() throws an exception
		try {
			normalBank.removeUser(username, hashedPassword);
			
// ERROR 3
			// these lines execute if an exception is NOT thrown
			passing = false;
			System.out.println(ERROR_3 + new Throwable().getStackTrace()[0].getMethodName());
		} catch (NoSuchElementException e) {
			// exception expected
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts BankATM.java getUser() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean BankATM_getUser() {
		boolean passing = true;
		
		// asserts a valid getUser() does NOT throw an exception and returns the correct user
		try {
			User temp = normalBank.getUser(username2, hashedPassword2);
			if (!temp.equals(user2)) {
// ERROR 1
				passing = false;
				System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
			}
		} catch (NoSuchElementException e) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// asserts an invalid getUser() throws an exception
		try {
			normalBank.getUser(username, hashedPassword);
			
// ERROR 3
			// these lines execute when an exception is NOT thrown
			passing = false;
			System.out.println(ERROR_3 + new Throwable().getStackTrace()[0].getMethodName());
		} catch (NoSuchElementException e) {
			// exception expected
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts BankATM.java size() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean BankATM_size() {
		boolean passing = true;
		
		// asserts a non-empty bank returns the correct size
		if (!(normalBank.size() == 1)) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// asserts an empty bank returns the correct size
		if (!(emptyBank.size() == 0)) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts BankATM.java isEmpty() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean BankATM_isEmpty() {
		boolean passing = true;
		
		// asserts a non-empty bank returns false
		if (normalBank.isEmpty()) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// asserts an empty bank returns true
		if (!emptyBank.isEmpty()) {
// ERROR 2
			passing = false;
			System.out.println(ERROR_2 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
	
	/**
	 * Asserts BankATM.java toString() functions properly.
	 * 
	 * @return passing - true if all tests passed, false otherwise
	 */
	public static boolean BankATM_toString() {
		boolean passing = true;
		
		if (!normalBank.toString().contains(username)) {
// ERROR 1
			passing = false;
			System.out.println(ERROR_1 + new Throwable().getStackTrace()[0].getMethodName());
		}
		
		// method testing finished
		return passing;
	}
}
