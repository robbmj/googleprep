package com.github.robbmj.googleprep.datastructures;

import java.lang.reflect.Array;
import java.util.Iterator;

public class Slice<T> implements Iterable<T> {

	private int size, capacity;
	// look into implanting this natively
	private T[] buffer;
	
	@SuppressWarnings("unchecked")
	public Slice(Class<?> c, int capacity) {
		this.size = 0;
		this.capacity = capacity;
		this.buffer = (T[])Array.newInstance(c, capacity);
	}
	
	public int size() {
		return size;
	}
	
	public void insert(T value, int i) {
		if (i < size) {
			if (size >= capacity) {
				doubleBuffer();
			}
			shiftRight(i);
			this.buffer[i] = value;
			size++;
		}
	}
	
	public void add(T value) {
		this.buffer[size++] = value;
		if (size >= capacity) {
			doubleBuffer();
		}
	}
	
	public T get(int i) {
		if (i >= size) {
			return null;
		}
		return buffer[i];
	}
	
	public T remove(int i) {
	
		T item = get(i);
		
		if (item == null) {
			return item;
		}
		
		shiftLeft(i);
		if (--size == capacity / 4) {
			halfBuffer();
		}
		return item;
	}
	
	public int getCapacity() {
		return capacity;
	}

	@SuppressWarnings("unchecked")
	private void halfBuffer() {
		T[] oldBuffer = buffer;
		capacity /= 2;
		buffer = (T[])Array.newInstance(buffer.getClass().getComponentType(), capacity);
		int oldSize = size;
		size = 0;
		copy(oldBuffer, oldSize);
	}
	
	@SuppressWarnings("unchecked")
	private void doubleBuffer() {
		T[] oldBuffer = buffer;
		capacity *= 2;
		buffer = (T[])Array.newInstance(buffer.getClass().getComponentType(), capacity);
		int oldSize = size;
		size = 0;
		copy(oldBuffer, oldSize);
	}
	
	private void copy(T[] from, int stopAt) {
		for (T e : from) {
			if (size == stopAt) {
				return;
			}
			buffer[size++] = e;
		}
	}
	
	private void shiftRight(int i) {
		for (int n = size; n > i; n--) {
			swap(n, n - 1);
		}
	}
	
	private void shiftLeft(int i) {
		for (i += 1; i < size; i++) {
			swap(i, i - 1);
		}
	}
	
	private void swap(int from, int to) {
		T temp = this.buffer[from];
		this.buffer[from] = this.buffer[to];
		this.buffer[to] = temp;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new SpliceIterator();
	}
	
	class SpliceIterator implements Iterator<T>  {

		private int i;
		
		SpliceIterator() {
			i = 0;
		}
		
		@Override
		public boolean hasNext() {
			return i == Slice.this.size;
		}

		@Override
		public T next() {
			return Slice.this.buffer[i++];
		}

		@Override
		public void remove() { }
	}
}
