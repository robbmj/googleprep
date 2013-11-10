

package com.github.robbmj.googleprep.tests;

import static com.github.robbmj.googleprep.algorithms.RandomProblems.karpRabin;
import static com.github.robbmj.googleprep.algorithms.RandomProblems.lessThan;
import static com.github.robbmj.googleprep.Util.Assert;

public final class AlgorithmTests {

	public static void testKarpRabin() {
		String s = "Testing karpRabin: ";
		Assert(karpRabin("mikejohn", "oh") == 5, s + "'mikejohn', 'oh' == 5");
		Assert(karpRabin("", "") == 0, s + " '', '' == 0");
		Assert(karpRabin(" ", " ") == 0, s + " ' ', ' ' == 0");
		System.out.println("All karpRabin Tests Passed");
	}
	
	public static void testLessThan() {
		
		Assert(lessThan(1, 2), "Testing 1 < 2");
		Assert(!lessThan(2, 1), "Testing 1 < 2");
		Assert(!lessThan(5, 5), "Testing 5 < 5");
		Assert(lessThan(-5, 5), "Testing 5 < 5");
		Assert(lessThan(-5, 25), "Testing 5 < 5");
		Assert(!lessThan(5, -5), "Testing 5 < 5");
		
		System.out.println("All LessThan Tests Passed");
	}
}
