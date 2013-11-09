

package com.github.robbmj.googleprep.datastructures;

import java.math.BigInteger;
//import java.util.LinkedList;

// Hashmap with chaining
public final class Hashmap<K, V> {

	private int size;
	
	// the length of the object array
	private long capasity;
	
	// largest prime < one trillion
	private BigInteger p = new BigInteger("999999999989"); 
	// random numbers > 0 and < p
	private BigInteger a, b;
	// an array of LinkedLists
	private Object[] map;
	
	private class ListNode {
		K key;
		V value;
		public ListNode(K key, V value) {
			this.value = value;
			this.key = key;
		}
	}
	
	public Hashmap(int capasity) {
		this.capasity = capasity;
		this.size = 0;
		map = new Object[(int)capasity];
		pickRandomVals();
	}
	
	public Hashmap() {
		this(32);
	}
	
	@SuppressWarnings("unchecked")
	public void add(K key, V value) {
		int pos = uHash(key);
		
		ListNode node = new ListNode(key, value);
		
		if (map[pos] == null) {
			Linkedlist<ListNode> list = new Linkedlist<>();
			list.add(node);
			map[pos] = list;
		}
		else {
			((Linkedlist<ListNode>)map[pos]).add(node);
		}
		
		size++;
		
		if (size >= capasity) {
			doubleMapSize();
		}
	}
	
	@SuppressWarnings("unchecked")
	public V get(K key) {
		int pos = uHash(key);
		
		if (map[pos] == null) {
			return null;
		}
		
		for (ListNode node : (Linkedlist<ListNode>)map[pos]) {
			if (node.key.equals(key)) {
				return node.value;
			}
		}
		
		return null;
	}
	
	public int size() {
		return size;
	}
	
	public int capacity() {
		return (int)capasity;
	}
	
	protected int uHash(K key) {
		long k = key.hashCode();
		BigInteger k1 = new BigInteger(k + "");
		BigInteger b1 = new BigInteger(a.multiply(k1).toString());
		BigInteger b2 = new BigInteger(b1.add(b).toString());
		BigInteger b3 = new BigInteger(b2.mod(p).toString());
		BigInteger b4 = new BigInteger(b3.mod(new BigInteger(capasity + "")).toString());
		return b4.intValue();
	}
	
	private void pickRandomVals() {
		long a1 = (long)(Math.random() * p.longValue());
		long a2 = (long)(Math.random() * p.longValue());
		a = new BigInteger(Math.min(a1, a2) + "");
		b = new BigInteger(Math.max(a1, a2) + "");
	}
	
	@SuppressWarnings("unchecked")
	protected void doubleMapSize() {

		capasity *= 2;
		
		Object[] oldMap = map;
		map = new Object[(int)capasity];
		
		pickRandomVals();

		for (int i = 0; i < oldMap.length; i++) {
			if (oldMap[i] != null) {
				for (ListNode node : (Linkedlist<ListNode>)oldMap[i]) {
					add(node.key, node.value);
				}
			}
		}
	}
}
