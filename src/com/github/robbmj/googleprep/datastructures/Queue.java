package com.github.robbmj.googleprep.datastructures;

public class Queue<T> {

	private Linkedlist<T> items;
	
	public Queue() {
		this(8);
	}
	
	public Queue(int capacity) {
		items = new Linkedlist<>();
	}
	
	public void queue(T item) {
		items.add(item);
	}
	
	public T dequeue() {
		if (size() == 0) {
			throw new InvalidStateException("Can't dequeue from an empty queue");
		}
		return items.removeFront();
	}
		
	public int size() {
		return items.size();
	}
}
