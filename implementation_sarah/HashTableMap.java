// --== CS400 File Header Information ==--
// Name: Sarah Fahlberg
// Email: sfahlberg@wisc.edu
// Team: CD
// TA: Yeping
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.lang.Math; 

/**
 * Hash table data structure that can be used with any generic type
 */
public class HashTableMap <KeyType,ValueType> implements MapADT<KeyType,ValueType> {
	
	private int size;
	private LinkedList<Pair<KeyType,ValueType>>[] hashTable;
	
	/**
	 * Constructor for the hash table
	 * @param the initial capacity for the hash table
	 */
	public HashTableMap(int capacity) {
		hashTable = new LinkedList[capacity];
		size = 0;
		for (int i=0; i<capacity; i++)
			hashTable[i] = new LinkedList<Pair<KeyType,ValueType>>();
	}
	
	/**
	 * Constructor for the hash table, default capacity is 10
	 */
	public HashTableMap() {
		hashTable = new LinkedList[10];
		size = 0;
		for (int i=0; i<10; i++)
			hashTable[i] = new LinkedList<Pair<KeyType,ValueType>>();
	}
	
	/**
	 * Hash function for this hashtable
	 * @param key - the key being hashed
	 * @param capacity - the capacity of the hash table
	 */
	private int hash(KeyType key, int capacity) {
		return (Math.abs(key.hashCode()))%capacity;
	}
	
	/**
	 * Puts a new key value pair in the hash table
	 * @param key - key to be stored, this is the value that will be hashed
	 * @param value - the value to be stored. Can be accessed by knowing the key
	 * @return true if the pair was successfully added to the table and false if that key is already
	 * stored in the table
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {
		//See if hashTable contains key value already, if so return false
		if(size != 0) {
			for (Pair<KeyType,ValueType> pair : hashTable[hash(key, hashTable.length)]) {//FiX
				if (pair.getKey().equals(key))
					return false;
			}
		}
		//Check if need to expand capacity
		if (((double) size + 1) / hashTable.length >= 0.8) {
			//If empty, expand to ten elements
			if (hashTable.length == 0) {
				hashTable = new LinkedList[10];
				for (int i=0; i<10; i++)
					hashTable[i] = new LinkedList<Pair<KeyType,ValueType>>();
			}

			//Else, double the capacity
			else {
				int newCapacity = hashTable.length * 2;
				LinkedList<Pair<KeyType,ValueType>>[] newHashTable = new LinkedList[newCapacity];
				for (int i=0; i<newCapacity; i++)
					newHashTable[i] = new LinkedList<Pair<KeyType,ValueType>>();
				for (int i=0; i<hashTable.length; i++) {
					if (hashTable[i].size() != 0) {
						for (Pair<KeyType,ValueType> pair : hashTable[i]) {
							newHashTable[hash(pair.getKey(), newCapacity)].add(pair);
						}
					}
				}
				hashTable = newHashTable;
			}
		}
		//Add the new key-value pair to the hash table
		Pair<KeyType,ValueType> newPair = new Pair<KeyType,ValueType>(key, value);
		hashTable[hash(newPair.getKey(), hashTable.length)].add(newPair);
		size++;
		return true;
	}
	
	/**
	 * Gets a value at a specified key
	 * @param key to be looked up
	 * @return value corresponding to that key
	 * @throws NoSuchElementException if that key is not found in the table
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		for(Pair<KeyType,ValueType> pair : hashTable[hash(key, hashTable.length)]) {
			if (pair.getKey().equals(key))
				return pair.getValue();
		}
		throw new NoSuchElementException("Unable to find key");
	}
	
	/**
	 * Returns the number of elements in the hash table
	 * @return size of the hash table
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Determines if the key is in the table
	 * @param key - the key to be looked up
	 * @return true if key is found in the table, else false
	 */
	@Override
	public boolean containsKey(KeyType key) {
		for(Pair<KeyType,ValueType> pair : hashTable[hash(key, hashTable.length)]) {
			if (pair.getKey().equals(key))
				return true;
		}
		return false;
	}
	
	/**
	 * Removes an element from the table with the specified key
	 * @param key - the key from the key-value pair to be removed
	 * @return the value that matches the key, null if key-value pair is not found
	 */
	@Override
	public ValueType remove(KeyType key) {
		//determine the pair we want to remove
		Pair<KeyType,ValueType> pairFound = null;
		for(Pair<KeyType,ValueType> pair : hashTable[hash(key, hashTable.length)]) {
			if (pair.getKey().equals(key)) {
				pairFound = pair;
				break;
			}
		}
		//if key-value pair found return remove it from the linked list and return value
		if (pairFound != null) {
			hashTable[hash(key, hashTable.length)].remove(pairFound);
			size--;
			return pairFound.getValue();
		}
		//else return null
		return null;
	}
	
	/**
	 * Clears and resets the size of the hash table
	 */
	@Override
    public void clear() {
    	hashTable = new LinkedList[10];
    	size = 0;
    	for (int i=0; i<10; i++)
			hashTable[i] = new LinkedList<Pair<KeyType,ValueType>>();
    }

}