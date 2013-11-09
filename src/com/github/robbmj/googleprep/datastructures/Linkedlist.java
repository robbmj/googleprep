

package com.github.robbmj.googleprep.datastructures;

import java.util.Iterator;

public class Linkedlist<T> implements Iterable<T> {

	private Node first;
	private Node last;
	
	private int size = 0;
	
	private class Node {
		Node left;
		Node right;
		T value;
		Node(Node left, Node right, T value) {
			this.left = left;
			this.right = right;
			this.value = value;
		}
	}
	
	public Linkedlist() { }
	
	public void add(T value) { // pushBack
		Node node = new Node(this.last, null, value);
		
		if (this.last != null) {
			this.last.right = node;
		}
		
		if (this.first == null) {
			this.first = node;
		}
		this.last = node;
		size++;
	}
	
	public void pushFront(T value) {
		Node node = new Node(null, this.first, value);
		
		if (this.first != null) {
			this.first.left = node;
		}
		
		if (this.last == null) {
			this.last = node;
		}
		this.first = node;
		size++;
	}
	
	public int size() {
		return size;
	}
	
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index = " + index + ", size = " + size);
		}
		return pGet(index).value;
	}
	
	public T removeFront() {
		
		if (size <= 0) {
			throw new InvalidStateException("can't remove front from an empty list");
		}
		
		T value = this.first.value;
		delNode(this.first);
		return value;
	}
	
	public T removeBack() {
		
		if (size <= 0) {
			throw new InvalidStateException("can't remove back from an empty list");
		}
		
		T value = this.last.value;
		delNode(this.last);
		return value;
	}
	
	private Node pGet(int index) {
		Node iTHnode = this.first;
		for (int i = 0; i < index; i++) {
			iTHnode = iTHnode.right;
		}
		return iTHnode;
	}
	
	private void delNode(Node node) {
		if (node.left != null) {
			node.left.right = node.right;
		}
		else if (size == 1) {
			this.first = null;
		}
		else {
			node.right.left = null;
			this.first = node.right;
		}
		
		if (node.right != null) {
			node.right.left = node.left;
		}
		else if (size == 1) {
			this.last = null;
		}
		else {
			node.left.right = null;
			this.last = node.left;
		}
		size--;
	}
	
	public void delete(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index = " + index + ", size = " + size);
		}
		Node iTHnode = pGet(index);
		delNode(iTHnode);
	}
	
	public boolean delete(T value) {
		
		if (size() == 0) {
			return false;
		}
		
		Node node = this.first;
		
		while (!node.value.equals(value)) {
			
			if (node.right == null) {
				return false;
			}
			node = node.right;
		}
		delNode(node);
		return true;
	}
	
	public boolean contains(T value) {
		for (T val : this) {
			if (val.equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		
		if (size() == 0) {
			return "[]";
		}
		
		String s = "[";
				
		for (T val : this) {
			s += val + ", ";
		}

		return s.substring(0, s.length() - 2) + "]";
	}

	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T> {

		private Node node = Linkedlist.this.first;
		
		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public T next() {
			T value = node.value;
			node = node.right;
			return value;
		}

		@Override
		public void remove() { }
	}
}

















