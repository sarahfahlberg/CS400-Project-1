public class Account {
  private String accountName;
  private double balance;

  /**
   * Constructor method for creating a new account
   * 
   * @param accountName
   */
  public Account(String accountName) {
    this.accountName = accountName;
    balance = 0;
  }
  
  // FOR DEVELOPMENT PURPOSES ONLY
  public Account(String accountName, double balance) {
    this.accountName = accountName;
    this.balance = balance;
  }

  /**
   * Checks the current balance in this account
   * 
   * @return the current balance
   */
  public double getAccountBalance() {
    return balance;
  }

  public String getAccountName() {

    return accountName;
  }

  /**
   * @throws IllegalArgumentException if balance overflows
   */
  public void deposit(double amount) throws IllegalArgumentException {

    if (balance + amount >= Double.MAX_VALUE) {
      throw new IllegalArgumentException(
          "Account balance overflow: please deposit " + "your money to a different account");
    }
    balance += amount;
  }

  /**
  @ -35,8 +38,12 @@ public class Account {
       * @throws IllegalArgumentException if amount being withdrawn is more than is currently in 
       * the account
       */
      
      public double withdraw(double amount) throws IllegalArgumentException {
          if (amount > balance) {
              throw new IllegalArgumentException("The amount you are trying to withdraw exceeds your "
                      + "balance, please withdraw a smaller amount or from a different account");
          }
          balance -= amount;
          return amount;
      }

  /**
   * Returns a string representation of the account name and its balance
   */
  @Override
  public String toString() {
    
    String str = "";
    str += accountName + " " + balance + "\n";
    return str;
  }
}
