

package com.github.robbmj.googleprep.datastructures;

import java.util.List;

public class BinaryTree<T extends Comparable<T>> {

	private BinaryTree<T> left;
	private BinaryTree<T> right;
	private BinaryTree<T> parent; // it is used
	private T value;
	
	public BinaryTree(T value, BinaryTree<T> parent) {
		this.value = value;
		this.parent = parent;
	}
	
	public BinaryTree(List<T> values) {
		
		this (values.remove(0), null);
		
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
	
	public void print() {
		print(this);
	}
	
	private void print(BinaryTree<T> node) {
		if (node != null) {
			print(node.left);
			System.out.print(node.value + " ");
			print(node.right);
		}
	}
}
