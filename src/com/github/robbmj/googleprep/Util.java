package com.github.robbmj.googleprep;

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
	
}
