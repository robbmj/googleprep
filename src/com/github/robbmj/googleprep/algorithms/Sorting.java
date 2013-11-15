package com.github.robbmj.googleprep.algorithms;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Sorting {
	
	public static <T extends Comparable<T>> T[] mergeSort(T[] c) {
		
		if (c.length == 1) {
			return c;
		}
		else {
			T[] left = mergeSort(Arrays.copyOfRange(c, 0, c.length / 2));
			T[] right = mergeSort(Arrays.copyOfRange(c, c.length / 2, c.length));
			return merge(left, right);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> T[] merge(T[] left, T[] right) {
		
		int lLen = left.length, 
		    rLen = right.length,
			mLen = 0,
			l = 0,
			r = 0;
		
		T[] merged = (T[])Array.newInstance(left.getClass().getComponentType(), lLen + rLen);
		
		while (l < lLen && r < rLen) {
			
			if (left[l].compareTo(right[r]) <= 0) {
				merged[mLen++] = left[l++];
			}
			else {
				merged[mLen++] = right[r++];
			}
		}
		
		if (l < lLen) {
			System.arraycopy(left, l, merged, mLen, lLen - l);
		}
		else {
			System.arraycopy(right, r, merged, mLen, rLen - r);
		}
		
		return merged;
	}
	
	public static <T extends Comparable<T>> void quickSort(T[] c) {
		
	}
}
