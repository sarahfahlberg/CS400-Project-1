import java.util.NoSuchElementException;
import java.util.LinkedList;
//--== CS400 File Header Information ==--
//Name: Alexander Wu
//Email: adwu2@wisc.edu
//Team: CD
//TA: Yeping
//Lecturer: Florian Heimerl  
//Notes to Grader: 
public class HashTableMap <KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	private LinkedList<Node<KeyType, ValueType>>[] hashTable;
	private int size = 0;
	private int capacity = 10;

	/**
	 * Constructor to set initial capacity and create hashTable and fills with LinkedLists
	 * @param capacity 
	 */
	public HashTableMap(int capacity) {
		this.capacity = capacity;
		hashTable = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			// each cell of hashTable is its own LinkedList
			// this makes it easier to deal with collisions (chaining)
			hashTable[i] = new LinkedList<Node<KeyType, ValueType>>();
		}
	}
	/**
	 * Constructor that sets initial capacity to 10 and fills with LinkedLists
	 */
	public HashTableMap() { //default capacity of 10 
		hashTable = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			hashTable[i] = new LinkedList<Node<KeyType, ValueType>>();
		}

	} 

	/**
	 * Finds the index that corresponds to a key by hashing and finding the
	 * remainder of dividing by the capacity.
	 * @param key
	 * @return hashed value used as index in the hashtable
	 */
	private int getListIndex(KeyType key) { // uses formula in directions
		int hash = Math.abs(key.hashCode());
		return hash%capacity;
	}

	/**
	 * This method creates a new hash table with twice the capacity and rehashes each key
	 * and places the key-value pairs in the new table accordingly.
	 */
	private void resize() {
		// grow
		LinkedList<Node<KeyType, ValueType>>[] oldHashTable = hashTable;
		capacity*=2; 
		hashTable = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) { // fill new hashTable with LinkedLists
			hashTable[i] = new LinkedList<Node<KeyType, ValueType>>();
		}

		for (int i = 0; i < capacity/2; i++) { // copy over Nodes from oldHashTable to hashTable
			for(int j = 0; j < oldHashTable[i].size(); j++) {
				int newIndex = getListIndex(oldHashTable[i].get(j).getKey());
				hashTable[newIndex].add(oldHashTable[i].get(j));
			}
		}
	}
	/**
	 * This method adds a key-value pair to the hashtable.
	 * 
	 * If the size is > or = to 80% of the capacity, call the resize() method.
	 * @return false if there is a duplicate key already in the table, return false and don't add the k-v pair
	 * true if the key was added successfully
	 */
	public boolean put(KeyType key, ValueType value) {
		if (containsKey(key))
			return false;
		int index = getListIndex(key);
		Node<KeyType, ValueType> node = new Node<KeyType, ValueType>(key, value);
		hashTable[index].add(node); 
		size++;
		if (size >= 0.8*capacity) { 
			// calls resize method so that capacity will be expanded for next call of put method
			resize(); 
		}
		return true;
	}

	/**
	 * @throws NoSuchElementException -- if the key is not in the HashTable
	 * @return the reference to the value found using given key.
	 */
	public ValueType get(KeyType key) throws NoSuchElementException {
		if (!containsKey(key))
			throw new NoSuchElementException("This key is not in the HashTableMap");

		int index = getListIndex(key);
		for (int i = 0; i < hashTable[index].size(); i++) {
			if (hashTable[index].get(i).getKey().equals(key))
				return hashTable[index].get(i).getValue();	
		}
		throw new NoSuchElementException("This key is not in the HashTableMap");

	}
	/**
	 * @return number of key-value pairs
	 */
	public int size() {
		return size;
	}

	/**
	 * used in test 5 of TestHashTable
	 * @return current capacity of the hashtable
	 */
	public int capacity() { 
		return capacity;
	}

	/**
	 * @return true if the hashtable contains given key
	 * false if the hashtable does not contain given key
	 */
	public boolean containsKey(KeyType key) {
		int index = getListIndex(key);
		for (int i = 0; i < hashTable[index].size(); i++) {
			if (hashTable[index].get(i).getKey().equals(key))
				return true;
		}
		return false;
	}

	/**
	 * removes the node corresponding to the key
	 * @return value that corresponds to given key
	 */
	public ValueType remove(KeyType key) {
		if (!containsKey(key))
			return null;
		int index = getListIndex(key);
		for (int i = 0; i < hashTable[index].size(); i++) {
			if (hashTable[index].get(i).getKey().equals(key)) {
				ValueType desVal = hashTable[index].get(i).getValue();
				hashTable[index].remove(i);
				size--;
				return desVal;
			}
		}
		return null;
	}

	/**
	 * clears the hashtable
	 */
	public void clear() {
		for (int i = 0; i < hashTable.length; i++) {
			for (int j = 0; j < hashTable[i].size(); j++) {
				hashTable[i].clear();
				size = 0;
			}
		}
	}

	/**
	 * used this method to debug earlier
	 */
	public void printMap() {
		for (int i = 0; i < hashTable.length; i++) {
			for (int j = 0; j < hashTable[i].size(); j++) {
				System.out.println(hashTable[i].get(j).getKey().toString());
			}
		}

	}

	
	
	/*
	 * author: Alex
	 * gets toString for each user
	 * @return combined toString for all users in hashTable.
	 */
	public String toString() {
		String string = "";
		for (int i = 0; i < hashTable.length; i++) {
			for (int j = 0; j < hashTable[i].size(); j++) {
				string += hashTable[i].get(j).getValue().toString() + "\n";
				
			}
		}
		return string;

	}
}