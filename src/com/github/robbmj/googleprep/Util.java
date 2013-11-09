package com.github.robbmj.googleprep;

public final class Util {

	public static void Assert(boolean asertion, String msg) {
		if (!asertion) {
			System.out.println("Assertion failed: " + msg);
			System.exit(1);
		}
	}
	
}
