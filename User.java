import java.util.ArrayList;

public class User {
  
  private ArrayList<Account> accounts = new ArrayList<Account>(); // all your accounts
  private int password;
  private String username; // aka the key
  
  public User() {
    
  }
  
  public User(String username, int password) {
    
    this.password = password;
    this.username = username;
  }
  
  public boolean checkUsername(String username) {
    
    if ( this.username.equals(username)) {
      return true;
    }
    return false;
  }
  
  public boolean checkPassword(int password) {
    
    if ( this.password == password){
      return true;
    }
    return false;
  }


}
