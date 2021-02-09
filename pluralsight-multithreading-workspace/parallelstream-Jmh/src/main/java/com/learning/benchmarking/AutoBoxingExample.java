package com.learning.benchmarking;

public class AutoBoxingExample {
public static void main(String[] args) {
	autoBoxing();
	manualBoxing();
	
	System.out.println("========================");
	autoBoxing2();
	manualBoxing2();
}

private static void manualBoxing() {
	int i = 23;
	//Integer j = 124;
	Integer j = Integer.valueOf(124);
	
	//int k = i+j;
	int k = i+ j.intValue();
	System.out.println("k value for autobzing is "+k);

}

public static void autoBoxing() {
	int i = 23;
	Integer j = 124;
	
	int k = i+j;
	System.out.println("k value for autobzing is "+k);
}

public static void manualBoxing2() {
	int i = 23;
	Integer j = 124;
	
	Integer k = i+j;
	System.out.println("k value for autobzing is "+k);
}

public static void autoBoxing2() {
	int i = 23;
	//Integer j = 124;
	Integer j = Integer.valueOf(124);
	
	//Integer k = i+j;
	Integer k = Integer.valueOf(i+j.intValue());
	
	//System.out.println("k value for autobzing is "+k);
	System.out.println("k value for autobzing is "+k.intValue());
}
}
