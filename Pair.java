// --== CS400 File Header Information ==--
// Name: Anish Munimadugu
// Email: munimadugu@wisc.edu
// Team: CD
// TA:  Yeping Wang
// Lecturer: Gary Dahl
// Notes to Grader: sup

/**
 * This class represents a singular node in the linked list
 * 
 * @author anish
 *
 * @param <KeyType> Generic key type
 * @param <ValueType> Generic value type
 */
public class Pair<KeyType, ValueType> {

  KeyType key;
  ValueType value;
  
  /**
   * Constructs a new linked node with the specified key and value
   * 
   * @param key Generic key type
   * @param value Generic value type
   */
  public Pair(KeyType key,ValueType value) {
    
    this.key = key;
    this.value = value;
  }
  
  public KeyType getKey() {
    return key;
  }
  
  public ValueType getValue() {
    return value;
  }

}
