// --== CS400 File Header Information ==--
// Name: Anish Munimadugu
// Email: munimadugu@wisc.edu
// Team: CD
// TA: Yeping Wang
// Lecturer: Gary Dahl
// Notes to Grader: sup

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class implements the hash table and contains all the methods required to add and retrieve
 * key value pairs with proper functionality
 * 
 * @author anish
 *
 * @param <KeyType>   Generic key type
 * @param <ValueType> Generic value type
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private int capacity;
  private int size;
  protected LinkedList<Pair>[] table;


  /**
   * Constructs a new HashTableMap with the specified capacity
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
   * Constructs a new HashTableMap with a default capacity of 10
   * 
   */
  public HashTableMap() {

    capacity = 10;
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
  public boolean put(KeyType key, ValueType value) {
    int index = Math.abs(key.hashCode()) % capacity;
    LinkedList<Pair> list = table[index];

    if (list == null) { // no other key hashed to the same index before
      list = new LinkedList();
      list.add(new Pair(key, value));
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

      list.add(new Pair(key, value)); // add to the end of the linked list
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
  public ValueType get(KeyType key) throws NoSuchElementException {

    int index = Math.abs(key.hashCode()) % capacity;
    LinkedList<Pair> list = table[index];

    if (list == null) {
      throw new NoSuchElementException("No such key exists");
    }
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).key.equals(key)) {
        return (ValueType) list.get(i).value;
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
  public boolean containsKey(KeyType key) {

    int index = Math.abs(key.hashCode()) % capacity;
    LinkedList<Pair> list = table[index];

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
  public ValueType remove(KeyType key) {

    int index = Math.abs(key.hashCode()) % capacity;
    LinkedList<Pair> list = table[index];

    if (list == null) {
      return null; // no value with that key
    }
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).key.equals(key)) {
        ValueType removedValue = (ValueType) list.get(i).value;
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

    LinkedList<Pair>[] newTable;

    if (size >= 0.8 * capacity) {
      newTable = new LinkedList[capacity * 2];
    } else {
      return;
    }

    for (int i = 0; i < capacity; i++) {
      if (table[i] == null) {
        continue;
      } else {
        for (Pair node : table[i]) {
          int newIndex = Math.abs(node.key.hashCode()) % (capacity * 2);
          if (newTable[newIndex] == null) {
            newTable[newIndex] = new LinkedList<Pair>();
          }
          newTable[newIndex].add(new Pair(node.key, node.value));
        }
      }
    }

    // save changes
    table = newTable;
    capacity *= 2;
  }
  
  /**
   * Returns a string representation of the hashtable
   */
  public String toString() {

    String fullString = "";
    for (int i = 0; i < table.length; i++) {
      if (table[i] != null) {
        for (Pair<KeyType, ValueType> pair : table[i]) {
          fullString += pair.getValue().toString();
        }
      }
    }

    return fullString;
  }

}
