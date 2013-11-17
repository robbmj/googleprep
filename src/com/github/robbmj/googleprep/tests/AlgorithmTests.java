

package com.github.robbmj.googleprep.tests;

import static com.github.robbmj.googleprep.Util.Assert;
import static com.github.robbmj.googleprep.algorithms.RandomProblems.count;
import static com.github.robbmj.googleprep.algorithms.RandomProblems.karpRabin;
import static com.github.robbmj.googleprep.algorithms.RandomProblems.latestTime;
import static com.github.robbmj.googleprep.algorithms.RandomProblems.lessThan;
import static com.github.robbmj.googleprep.algorithms.Sorting.mergeSort;
import static com.github.robbmj.googleprep.algorithms.Sorting.quickSort;
import static com.github.robbmj.googleprep.algorithms.chess.GeneralChess.attackPositions;
import static com.github.robbmj.googleprep.algorithms.chess.GeneralChess.fastKnight;
import static com.github.robbmj.googleprep.algorithms.chess.GeneralChess.fastKnight2;

import java.util.Arrays;

import com.github.robbmj.googleprep.Util.Compexity;
import com.github.robbmj.googleprep.Util.Compexity.MilliS;
import com.github.robbmj.googleprep.algorithms.PlayingCardDeck;
import com.github.robbmj.googleprep.algorithms.PlayingCardDeck.PlayingCard;
import com.github.robbmj.googleprep.algorithms.PlayingCardDeck.Rank;
import com.github.robbmj.googleprep.algorithms.PlayingCardDeck.Suit;
import com.github.robbmj.googleprep.algorithms.chess.IChessPeice.Team;
import com.github.robbmj.googleprep.algorithms.chess.Knight;
import com.github.robbmj.googleprep.algorithms.chess.Pawn;
import com.github.robbmj.googleprep.datastructures.Linkedlist;
import com.github.robbmj.googleprep.datastructures.Point;
import com.github.robbmj.googleprep.datastructures.Slice;
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

		pos = attackPositions(new Point[] { new Point(2, 1), new Point(-1, -2)}, 10000);
		Assert(pos.size() == 2, "Assert failed attackPositions() 2 == " + pos.size());
		
		pos = attackPositions(new Point[] { new Point(0, 0)}, 10000);
		Assert(pos.size() == 8, "Assert failed attackPositions() 8 == " + pos.size());
		
		pos = attackPositions(new Point[] { new Point(0, 0), new Point(2, 1)}, 10000);
		Assert(pos.size() == 0, "Assert failed attackPositions() 0 == " + pos.size());
		
		pos = attackPositions(new Point[] { new Point(-1000,1000), new Point(-999,999), new Point(-999,997)}, 10000);
		Assert(pos.size() == 1, "Assert failed attackPositions() 1 == " + pos.size());
		
		System.out.println("All Attack Position Tests passed");
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
	
	public static void testMergeSort(int size, Compexity c) {
	
		Point[] points = new Point[size];
		
		for (int i = 0; i < size; i++) {
			points[i] = new Point((int)(Math.random() * 200), (int)(Math.random() * 200));
		}
		
		MilliS m = c.startRecording(size);
		points = mergeSort(points);
		c.stopRecording(m);
		
		Point prev = points[0];
		
		for (int i = 1; i < size; i++) {
			Assert(prev.compareTo(points[i]) <= 0, "Merge sort failed");
			prev = points[i];
		}
	}
	
	public static void testMergeSort() {
		Compexity c = new Compexity();
		
		for (int i = 0; i < 16; i++) {
			testMergeSort((int)Math.pow(2, i), c);
		}
		
		//System.out.println(c);
		
		System.out.println("All merge sort tests passed");
	}
	
	
	public static void testQuickSort(int size, Compexity c) {
		
		Slice<Integer> ints = new Slice<>(Integer.class);
				
		while (ints.size() < size) {
			int candidate = (int)(Math.random() * 100);
			ints.add(candidate);		
		}
		
		MilliS m = c.startRecording(size);
		quickSort(ints);
		c.stopRecording(m);
		
		int prev = Integer.MIN_VALUE;
		
		for (int i = 0; i < size; i++) {
			Assert(ints.get(i) >= prev, "Quick sort failed to sort");
			prev = ints.get(i);
		}
	}
	
	public static void testQuickSort() {
	
		Compexity c = new Compexity();
		
		for (int i = 0; i < 16; i++) {
			testQuickSort((int)Math.pow(2, i), c);
		}
		
		//System.out.println(c);
		
		System.out.println("All quick sort tests passed");
	}
	
	public static void testCountingSort() {
		PlayingCardDeck deck = new PlayingCardDeck();
		deck.shffleDeck();
		deck.sortByRank();
		
		int t = 0, 
			numRanks = PlayingCardDeck.CARDS_IN_DECK / Rank.values().length;
		
		for (int i = 0; i < PlayingCardDeck.CARDS_IN_DECK; t++) {
			for (int j = 0; j < numRanks; j++, i++) {
				Assert(t == deck.cardAt(i).getRank().ordinal(), "counting sort failed");
			}
		}
		
		System.out.println("All counting sort tests passed");
	}

	public static void testBucketSort() {
		PlayingCardDeck deck = new PlayingCardDeck();
		deck.shffleDeck();
		deck.sortBySuit();
		int t = 0, 
			numSuits = PlayingCardDeck.CARDS_IN_DECK / Suit.values().length;
		
		for (int i = 0; i < PlayingCardDeck.CARDS_IN_DECK; t++) {
			for (int j = 0; j < numSuits; j++, i++) {
				Assert(t == deck.cardAt(i).getSuit().ordinal(), "bucket sort failed");
			}
		}
		
		System.out.println("All bucket sort tests passed");
	}
	
	public static void testDeckSorting() {
		PlayingCardDeck deck = new PlayingCardDeck();
		deck.shffleDeck();
		deck.sort();
		PlayingCardDeck comparator = new PlayingCardDeck();
		
		for (int i = 0; i < PlayingCardDeck.CARDS_IN_DECK; i++) {
			
			Assert(deck.cardAt(i).equals(comparator.cardAt(i)),
					"deck.sort() failed");
		}
		System.out.println("All bucket / counting sort tests passed");
	}
	
	public static void testFastKnight2() {
		int steps;
		steps = fastKnight2(new Knight(new Point(0, 0), Team.BLACK),
				new Pawn(new Point(0, 1), Team.WHITE),
				new Pawn(new Point(7, 7), Team.WHITE),
				new Pawn(new Point(2, 4), Team.WHITE));
		
		steps = fastKnight2(new Knight(new Point(0, 2), Team.BLACK),
				new Pawn(new Point(0, 1), Team.WHITE),
				new Pawn(new Point(7, 7), Team.WHITE),
				new Pawn(new Point(2, 4), Team.WHITE),
				new Pawn(new Point(3, 4), Team.WHITE),
				new Pawn(new Point(4, 3), Team.WHITE),
				new Pawn(new Point(0, 7), Team.WHITE));
		
		
		System.out.println("All Fast Knight 2 tests passed");
	}
	
	
}
