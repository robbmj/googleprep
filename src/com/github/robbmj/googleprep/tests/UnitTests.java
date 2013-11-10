

package com.github.robbmj.googleprep.tests;

import static com.github.robbmj.googleprep.tests.AlgorithmTests.testKarpRabin;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testLessThan;

import static com.github.robbmj.googleprep.tests.DataStructureTests.testBTree;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testHashmap;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testLinkedlist;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testMaxHeap;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testMinHeap;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testQueue;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testStack;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testGraph;

public final class UnitTests {

	public static void runAllTests() {
		testGraph();
		
		System.exit(0);
		
		testKarpRabin();
		testLessThan();
		testQueue();
		testStack();
		testLinkedlist();
		testHashmap();
		testBTree();
		testMaxHeap();
		testMinHeap();
		
		System.out.println("All tests passed");
	}
}
