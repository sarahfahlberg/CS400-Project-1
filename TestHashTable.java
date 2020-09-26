// --== CS400 File Header Information ==--
// Name: Gabriel Friederichs
// Email: ghfriederich@wisc.edu
// Team: <your team name: two letters>
// TA: <name of your team's ta>
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

public class TestHashTable {
	public static boolean test1() {
		// test constructors
		// Since the HashNode array has to be private, I'm not sure how else I'd test that this works.
		HashTableMap<String,String> myTable = new HashTableMap<>();
		HashTableMap<String,String> myTable2 = new HashTableMap<>(15);
		
		if (myTable instanceof HashTableMap && myTable2 instanceof HashTableMap) return true;
		
		return false;
	}
	
	public static boolean test2() {
		// test put and get
		HashTableMap<String,String> myTable = new HashTableMap<>();
		
		myTable.put("1", "A");
		myTable.put("2", "B");
		myTable.put("3", "C");
//		myTable.put("4", "D");
//		myTable.put("5", "E");
//		myTable.put("6", "F");
//		myTable.put("7", "G");
//		myTable.put("8", "H");
//		myTable.put("9", "I");
//		myTable.put("10", "J");
//		myTable.put("11", "K");
		myTable.put("12", "L");
//		myTable.put("13", "M");
//		myTable.put("14", "N");
//		myTable.put("15", "O");
//		myTable.put("16", "P");
//		myTable.put("17", "Q");
//		myTable.put("18", "R");
//		myTable.put("19", "S");
//		myTable.put("20", "T");
//		myTable.put("21", "U");
		
		if (myTable.containsKey("1") &&
			myTable.containsKey("2") &&
			myTable.containsKey("3") &&
			myTable.containsKey("12") &&
			myTable.get("1").equals("A") &&
			myTable.get("2").equals("B") &&
			myTable.get("3").equals("C") &&
			myTable.get("12").equals("L")) return true;
			
		return false;
	}
	
	public static boolean test3() {
		// test remove
		HashTableMap<String,String> myTable = new HashTableMap<>();
		
		myTable.put("1", "A");
		myTable.put("2", "B");
		myTable.put("3", "C");
//		myTable.put("4", "D");
//		myTable.put("5", "E");
//		myTable.put("6", "F");
//		myTable.put("7", "G");
//		myTable.put("8", "H");
//		myTable.put("9", "I");
//		myTable.put("10", "J");
//		myTable.put("11", "K");
		myTable.put("12", "L");
//		myTable.put("13", "M");
//		myTable.put("14", "N");
//		myTable.put("15", "O");
//		myTable.put("16", "P");
//		myTable.put("17", "Q");
//		myTable.put("18", "R");
//		myTable.put("19", "S");
//		myTable.put("20", "T");
//		myTable.put("21", "U");
		
//		System.out.println("Removing Element with Key: 1");
		myTable.remove("1");
//		System.out.println("Removing Element with Key: 12");
		myTable.remove("12");
		
		if (!myTable.containsKey("1") &&
			 myTable.containsKey("2") &&
			 myTable.containsKey("3") &&
			!myTable.containsKey("12")) {
			try {
				myTable.get("1");
			} catch (Exception e) {
				try {
					myTable.get("12");
				} catch (Exception e2) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean test4() {
		// test clear
		HashTableMap<String,String> myTable = new HashTableMap<>();
		
		myTable.put("1", "A");
		myTable.put("2", "B");
		myTable.put("3", "C");
//		myTable.put("4", "D");
//		myTable.put("5", "E");
//		myTable.put("6", "F");
//		myTable.put("7", "G");
//		myTable.put("8", "H");
//		myTable.put("9", "I");
//		myTable.put("10", "J");
//		myTable.put("11", "K");
		myTable.put("12", "L");
//		myTable.put("13", "M");
//		myTable.put("14", "N");
//		myTable.put("15", "O");
//		myTable.put("16", "P");
//		myTable.put("17", "Q");
//		myTable.put("18", "R");
//		myTable.put("19", "S");
//		myTable.put("20", "T");
//		myTable.put("21", "U");
		
		myTable.clear();
		if (myTable.size() == 0) return true;
		
		return false;
	}
	
	public static boolean test5() {
		// test grow
		HashTableMap<String,String> myTable = new HashTableMap<>();
		
		myTable.put("1", "A");
		myTable.put("2", "B");
		myTable.put("3", "C");
		myTable.put("4", "D");
		myTable.put("5", "E");
		myTable.put("6", "F");
		myTable.put("7", "G");
		myTable.put("8", "H");
		myTable.put("9", "I");
		myTable.put("10", "J");
		myTable.put("11", "K");
		myTable.put("12", "L");
		myTable.put("13", "M");
		myTable.put("14", "N");
		myTable.put("15", "O");
		myTable.put("16", "P");
		myTable.put("17", "Q");
		myTable.put("18", "R");
		myTable.put("19", "S");
		myTable.put("20", "T");
		myTable.put("21", "U");
		
		if (myTable.size() == 21) return true;
		
		return false;
	}
}
