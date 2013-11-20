package com.github.robbmj.googleprep.algorithms;

public class Search {

	public static <T extends Comparable<T>> int binarySearch(T[] a, T key) {
		
		if (a == null || a.length == 0 || key == null) {
			return -1;
		}
		
		int low = 0,
			high = a.length,
			mid,
			comp;
		
		while (low <= high) {
			mid = ((high - low) / 2) + low;
			comp = a[mid].compareTo(key);
		
			if (comp < 0) {
				if (low == mid) break;
				low = mid;
			}
			else if (comp > 0) {
				if (high == mid) break;
				high = mid;
			}
			else {
				return mid;
			}
		}
		
		return -1;
	}
}
