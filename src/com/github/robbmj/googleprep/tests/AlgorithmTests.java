

package com.github.robbmj.googleprep.tests;

import static com.github.robbmj.googleprep.Util.Assert;
import static com.github.robbmj.googleprep.algorithms.RandomProblems.karpRabin;
import static com.github.robbmj.googleprep.algorithms.RandomProblems.latestTime;
import static com.github.robbmj.googleprep.algorithms.RandomProblems.lessThan;
import static com.github.robbmj.googleprep.algorithms.chess.GeneralChess.attackPositions;
import static com.github.robbmj.googleprep.algorithms.chess.GeneralChess.fastKnight;
import static com.github.robbmj.googleprep.algorithms.RandomProblems.count;

import com.github.robbmj.googleprep.datastructures.Linkedlist;
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
	
	public static void testAttackPositions() {
		
		Linkedlist<String> pos;
		
		// TODO write assertions here
		pos = attackPositions(new Point[] { new Point(2, 1), new Point(-1, -2)}, 10000);
		//System.out.println(pos);
		
		pos = attackPositions(new Point[] { new Point(0, 0)}, 10000);
		//System.out.println(pos);
		
		pos = attackPositions(new Point[] { new Point(0, 0), new Point(2, 1)}, 10000);
		//System.out.println(pos);
		
		pos = attackPositions(new Point[] { new Point(-1000,1000), new Point(-999,999), new Point(-999,997)}, 10000);
		//System.out.println(pos);

	}
	
	public static void testLatestTime() {
		
		String departAt;
		
		int[] depart = new int[] {0,1,2,3,4,5,6,7,8,9};
		int[] walking = new int[] {11,11,11,11,11,11,11,11,11,11};
		int[] driving = new int[] {190,190,190,190,190,190,190,190,190,190};
		
		departAt = latestTime(depart, walking, driving);
		System.out.println(departAt);
		
		depart = new int[] {7,4,0,0,2,1,6,7,7,0,8,6,0,5,0,6,7,9,0,2,4,8,4,7,
			9,2,4,4,3,1,4,5,8,8,2,5,7,8,7,5,6,8,8,0,1,3,5,0,8};
		
		walking = new int[]	{26,14,1,4,16,28,16,6,4,5,21,18,5,2,21,21,28,22,5,22,26,16,14,
			19,19,19,4,12,24,4,30,16,28,20,25,2,30,18,4,6,9,22,8,3,7,29,8,30,6};
			
		driving = new int[]	{151,264,280,89,63,57,15,120,28,296,76,269,90,106,31,222,
			291,52,102,73,140,248,44,187,76,49,296,106,54,119,54,283,263,
			285,275,127,108,82,84,241,169,203,244,256,109,288,9,262,103};
		
		departAt = latestTime(depart, walking, driving);
		System.out.println(departAt);
	
		depart = new int[] {9};
		walking = new int[]	{1};
		driving = new int[]	{1};
		
		departAt = latestTime(depart, walking, driving);
		System.out.println(departAt);
		
		
		depart = new int[] {6};
		walking = new int[]	{9};
		driving = new int[]	{120};
		
		departAt = latestTime(depart, walking, driving);
		System.out.println(departAt);
			
		depart = new int[] {6,9};
		walking = new int[]	{9, 10};
		driving = new int[]	{120, 121};
		
		departAt = latestTime(depart, walking, driving);
		System.out.println(departAt);

	}
	
	public static void testCount() {
		int c;
		c = count(new int[] {4}, new int[] {3}, 20);
		Assert(c == 4, "Failed in testCount() 4 == " + c);
		
		c = count(new int[] {3,2}, new int[] {2,3}, 9);
		Assert(c == 5, "Failed in testCount() 5 == " + c);
		
		c = count(new int[] {5,15,5,15}, new int[] {4,4,2,2}, 50);
		Assert(c == 8, "Failed in testCount() 8 == " + c);
		
		c = count(new int[] {1}, new int[] {1}, 1000);
		Assert(c == 0, "Failed in testCount() 0 == " + c);
		/*System.out.println("---------------------------------------");
		c = count(new int[] {168120222,756,408,194,681,856,964,677,250,845}, 
				  new int[] {809,652,204,532,420,10,640688057,55,174076738,318}, 
				  1000000000);
		
		System.out.println(c);*/
	}
}
