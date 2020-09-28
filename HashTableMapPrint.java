
public class HashTableMapPrint<KeyType,ValueType> extends HashTableMap<KeyType,ValueType> {
	@Override
	public String toString() {
		String fullString = "";
		//must change field of hashTable or whatever the name of the array is to protected
		for (int i=0; i<super.hashTable.length; i++) {
			for(Pair<KeyType,ValueType> pair : hashTable[i]) {
				fullString += pair.getKey().toString() + pair.getValue().toString();
			}
		}
		return fullString;
	}

}

