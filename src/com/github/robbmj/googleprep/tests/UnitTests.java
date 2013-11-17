

package com.github.robbmj.googleprep.tests;

import static com.github.robbmj.googleprep.tests.AlgorithmTests.testAttackPositions;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testBucketSort;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testCount;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testCountingSort;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testDeckSorting;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testFastKnight;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testFastKnight2;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testKarpRabin;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testLatestTime;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testLessThan;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testMergeSort;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testQuickSort;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testBTree;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testGraph;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testHashmap;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testLinkedlist;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testMaxHeap;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testMinHeap;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testMinSpanningTree;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testQueue;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testSlice;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testStack;

public final class UnitTests {

	public static void runAllTests() {
		
		testDeckSorting();

		//testCountingSort();
		//testBucketSort();
		System.exit(0);
		
		testDataStructrues();
		testAlgorithums();
		
		System.out.println("All tests passed");
		
	}

	public static void testDataStructrues() {
		testSlice();
		testLinkedlist();
		testHashmap();
		testQueue();
		testStack();
		testBTree();
		testMaxHeap();
		testMinHeap();
		testGraph();
	}
	
	public static void testAlgorithums() {
		testBucketSort();
		testCountingSort();
		testFastKnight2();
		testFastKnight();
		testAttackPositions();
		testMergeSort();
		testQuickSort();
		testLessThan();
		testMinSpanningTree();
		
		System.exit(0);
	
		testKarpRabin();
		testCount();
		testLatestTime();
	}
}
