// --== CS400 File Header Information ==--
// Name: Gabriel Friederichs
// Email: ghfriederich@wisc.edu
// Team: <your team name: two letters>
// TA: <name of your team's ta>
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

public class HashNode {
	// Some of these variables and their corresponding functions will likely never be
	// used in an actual implementation, but they may helpful for debugging.
	
	private int index;

	private Object key;
	private Object value;
	
	private HashNode previous;
	private HashNode next;
	
	public HashNode(int index, Object key, Object value) {
		this.index = index;
		this.key = key;
		this.value = value;
		this.previous = null;
		this.next = null;
	}
	
	
	
	public int getIndex() {
		return index;
	}


	
	public Object getKey() {
		return key;
	}
	
	
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	
	
	public HashNode getNext() {
		return next;
	}

	public void setNext(HashNode next) {
		this.next = next;
	}



	public HashNode getPrevious() {
		return previous;
	}

	public void setPrevious(HashNode previous) {
		this.previous = previous;
	}
}
