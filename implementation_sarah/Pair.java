// --== CS400 File Header Information ==--
// Name: Sarah Fahlberg
// Email: sfahlberg@wisc.edu
// Team: CD
// TA: Yeping
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

public class Pair <KeyType,ValueType> {
	private KeyType key;
	private ValueType value;
	
	public Pair(KeyType key, ValueType value) {
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
