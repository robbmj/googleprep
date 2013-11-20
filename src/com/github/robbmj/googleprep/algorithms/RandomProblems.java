

package com.github.robbmj.googleprep.algorithms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.github.robbmj.googleprep.datastructures.Hashmap;

public class RandomProblems {

	public static boolean lessThan(int x, int y) {
		if (x == y) return false;
		return (y - x) >> 31 == 0;
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
	
	public static int karpRabin(String text, String pattern) { // no, no, no
		
		int notFound = -1;
		
		if (pattern.length() > text.length()) {
			return notFound;
		}
				
		int head = pattern.length();
		
		HashMap<String, Integer> map = new HashMap<>();
		
		for (int i = 0; head <= text.length(); i++, head++) {
			map.put(text.substring(i, i + pattern.length()), i);
		}
		
		if (map.containsKey(pattern)) {
			return map.get(pattern);
		}
		return notFound;
	}
	
	public static double log2(double num) {
		return Math.log(num) / Math.log(2);
	}
	
	@SuppressWarnings("unused")
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
	
	public static String[] mediaTable(String[] medals) {
		return null;
	}
	
	// http://community.topcoder.com/stat?c=problem_statement&pm=3080&rd=6518
	public static String latestTime(int[] departure, int[] walking, int[] driving) {
		
		int smallestSoFar = Integer.MAX_VALUE;
						
		for (int i = 0; i < departure.length; i++) {
			
			for (int j = 1; j <= 5; j++) {
				int travelTime = (departure[i] * j) + walking[i] + driving[i];
				
				if (travelTime < smallestSoFar) {
					smallestSoFar = travelTime;
				}
			}
			
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = null;
		
		try {
			d = sdf.parse("19/11/2013 14:30:00");	
		} catch (ParseException e) { }
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, -smallestSoFar);
		sdf = new SimpleDateFormat("hh:mm");
		return sdf.format(cal.getTime()).toString();
	}
	
	// http://community.topcoder.com/stat?c=problem_statement&pm=12816
	public static int count(int[] a, int[] b, int n) {
		
		int c = 0;
		int ta;
		
		Hashmap<Integer, Boolean> map = new Hashmap<>((int)Math.sqrt(n));
		for (int j = 0; j < a.length; j++) {
			
			if (a[j] == b[j] || a[j] > n || b[j] > n) {
				continue;
			}
			
			for (int i = 1; i <= n / 2; i++) {
							
				ta = a[j] * i;
							
				if (ta > n) {
					break;
				}
				
				if (ta % b[j] != 0 && map.get(ta) == null) {
					map.add(ta, true);
					c++;
				}
			}
		}
		
		return c;
	}
	
	public static String path(String wordToType, int width) {

		int numLetters = 26;
		
		char[] keyPad = new char[numLetters];

		int ord = 0;
		
		for (char c = 'a'; c <= 'z'; c++) {
			keyPad[ord++] = c;
			System.out.print(c);
			if (ord % width == 0) {
				System.out.println();
			}
		}
		System.out.println("\n-------------------------------");
		char[] letters = wordToType.toCharArray();
		String path = "";
		
		int currentPos = 0;

		for (int i = 0; i < letters.length; i++) {
			
	       	if (letters[i] != keyPad[currentPos]) {
								
	       		int y = (letters[i] - 'a') / width;
				int x = (letters[i] - 'a') % width;
				
				int curY = currentPos / width;
				int curX = currentPos % width;
				
				int diffX = x - curX;
				int diffY = y - curY;
				
				path += strRepeat(diffX, diffX > 0 ? "r" : "l");
				path += strRepeat(diffY, diffY > 0 ? "d" : "u");
				path += "|";
				
				currentPos = letters[i] - 'a';
			}
		}

		return path;
	}

	public static String strRepeat(int numTimes, String s) {
		String str = "";
		for (int i = 0; i < Math.abs(numTimes); i++) {
			str += s;
		}
		return str;
	}
	
	@SuppressWarnings("unused")
	public static String _path(String wordToType, int width) {

		int numLetters = 26;
		int numRows = numLetters / width; //arg numLetters
		numRows += numLetters % width;

		char[] keyPad = new char[numLetters];

		int ord = 0;
		for (char l = 'a'; l <= 'z'; l++) {
			keyPad[ord++] = l;
		}

		char[] letters = wordToType.toCharArray();
		String path = "";
		
		int currentPos = 0;

		for (int i = 0; i < letters.length; i++) {
	       	if (letters[i] != keyPad[currentPos]) {
				int diff = letters[i] - keyPad[currentPos];
				int y = diff / width;
				int x = diff % width;
			
				int curY = currentPos / width;
				int curX = currentPos % width;
			
				int diffX = x - curX;
				int diffY = y - curY;
			
				path += strRepeat(diffX, diffX > 0 ? "r" : "l");
				path += strRepeat(diffY, diffY > 0 ? "d" : "u");
				path += "|";
			
				currentPos = letters[i];
			}
		}

		return path;
	}

}
