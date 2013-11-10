

package com.github.robbmj.googleprep.tests;

import static com.github.robbmj.googleprep.Util.Assert;

import java.util.ArrayList;
import java.util.Arrays;

import com.github.robbmj.googleprep.datastructures.BinaryTree;
import com.github.robbmj.googleprep.datastructures.Hashmap;
import com.github.robbmj.googleprep.datastructures.InvalidStateException;
import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.MaxHeap;
import com.github.robbmj.googleprep.datastructures.MinHeap;
import com.github.robbmj.googleprep.datastructures.Queue;
import com.github.robbmj.googleprep.datastructures.Stack;
import com.github.robbmj.googleprep.datastructures.graphs.EdgeListGraph;
import com.github.robbmj.googleprep.datastructures.graphs.EdgeListGraph.Vertex;


public final class DataStructureTests {

	public static EdgeListGraph<String> muskokaTestData() {
		
		EdgeListGraph<String> graph = new EdgeListGraph<>();
		
		Vertex<String> b = graph.createVertex("Bracebridge");
		Vertex<String> g = graph.createVertex("Gravenhurst");
		Vertex<String> h = graph.createVertex("Huntsville");
		Vertex<String> bay = graph.createVertex("Baysville");
		Vertex<String> bala = graph.createVertex("Bala");
		Vertex<String> port = graph.createVertex("Port Sydney");
		Vertex<String> m = graph.createVertex("Mactier");
		
		b.addAdjacentVertex(g, 12);
		b.addAdjacentVertex(h, 40);
		b.addAdjacentVertex(bay, 25);
		b.addAdjacentVertex(bala, 70, true);
	
		g.addAdjacentVertex(bala, 50);
		
		h.addAdjacentVertex(bay, 35);
		h.addAdjacentVertex(bala, 2);
		
		bala.addAdjacentVertex(h, 2);
		bala.addAdjacentVertex(m, 34);
		
		port.addAdjacentVertex(b, 30, true);
		
		return graph;
	}
	
	public static void testGraph() {
		
		EdgeListGraph<String> graph = muskokaTestData();
		
		Hashmap<String, Vertex<String>> map = graph.getNodes();
		/*
		graph.bredthFirstSearch(map.get("Baysville"));
		
		System.out.println("--------------------------------");
		
		graph.depthFirstSearch(map.get("Bala"));
				
		System.out.println("--------------------------------");*/
		System.out.println(
				graph.shortestPath(map.get("Mactier"), map.get("Baysville"))
			);
		
		//System.out.println(graph);
	}
	
	public static void testQueue() {
		Queue<Integer> queue = new Queue<>();
		
		for (int i = 0; i < 10; i++) {
			queue.queue(i);
		}
		
		Assert(queue.size() == 10, "Test queue.size() == 10");
		
		for (int i = 0; i < 10; i++) {
			Assert(queue.dequeue() == i, "Testing dequeue() did not match: " + i);
		}
		
		Assert(queue.size() == 0, "Test queue.size() == 0");
		
		System.out.println("All Queue Tests Passed");
	}
	
	public static void testStack() {
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		
		Assert(stack.size() == 10, "Test stack.size() == 10");
		
		for (int i = 9; i >= 0; i--) {
			Assert(stack.pop() == i, "Testing stack.pop() did not match: " + i);
			Assert(stack.size() == i, "Test stack.size() == 0");
		}		
		
		System.out.println("All Stack Tests Passed");
	}
	
	public static void testLinkedlist() {
		Linkedlist<String> list = new Linkedlist<>();
		
		String[] tests = new String[] { "Mike", "John", "Robb", "Loves", "Amanada", "Victoria", "Irwin", "and", "programming" };
		
		for (String str : tests) {
			list.pushFront(str);
			Assert(list.get(0).equals(str), "Testing the first element in list == the last element added there");
		}
		
		Assert(list.size() == tests.length, "Testing list.size() is == to 9");
		
		for (int i = 0; i < tests.length; i++) {
			String item = list.removeBack();
			Assert(item.equals(tests[i]), "Test list.removeBack() != " + tests[i]);
		}
		
		Assert(list.size() == 0, "Testing list.size() is == to 0");
		Assert(list.contains("") == false, "Testing that an empty list contains nothing");
		
		for (int i = tests.length - 1; i >= 0; i--) {
			list.add(tests[i]);
		}
		
		Assert(list.contains("John"), "Testing list conations the value 'John'");
		Assert(list.contains("Victoria"), "Testing list conations the value 'Victoria'");
		
		list.delete(3);
		Assert(!list.contains("Victoria"), "Testing list.delete(int): list conations the value 'Victoria'");
		
		list.delete("John");
		Assert(!list.contains("John"), "Testing list.delete(T): list conations the value 'John'");
		
		// TODO when i written insert test it here
		
		for (int i = tests.length - 1; i >= 0; i--) {
			if (tests[i].equals("John") || tests[i].equals("Victoria")) {
				// remove when insert is written
				continue;
			}
			String item = list.removeFront();
			Assert(item.equals(tests[i]), "Test list.removeFront() != " + tests[i]);
		}
		
		Assert(list.size() == 0, "Testing list.size() is == to 0");
		
		// test exception conditions
		boolean threw = false;
		
		try {
			list.get(0);
		} catch (IndexOutOfBoundsException e) {
			threw = true;
		}
		
		Assert(threw, "Calling get(0) on empty list did not throw exception");
		threw = false;
		
		try {
			list.delete(0);
		} catch (IndexOutOfBoundsException e) {
			threw = true;
		}
		
		Assert(threw, "Calling delete(0) on empty list did not throw exception");
		threw = false;
		
		try {
			list.removeBack();
		} catch (InvalidStateException e) {
			threw = true;
		}
		
		Assert(threw, "Calling removeBack() on empty list did not throw exception");
		threw = false;
		
		try {
			list.removeFront();
		} catch (InvalidStateException e) {
			threw = true;
		}
		
		Assert(threw, "Calling removeBack() on empty list did not throw exception");
		threw = false;
		
		System.out.println("All Linkedlist Tests Passed");
	}
	
	public static void testHashmap() {
		Hashmap<String, Integer> myMap = new Hashmap<>(1);
		myMap.add("one", 1);
		myMap.add("two", 2);
		myMap.add("three", 3);
		myMap.add("four", 4);
		myMap.add("five", 5);
			
		Assert(myMap.get("one") == 1, 	"Tests the key 'one' maps to '1'");
		Assert(myMap.get("two") == 2, 	"Tests the key 'two' maps to '2'");
		Assert(myMap.get("three") == 3, "Tests the key 'three' maps to '3'");
		Assert(myMap.get("four") == 4, 	"Tests the key 'four' maps to '4'");
		Assert(myMap.get("five") == 5, 	"Tests the key 'five' maps to '5'");
		Assert(myMap.get("") == null, 	"Tests the key '' maps to null");
				
		myMap.add("five", 6);
		Assert(myMap.get("five") == 6, 	"Tests the key 'five' maps to '6'");
		
		System.out.println("All Hashmap Tests Passed");
	}
	
	public static void testBTree() {
		Integer[] tests = new Integer[] { 7, 3, 12, 1, 6, 9, 9, 13 };
		ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(tests));
		
		BinaryTree<Integer> bTree = new BinaryTree<>(ints);
		
		try {
			bTree.traverse(bTree.new TreeCallback<Integer>() {
				int test = 0;
				@Override
				public Integer call() throws Exception {
					Assert(nodeValue >= test, "Binary Tree is returning items out of order");
					test = nodeValue;
					return null;
				}
			});
		} catch (Exception e) { }
		
		System.out.println("All BinaryTree Tests Passed");
	}
	
	public static void testMaxHeap() {
		Integer[] tests = new Integer[] { 7, 3, 12, 1, 6, 9, 9, 13 };
		ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(tests));
		
		Arrays.sort(tests);
		
		MaxHeap<Integer> heap = new MaxHeap<>(ints);
				
		for (int i = tests.length - 1; i >= 0; i--) {
			int top = heap.remove();
			Assert(top == tests[i], "Testing MaxHeap, heap removing in non decending order: " + top + " != " + tests[i]);
		}
		
		System.out.println("All MaxHeap Tests Passed");
	}
	
	public static void testMinHeap() {
		Integer[] tests = new Integer[] { 7, 3, 12, 1, 6, 9, 9, 13 };
		ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(tests));
		
		Arrays.sort(tests);
		
		MinHeap<Integer> heap = new MinHeap<>(ints);
				
		for (int i = 0; i < tests.length; i++) {
			int top = heap.remove();
			Assert(top == tests[i], "Testing MinHeap, heap removing in non asending order: " + top + " != " + tests[i]);
		}
		System.out.println("All MinHeap Tests Passed");
	}
}
