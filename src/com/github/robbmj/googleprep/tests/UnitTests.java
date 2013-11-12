

package com.github.robbmj.googleprep.tests;

import static com.github.robbmj.googleprep.tests.AlgorithmTests.testFastKnight;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testKarpRabin;
import static com.github.robbmj.googleprep.tests.AlgorithmTests.testLessThan;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testBTree;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testGraph;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testHashmap;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testLinkedlist;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testMaxHeap;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testMinHeap;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testQueue;
import static com.github.robbmj.googleprep.tests.DataStructureTests.testStack;

public final class UnitTests {

	public static void runAllTests() {
		
				
		testFastKnight();
		testKarpRabin();
		testLessThan();
		
		System.exit(0);
		
		testGraph();
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
