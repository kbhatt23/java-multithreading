package com.learning.paralleprogramming.issues;

public class SimpleFactorial {
public static void main(String[] args) {
		int number = 2;
		long factorial =1;
		for(int j=1 ; j <= number; j++) {
			factorial*=j;
		}
		System.out.println(factorial);
}
}
