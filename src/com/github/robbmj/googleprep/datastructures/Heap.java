package com.github.robbmj.googleprep.datastructures;

import java.util.ArrayList;

public abstract class Heap<T extends Comparable<T>> {
	
	protected ArrayList<T> heap = new ArrayList<>();

	protected abstract void heapifyUp(int index);
	
	protected abstract void heapifyDown(int index);
	
	protected void swap(int from, int to) {		
		T tempObj = heap.get(from);
		heap.set(from, heap.get(to));
		heap.set(to, tempObj);
	}
	
	public void add(T value) {
		heap.add(value);
	}
	
	public T remove() {
		if (heap.isEmpty()) {
			return null;
		}
		
		T obj = heap.remove(0);
		
		if (heap.size() > 1) {
			T last = heap.remove(heap.size() - 1);
			heap.add(0, last);
		}
		
		return obj;
	}
	
	public void print() {
		System.out.println(heap);
	}
}
