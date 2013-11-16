

package com.github.robbmj.googleprep.datastructures;

import java.util.concurrent.Callable;

public class BinaryTree<T extends Comparable<T>> {

	private BinaryTree<T> left;
	private BinaryTree<T> right;
	@SuppressWarnings("unused")
	private BinaryTree<T> parent; // it is used
	private T value;
	
	public BinaryTree(T value) {
		this (value, null);
	}
	
	public BinaryTree(T value, BinaryTree<T> parent) {
		this.value = value;
		this.parent = parent;
	}
	
	public BinaryTree(Linkedlist<T> values) {
		
		this (values.removeFront(), null);
		
		for (T newValue : values) {
			this.add(newValue);
		}
	}
	
	public void add(T newValue) {

		int comparison = newValue.compareTo(this.value);
		
		if (comparison > 0) {
			if (this.right == null) {
				this.right = new BinaryTree<T>(newValue, this);
			}
			else {
				this.right.add(newValue);
			}
		}
		else {
			if (this.left == null) {
				this.left = new BinaryTree<T>(newValue, this);
			}
			else {
				this.left.add(newValue);
			}
		}
	}
	
	public boolean contains(T value) {
		int c = this.value.compareTo(value);
		if (c == 0) {
			return true;
		}
		else if (c > 0) {
			if (this.left != null) {
				return this.left.contains(value);
			}
		}
		else {
			if (this.right != null) {
				return this.right.contains(value);
			}
		}
		return false;
	}
	
	public void traverse(TreeCallback<T> cb) throws Exception {
		if (this.left != null) {
			this.left.traverse(cb);	
		}
		cb.setNodeValue(this.value);
		cb.call();
		if (this.right != null) {
			this.right.traverse(cb);	
		}
	}
	
	public void print() {
		try {
			traverse(new TreeCallback<T>() {
				@Override
				public T call() throws Exception {
					System.out.print(nodeValue + " ");
					return nodeValue;
				}
			});
		} catch(Exception e) { }
	}

	@SuppressWarnings("hiding")
	public abstract class TreeCallback<T> implements Callable<T> {

		protected T nodeValue;
		
		public final void setNodeValue(T nodeValue) {
			this.nodeValue = nodeValue;
		}
	}
}

