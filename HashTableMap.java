
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class implements the hash table and contains all the methods required to add and retrieve
 * key value pairs with proper functionality
 * 
 * @author Team CD
 *
 * @param <String>  the username is used as the key
 * @param <User>    the User object
 */
public class HashTableMap implements MapADT<String, User> {

  int capacity;
  private int size;
  
  private LinkedList<HashNode>[] table;

  /**
   * Constructs a new HashTableMap with the specified capacity and master password
   * 
   * @param capacity the capacity of the table
   */
  public HashTableMap(int capacity) {

    this.capacity = capacity;
    
    size = 0;
    table = new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      table[i] = null;
    }
    
  }

  /**
   * adds a new key-value pair to the hashtable. If the table becomes too packed it will grow.
   */
  @Override
  public boolean put(String key, User value) {
    int index = Math.abs(key.hashCode()) % capacity;
    LinkedList<HashNode> list = table[index];

    if (list == null) { // no other key hashed to the same index before
      list = new LinkedList();
      list.add(new HashNode(key, value));
      table[index] = list;
      size++;
      grow();
      return true;
    } else {
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).key.equals(key)) {
          return false; // a value with this key already exists
        }
      }

      list.add(new HashNode(key, value)); // add to the end of the linked list
      table[index] = list;
      size++;
      grow();
      return true;
    }
  }

  /**
   * returns the value associated with the key parameter
   * 
   * @throws NoSuchElementException if no pair is found with the specified key
   */
  @Override
  public User get(String key) throws NoSuchElementException {

    int index = Math.abs(key.hashCode()) % capacity;
    LinkedList<HashNode> list = table[index];

    if (list == null) {
      throw new NoSuchElementException("No such key exists");
    }
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).key.equals(key)) {
        return (User) list.get(i).value;
      }
    }

    throw new NoSuchElementException("No such key exists");

  }

  /**
   * returns the number of key-value pairs in the table
   */
  @Override
  public int size() {

    return size;
  }

  /**
   * returns true if a pair with the specified key exists in the table and false if it doesn't
   */
  @Override
  public boolean containsKey(String key) {

    int index = Math.abs(key.hashCode()) % capacity;
    LinkedList<HashNode> list = table[index];

    if (list == null) {
      return false;
    }
    for (int i = 0; i < list.size(); i++) { // traverse the linked list
      if (list.get(i).key.equals(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * removes a key-value pair after taking the key as a parameter
   */
  @Override
  public User remove(String key) {

    int index = Math.abs(key.hashCode()) % capacity;
    LinkedList<HashNode> list = table[index];

    if (list == null) {
      return null; // no value with that key
    }
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).key.equals(key)) {
        User removedValue = (User) list.get(i).value;
        list.remove(i);
        table[index] = list;
        size--;
        return removedValue;
      }
    }

    return null; // no value with that key
  }

  /**
   * clears the table
   */
  @Override
  public void clear() {

    table = new LinkedList[capacity];
    size = 0;
  }

  /**
   * grows and rehashes all the key-value pairs to new indexes. Runs every time a new pair is added.
   */
  private void grow() {

    LinkedList<HashNode>[] newTable;

    if (size >= 0.8 * capacity) {
      newTable = new LinkedList[capacity * 2];
    } else {
      return;
    }

    for (int i = 0; i < capacity; i++) {
      if (table[i] == null) {
        continue;
      } else {
        for (HashNode node : table[i]) {
          int newIndex = Math.abs(node.key.hashCode()) % (capacity * 2);
          if (newTable[newIndex] == null) {
            newTable[newIndex] = new LinkedList<HashNode>();
          }
          newTable[newIndex].add(new HashNode(node.key, node.value));
        }
      }
    }

    // save changes
    table = newTable;
    capacity *= 2;
  }

}
