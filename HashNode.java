
/**
 * This class represents a singular node in the linked list
 * 
 * @author Team CD
 *
 * @param <KeyType> Generic key type
 * @param <ValueType> Generic value type
 */
public class HashNode {

  String key; // aka full unhashed username
  User value;
  
  /**
   * Constructs a new linked node with the specified key and value
   * 
   * @param key Generic key type
   * @param value Generic value type
   */
  public HashNode(String key,User value) {
    
    this.key = key;
    this.value = value;
  }

}
