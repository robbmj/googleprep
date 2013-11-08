
package com.github.robbmj.googleprep;

import java.util.ArrayList;

import com.github.robbmj.googleprep.datastructures.BinaryTree;
import com.github.robbmj.googleprep.datastructures.Heap;
import com.github.robbmj.googleprep.datastructures.MaxHeap;
import com.github.robbmj.googleprep.datastructures.MinHeap;

public class Main {

	public static void main(String[] args) {
		
		//testMaxHeap();
		testBTree();
	}

	public static double log2(double num) {
		return Math.log(num) / Math.log(2);
	}
	
	public static void divide() {
		//System.out.println(log2(0));
		
		int x = 55;
		int y = 11;
		double log = log2(y);
		int f = (int)log;
		int s = (int)log2((int)((log - f) * 10));
		int foo = f + s;
		//System.out.printf("%d, %d, %f, %d, %d\n", x, y, log, f, s);
		
		int test = x >> f;
		//System.out.println(test + " " + s);
		System.out.println(x >> f);	
	}
	
	static void testBTree() {
		ArrayList<Integer> ints = new ArrayList<>();
		ints.add(7);
		ints.add(3);
		ints.add(12);
		ints.add(1);
		ints.add(6);
		ints.add(9);
		ints.add(13);
		
		BinaryTree<Integer> bTree = new BinaryTree<>(ints);
		
		bTree.print();
	}
	
	public static void testMaxHeap() {
		ArrayList<Integer> ints = new ArrayList<>();
		ints.add(5);
		ints.add(1);
		ints.add(9);
		ints.add(6);
		ints.add(7);
		
		MaxHeap<Integer> heap = new MaxHeap<>(ints);
		heap.print();
		
		System.out.println(heap.remove());
		heap.print();
		
		System.out.println(heap.remove());
		heap.print();
		
		System.out.println(heap.remove());
		heap.print();
		
		System.out.println(heap.remove());
		heap.print();
		
		System.out.println(heap.remove());
		heap.print();
		
	}
	
	public static void testMinHeap() {
		ArrayList<Integer> ints = new ArrayList<>();
		ints.add(5);
		ints.add(1);
		ints.add(9);
		ints.add(6);
		ints.add(7);
		
		Heap<Integer> heap = new MinHeap<>(ints);
		heap.print();
		
		System.out.println(heap.remove());
		heap.print();
		
		System.out.println(heap.remove());
		heap.print();
		
		System.out.println(heap.remove());
		heap.print();
		
		System.out.println(heap.remove());
		heap.print();
		
		System.out.println(heap.remove());
		heap.print();
		
	}
	
	public static void schedual() {
		
		ArrayList<int[]> times = new ArrayList<>(9);
		times.add(new int[] {1,6});
		times.add(new int[] {2,5});
		times.add(new int[] {3,7});
		times.add(new int[] {5,9});
		times.add(new int[] {6,11});
		times.add(new int[] {10,14});
		times.add(new int[] {8,13});
		times.add(new int[] {12,16});
		times.add(new int[] {15,17});
		
		ArrayList<int[]> aceptedSchedual = new ArrayList<>();
		
		while (times.size() > 0) {
			
			int smallestRight = -1;
			int indexOfSmallest = 0;
			
			for (int i = 0; i < times.size(); i++) {
				
				if (smallestRight == -1 || times.get(i)[1] < smallestRight) {
					smallestRight = times.get(i)[1];
					indexOfSmallest = i;
				}
			}
			
			aceptedSchedual.add(times.get(indexOfSmallest));
			
			int acceptedStart = times.get(indexOfSmallest)[0];
			int acceptedFinish = times.get(indexOfSmallest)[1];
			
			times.remove(indexOfSmallest);
			
			for (int i = 0; i < times.size(); i++) {
								
				if (acceptedStart > times.get(i)[0] && acceptedStart < times.get(i)[1]
				 || acceptedFinish < times.get(i)[1] && acceptedFinish > times.get(i)[0] ) {
					
					times.remove(i);
					i--;
				}
				
			}
		}
		printArray(aceptedSchedual);
	}
	
	static void printArray(ArrayList<int[]> a) {
		for (int i = 0; i < a.size(); i++) {
			System.out.println("{" + a.get(i)[0] + ", " + a.get(i)[1] + "}");
		}
	}
	
	
	
}
