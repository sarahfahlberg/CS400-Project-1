

public class TestDriver extends TestFunctions {
	public static void main(String[] args) {
		System.out.println("------------------");
		System.out.println("Account.java Tests");
		System.out.println("------------------");
		System.out.println("Account.java constructor:            " + Account_constructor());
		System.out.println("Account.java deposit():              " + Account_deposit());
		System.out.println("Account.java withdraw():             " + Account_withdraw());
		System.out.println("Account.java getAccountBalance():    " + Account_getAccountBalance());
		System.out.println("Account.java toString():             " + Account_toString());
		System.out.println();
		System.out.println("---------------");
		System.out.println("User.java Tests");
		System.out.println("---------------");
		System.out.println("User.java constructor:               " + User_constructor());
		System.out.println("User.java correctPassword():         " + User_correctPassword());
		System.out.println("User.java addAccount():              " + User_addAccount());
//		System.out.println("User.java listAccounts():            " + User_listAccounts());
		System.out.println("User.java getAccount():              " + User_getAccount());
		System.out.println("User.java toString():                " + User_toString());
		System.out.println("User.java toString2():               " + User_toString2());
		System.out.println();
		System.out.println("------------------");
		System.out.println("BankATM.java Tests");
		System.out.println("------------------");
		System.out.println("BankATM.java constructor:            " + BankATM_constructor());
		System.out.println("BankATM.java addNewUser():           " + BankATM_addNewUser());
		System.out.println("BankATM.java removeuser():           " + BankATM_removeUser());
		System.out.println("BankATM.java getUser():              " + BankATM_getUser());
		System.out.println("BankATM.java size():                 " + BankATM_size());
		System.out.println("BankATM.java isEmpty():              " + BankATM_isEmpty());
		System.out.println("BankATM.java toString():             " + BankATM_toString());
		System.out.println();
	}
}
