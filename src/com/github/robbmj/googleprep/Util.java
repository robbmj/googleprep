package com.github.robbmj.googleprep;

import java.util.ArrayList;
import java.util.Arrays;

import com.github.robbmj.googleprep.datastructures.Hashmap;
import com.github.robbmj.googleprep.datastructures.Slice;

public final class Util {

	public static void Assert(boolean asertion, String msg) {
		if (!asertion) {
			System.out.println("Assertion failed: " + msg);
			System.exit(1);
		}
	}
	
	
	public static class Compexity {
	
		public enum Order {
			One,
			LogN,
			N,
			NLogN,
			Quadratic,
			Exponential,
			Factorial
		}
		
		private Slice<MilliS> times;
		
		public Compexity() {
			times = new Slice<>(MilliS.class);
		}
		
		public class MilliS {
			long start;
			long end;
			int sizeOfinput;
			
			MilliS(int sizeOfinput) {
				start = System.currentTimeMillis();
				this.sizeOfinput = sizeOfinput;
			}
			
			public long duration() {
				return end - start;
			}
			
			@Override
			public String toString() {
				return "[s:" + sizeOfinput + ", t:" + duration() + "]";
			}
		}
		
		public MilliS startRecording(int sizeOfinput) {
			return new MilliS(sizeOfinput);
		}
		
		public void stopRecording(MilliS m) {
			m.end = System.currentTimeMillis();
			times.add(m);
		}
		
		public Order calcOrder() {
			return Order.One;
		}
		
		@Override
		public String toString() {
			return times.toString();
		}
	}
	
	public static class Prime {
		
		boolean[] primes;
		ArrayList<Integer> justPrimes;
	//	Hashmap<Integer, Integer> primeMap;
		
		public Prime(int upto) {
			
			justPrimes = new ArrayList<>();
			//primeMap = new Hashmap<>();
			
			primes = new boolean[upto];
			
			Arrays.fill(primes, true);
			primes[0] = false;
			primes[1] = false;
			
			for (int i = 2; i < upto; i++) {
				
				if (primes[i]) {
					justPrimes.add(i);
					//primeMap.add(i, justPrimes.size() - 1);
					for (int j = 2; i * j < upto; i++) {
						primes[i * j] = false;
					}
				}
			}
			System.out.println(justPrimes.size());
		}
		
		public boolean isPrime(int number) {
			return primes[number];
		}
		
		public int closestPrime(int number) {
			return findNextLargest(number);
		}
		
		private int findNextLargest(int i) {
			int low = 0,
					high = justPrimes.size(),
					mid = 0,
					comp;
				
			while (low <= high) {
				mid = ((high - low) / 2) + low;
				comp = justPrimes.get(mid).compareTo(i);
			
				if (comp < 0) {
					if (low == mid) break;
					low = mid;
				}
				else if (comp > 0) {
					if (high == mid) break;
					high = mid;
				}
				else {
					break;
				}
			}
			
			return justPrimes.get(mid);
		}
	}
	
	
	public static int gcd(int a, int b) {
        while (b > 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
