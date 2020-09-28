// --== CS400 File Header Information ==--
// Name: Gabriel Friederichs
// Email: ghfriederich@wisc.edu
// Team: <your team name: two letters>
// TA: <name of your team's ta>
// Lecturer: Gary Dahl
// Notes to Grader: The "print()" statements written in column 1 are there to make it easy to see where my debugging points are.

import java.util.NoSuchElementException;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

	private HashNode[] myArray; // 1D array which stores HashNodes
	private int numberOfElements; // counter for total number of elements in this HashTableMap
	
	
	public HashTableMap(int capacity) {
		myArray = new HashNode[capacity];
		numberOfElements = 0;
	}
	
	public HashTableMap() {
		// default capacity = 10
		myArray = new HashNode[10];
		numberOfElements = 0;
	}
	
	

	@Override
	public boolean put(KeyType key, ValueType value) {
		if (containsKey(key)) return false;	// a node already exists with that key! No changes made.
		
		int putIndex = Math.abs(key.hashCode()) % myArray.length; // determines index to put node at
		HashNode newNode = new HashNode(putIndex, key, value); // creates node
		
		// will put node right at index if element is null
		// otherwise, will be placed at the end of the existing chain
		if (myArray[putIndex] == null) {
			myArray[putIndex] = newNode;
		}
		else {
			HashNode prevNode = myArray[putIndex]; // prevNode refers to an existing node
			
			// while prevNode has a next node, prevNode is set to that next node
			while (prevNode.getNext() != null) {
				prevNode = prevNode.getNext();
			}
			
			// places the new node at the end of the chain and links it to the previous node
			prevNode.setNext(newNode);
			newNode.setPrevious(prevNode);
		}
		
		++numberOfElements;
		
		if (needsToGrow()) {
			grow();
		}
		
//print();
		return true;
	}

	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		// I've elected not to check if the key exists first using containsKey() since this method is
		// so similar that it would be redundant.
		
		int getIndex = Math.abs(key.hashCode()) % myArray.length;
		HashNode currentNode = myArray[getIndex]; // the node that will be checked
		
//		if (currentNode == null) {
//			throw new NoSuchElementException("No element found at index [" + getIndex + "]!");
//		}
		
		while (currentNode != null) {
			if (currentNode.getKey().equals(key)) {
				return (ValueType) currentNode.getValue();
			}
			else {
				currentNode = currentNode.getNext();
			}
		}
		
		throw new NoSuchElementException("No element found at index [" + getIndex + "] with that key!");
	}

	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public boolean containsKey(KeyType key) {
		// Goes to the hashed index and then examines the chain to see if the key exists in it
		
		int index = Math.abs(key.hashCode()) % myArray.length;
		HashNode currentNode = myArray[index];
		
		// examines every element in the chain at the proper index
		while (currentNode != null) {
			if (currentNode.getKey().equals(key)) {
				return true;
			}
			else {
				currentNode = currentNode.getNext();
			}
		}
		
		return false;
	}

	@Override
	public ValueType remove(KeyType key) {
//print();
		if (!containsKey(key)) return null; // returns null since the key does not exist in the HashTableMap
		
		int removeIndex = Math.abs(key.hashCode()) % myArray.length;
		HashNode currentNode = myArray[removeIndex];
		
		// since containsKey() was already checked, the key *will* be found somewhere in the currentNode chain
		while (!currentNode.getKey().equals(key)) {
			currentNode = currentNode.getNext();
		}
		// after exiting, currentNode refers to the node with the matching key
		
		HashNode prevNode = currentNode.getPrevious();
		HashNode nextNode = currentNode.getNext();
		
		if (prevNode != null) {
			prevNode.setNext(nextNode); // the previous node's next now refers to the next node
		} else {
			// the removal node is at the head of the chain, so set the next node at the index
			myArray[removeIndex] = nextNode;
		}
		
		if (nextNode != null) {
			nextNode.setPrevious(prevNode); // the next node's previous now refers to the previous node
		}
		// now nothing refers to the current node
		
		// creates a dummy node so the current node value can be retained
		HashNode returnNode = new HashNode(currentNode.getIndex(), currentNode.getKey(), currentNode.getValue());
		currentNode = null; // safely sets the target node to null
		
		--numberOfElements;
//print();
		return (ValueType) returnNode.getValue();
	}

	@Override
	public void clear() {
		// removes all key-value pairs from the HashTableMap
		for (int i=0; i<myArray.length; ++i) {
			myArray[i] = null;
		}
		
		numberOfElements = 0;
	}
	
	
	
	private boolean needsToGrow() {
		if (size() >= 0.8 * myArray.length) {
			return true;
		}
		return false;
	}

	private void grow() {
		// doubles and rehashes whenever its load capacity is >= 80%
		HashNode[] newArray = new HashNode[myArray.length * 2];
		
		// iterates through every index
		for (int i=0; i<myArray.length; ++i) {
			HashNode currentNode = myArray[i];
			
			// iterates through every chain
			while (currentNode != null) {
				KeyType currentKey = (KeyType) currentNode.getKey();
				ValueType currentValue = (ValueType) currentNode.getValue();
				
				int newIndex = Math.abs(currentKey.hashCode()) % newArray.length;
				HashNode newNode = new HashNode(newIndex, currentKey, currentValue);
				
				if (newArray[newIndex] == null) {
					// adds the new node right at the index
					newArray[newIndex] = newNode;
				}
				else {
					// iterates to the end of the new chain and connects the new node
					HashNode currentNewNode = newArray[newIndex];
					
					while(currentNewNode.getNext() != null) {
						currentNewNode = currentNewNode.getNext();
					}
					// after exiting, currentNewNode refers to the node at the current end of the chain
					
					// links the end of the chain to the new end of the chain
					currentNewNode.setNext(newNode);
					newNode.setPrevious(currentNewNode);
				}
				
				currentNode = currentNode.getNext();
			}
		}
		
		this.myArray = newArray;
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

	}
	
//	private void print() {
//		// prints out the current state of the HashTableMap in an easily understandable format
//		System.out.println();
//		System.out.println("Size: " + size());
//		
//		for (int i=0; i<myArray.length; ++i) {
//			System.out.print(i + ": ");
//			
//			HashNode currentNode = myArray[i];
//			while (currentNode != null) {
//				System.out.print("<" + currentNode.getKey().toString() + " , " + currentNode.getValue().toString() +  "> ");
//				currentNode = currentNode.getNext();
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}
