
public class Account {

  private double amount;
  private int accountNum; // im thinking just 1, 2, 3 and so on

  public Account() {
    amount = 0.0;
    // should assign the next available account number. Like if the user has 2 accounts already then
    // this one should have accountNum = 3
  }

}
