package com.github.robbmj.googleprep.datastructures;

public class Stack<T> {

	private Linkedlist<T> items;
	
	public Stack() {
		this(8);
	}
	
	public Stack(int capacity) {
		items = new Linkedlist<>();
	}
	
	public void push(T item) {
		items.pushFront(item);
	}
	
	public T pop() {
		if (size() == 0) {
			throw new InvalidStateException("Can't pop from an empty stack");
		}
		return items.removeFront();
	}
	
	public T peek() {
		if (size() == 0) {
			return null;
		}
		return items.get(0);
	}
	
	public int size() {
		return items.size();
	}
}
