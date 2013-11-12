

package com.github.robbmj.googleprep.tests;

import static com.github.robbmj.googleprep.algorithms.RandomProblems.karpRabin;
import static com.github.robbmj.googleprep.algorithms.RandomProblems.lessThan;
import static com.github.robbmj.googleprep.Util.Assert;
import static com.github.robbmj.googleprep.algorithms.chess.GeneralChess.fastKnight;

import com.github.robbmj.googleprep.datastructures.Point;

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
	
	public static void testFastKnight() {
		int steps;
		
		steps = fastKnight(new Point(0, 0), new Point(1, 2), new Point(2, 1));
		Assert(steps == 3, "Failed testing fastKnight 3 != " + steps);
		
		steps = fastKnight(new Point(0, 0), new Point(1, 7), new Point(2, 4));
		Assert(steps == 4, "Failed testing fastKnight 4 != " + steps);
		
		
		steps = fastKnight(new Point(0, 0), new Point(7, 7), new Point(2, 4));
		Assert(steps == 6, "Failed testing fastKnight 6 != " + steps);
		
		steps = fastKnight(new Point(0, 0), new Point(7, 7), new Point(0, 7));
		Assert(steps == 10, "Failed testing fastKnight 10 != " + steps);
		
		steps = fastKnight(new Point(4, 4), new Point(7, 7), new Point(0, 7));
		Assert(steps == 7, "Failed testing fastKnight 7 != " + steps);
		
		System.out.println("All Fast Knight Tests Passed!");
	}
}
