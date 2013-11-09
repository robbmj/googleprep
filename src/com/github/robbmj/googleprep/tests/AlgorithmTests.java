

package com.github.robbmj.googleprep.tests;

import static com.github.robbmj.googleprep.algorithms.RandomProblems.karpRabin;
import static com.github.robbmj.googleprep.Util.Assert;

public final class AlgorithmTests {

	public static void testKarpRabin() {
		String s = "Testing karpRabin: ";
		Assert(karpRabin("mikejohn", "oh") == 5, s + "'mikejohn', 'oh' == 5");
		Assert(karpRabin("", "") == 0, s + " '', '' == 0");
		Assert(karpRabin(" ", " ") == 0, s + " ' ', ' ' == 0");
	}
}
