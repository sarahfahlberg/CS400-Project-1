

// --== CS400 File Header Information ==--
// Name: Sarah Fahlberg
// Email: sfahlberg@wisc.edu
// Team: CD
// Role: Backend developer
// TA: Yeping
// Lecturer: Gary Dahl
// Notes to Grader: collaboration between Sarah Fahlberg and Alex Wu

public class HashTableMapPrint<KeyType,ValueType> extends HashTableMap<KeyType,ValueType> {
	/*
	 * author: Alex
	 * gets toString for each user
	 * @return combined toString for all users in hashTable.
	 */
	@Override
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

