package com.github.robbmj.googleprep.datastructures;

import java.util.ArrayList;

public abstract class Heap<T extends Comparable<T>> {
	
	protected int sortedPartition;
	
	protected ArrayList<T> heap = new ArrayList<>();

	protected abstract void heapifyUp(int index);
	
	protected abstract void heapifyDown(int index);
	
	protected void swap(int from, int to) {		
		T tempObj = heap.get(from);
		heap.set(from, heap.get(to));
		heap.set(to, tempObj);
	}
	
	protected int sortedPartition() {
		return sortedPartition;
	}
	
	public void add(T value) {
		heap.add(value);
		sortedPartition++;
	}
	
	public T remove() {
		if (heap.isEmpty()) {
			return null;
		}
		
		T obj = heap.get(0);
		
		if (heap.size() > 1) {
			T last = heap.remove(heap.size() - 1);
			heap.set(0, last);
		}
		else {
			heap.remove(0);
		}
		sortedPartition--;
		
		return obj;
	}
	
	public T peek() {
		if (this.heap.size() == 0) {
			return null;
		}
		return this.heap.get(0);
	}
	
	public void print() {
		System.out.println(heap);
	}
	
	@Override
	public String toString() {
		return heap.toString();
	}
}
