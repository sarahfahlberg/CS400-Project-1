// --== CS400 File Header Information ==--
// Name: Sarah Fahlberg
// Email: sfahlberg@wisc.edu
// Team: CD
// Role: Backend developer
// TA: Yeping
// Lecturer: Gary Dahl
// Notes to Grader: collaboration between Sarah Fahlberg and Alex Wu

/**
 * Child class of HashTableMap that enables printing of the HashTable
 * @author sarahfahlberg and alexwu
 */
public class HashTableMapPrint<KeyType,ValueType> extends HashTableMap<KeyType,ValueType> {
	/*
	 * author: Alex
	 * gets toString for each user
	 * @return combined toString for all users in hashTable.
	 */
	//must change super.hashTable to protected instead of private
	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < super.hashTable.length; i++) {
			for (int j = 0; j < super.hashTable[i].size(); j++) {
				string += super.hashTable[i].get(j).getValue().toString() + "\n";
				
			}
		}
		return string;

	}

}

