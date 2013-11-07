package datastructures;

public abstract class Node<T extends Comparable<T>> {

	private T value;
	
	public Node(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
