package com.learning.benchmarking;

public class WrapperObjects {
	public static void main(String[] args) {
		Integer a =128;
		Integer b=128;
		System.out.println("are values eqaul "+a.equals(b));
		//we thought both obects were different
		//as they are imutable just like string they are shared only if values are 127 or less and -128 or more
		
		System.out.println("are objects same "+(a == b));
	}
}
