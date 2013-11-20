

package com.github.robbmj.googleprep.tests;

import static com.github.robbmj.googleprep.Util.Assert;

import java.util.Arrays;

import com.github.robbmj.googleprep.datastructures.BinaryTree;
import com.github.robbmj.googleprep.datastructures.Hashmap;
import com.github.robbmj.googleprep.datastructures.Hashmap.Entry;
import com.github.robbmj.googleprep.datastructures.InvalidStateException;
import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.MaxHeap;
import com.github.robbmj.googleprep.datastructures.MinHeap;
import com.github.robbmj.googleprep.datastructures.Queue;
import com.github.robbmj.googleprep.datastructures.Slice;
import com.github.robbmj.googleprep.datastructures.Stack;
import com.github.robbmj.googleprep.datastructures.graphs.EdgeListGraph;
import com.github.robbmj.googleprep.datastructures.graphs.EdgeListGraph.Path;
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
		
		Path<String> p;
		
		p = graph.shortestPath(map.get("Bracebridge"), map.get("Mactier"));
		Assert(p.getTotalWeight() == 76, "Failed shortest path 76 == " + p.getTotalWeight());
		
		p = graph.shortestPath(map.get("Baysville"), map.get("Bala"));
		Assert(p.getTotalWeight() == 37, "Failed shortest path 37 == " + p.getTotalWeight());
		
		p = graph.shortestPath(map.get("Port Sydney"), map.get("Huntsville"));
		Assert(p.getTotalWeight() == 70, "Failed shortest path 70 == " + p.getTotalWeight());
		
		p = graph.shortestPath(map.get("Huntsville"), map.get("Port Sydney"));
		Assert(p == null, "Failed shortest path null == " + p);
		
		System.out.println("All Graph Tests Passed");

	}
		
	public static void testMinSpanningTree() {
		EdgeListGraph<String> g = muskokaTestData();
		Hashmap<String, Vertex<String>> map = g.getNodes();
		Path<String> p;
		//p = g.minimumSpanningTree(map.get("Baysville"));
		p = g.minimumSpanningTree(map.get("Bracebridge"));
		Assert(p.getTotalWeight() == 108, "Failed minSpanningTree 108 == " + p.getTotalWeight());
		
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
		
		// TODO when i have written insert test it here
		
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
			
		System.out.println("All Linkedlist Tests Passed");
	}
	
	public static void testHashmap() {
		Hashmap<String, Integer> myMap = new Hashmap<>(1);
		myMap.add("one", 1);
		myMap.add("two", 2);
		myMap.add("three", 3);
		myMap.add("four", 4);
		myMap.add("five", 5);
		
		Assert(myMap.size() == 5, "Test Hashmap.size() failed to assert 5 == " + myMap.size());
		
		Assert(myMap.get("one") == 1, 	"Tests the key 'one' maps to '1'");
		Assert(myMap.get("two") == 2, 	"Tests the key 'two' maps to '2'");
		Assert(myMap.get("three") == 3, "Tests the key 'three' maps to '3'");
		Assert(myMap.get("four") == 4, 	"Tests the key 'four' maps to '4'");
		Assert(myMap.get("five") == 5, 	"Tests the key 'five' maps to '5'");
		Assert(myMap.get("") == null, 	"Tests the key '' maps to null");
				
		myMap.add("five", 6);
		Assert(myMap.get("five") == 6, 	"Tests the key 'five' maps to '6'");
		Assert(myMap.size() == 5, "Test Hashmap.size() failed to assert 5 == " + myMap.size());
		
		// immutable iterator test
		Hashmap<String, Linkedlist<String>> iteratorTest = new Hashmap<>();
		Linkedlist<String> test = new Linkedlist<>();
		test.add("mike");
		iteratorTest.add("mike", test);
		
		// test to make sure that we can keep track of objects in the map when there value changes
		Assert(iteratorTest.get("mike").size() == 1, "Failed asserting size() 1 == " + iteratorTest.size());
		test.add("robb");
		Assert(iteratorTest.get("mike").size() == 2, "Failed asserting size() 2 == " + iteratorTest.size());
		
		for (Entry<String, Linkedlist<String>> e : iteratorTest.getIterator()) {
			e.value = new Linkedlist<String>();
			e.key = "robb";
		}
		Assert(iteratorTest.get("mike").size() == 2, "Failed asserting size() 2 == " + iteratorTest.size());
		
		System.out.println("All Hashmap Tests Passed");
	}
	
	public static void testBTree() {
		Integer[] tests = new Integer[] { 7, 3, 12, 1, 6, 9, 9, 13 };
		Linkedlist<Integer> ints = new Linkedlist<>(Arrays.asList(tests));
		
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
		Linkedlist<Integer> ints = new Linkedlist<>(Arrays.asList(tests));
		MaxHeap<Integer> heap = new MaxHeap<>(ints);
		
		Arrays.sort(tests); // so that I can test the heap removes in descending order
						
		for (int i = tests.length - 1; i >= 0; i--) {
			int top = heap.remove();
			Assert(top == tests[i], "Testing MaxHeap, heap removing in non descending order: " + top + " != " + tests[i]);
		}
		
		System.out.println("All MaxHeap Tests Passed");
	}
	
	public static void testMinHeap() {
		Integer[] tests = new Integer[] { 7, 3, 12, 1, 6, 9, 9, 13 };
		Linkedlist<Integer> ints = new Linkedlist<>(Arrays.asList(tests));
		MinHeap<Integer> heap = new MinHeap<>(ints);
		
		Arrays.sort(tests); // so that I can test the heap removes in ascending order
						
		for (int i = 0; i < tests.length; i++) {
			int top = heap.remove();
			Assert(top == tests[i], "Testing MinHeap, heap removing in non ascending order: " + top + " != " + tests[i]);
		}
		System.out.println("All MinHeap Tests Passed");
	}
	
	public static void testSlice() {
		Slice<Integer> ints = new Slice<>(Integer.class, 1);
		
		for (int i = 1; i < 32; i++) {
			ints.add(i);
			Assert(ints.size() == i, "Failed assert slice.size() " + i + " == " + ints.size());
		}
		
		ints.insert(0, 0);
		
		int i = 0;
		
		for (int n : ints) {
			Assert(n == i++, "Failed asserting slice " + i + " == " + i);
		}
		
		int size = ints.size();
		
		for (i = 0; i < size; i++) {
			int temp = ints.remove(0);
			Assert(temp == i, "Failed slice.remove() " + i +" == " + temp);
			Assert(ints.size() == size - i - 1, "Failed asserting ints.size() " + ints.size() + " == " + (size - i));
		}
		
		System.out.println("All slice Tests passed");
	}
}
