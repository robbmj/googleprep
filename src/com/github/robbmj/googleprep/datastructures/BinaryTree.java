package com.github.robbmj.googleprep.datastructures;

import java.util.List;


public class BinaryTree<T extends Comparable<T>> {

	protected BinaryNode<T> root;
	
	public BinaryTree() {}
	
	public BinaryTree(List<T> values) {
		this.root = new BinaryNode<T>(values.get(0));
		
		values.remove(0);
		
		for (T value : values) {
			this.add(value);
		}
	}
	
	public void add(T value) {
		add(value, root);
	}
	
	private void add(T value, BinaryNode<T> node) {
		int comparison = value.compareTo(node.getValue());
		
		if (comparison > 0) {
			if (node.right == null) {
				node.right = new BinaryNode<T>(value);
			}
			else {
				add(value, node.right);
			}
		}
		else {
			if (node.left == null) {
				node.left = new BinaryNode<T>(value);
			}
			else {
				add(value, node.left);
			}
		}
	}
	
	public void print() {
		print(root);
	}
	
	private void print(BinaryNode<T> node) {
		if (node != null) {
			System.out.println(node);
			print(node.left);
			print(node.right);
		}
	}
}

class BinaryNode<T extends Comparable<T>> extends Node<T> {

	BinaryNode<T> left = null; 
	BinaryNode<T> right = null;
	
	public BinaryNode(T value) {
		super(value);
	}
}
