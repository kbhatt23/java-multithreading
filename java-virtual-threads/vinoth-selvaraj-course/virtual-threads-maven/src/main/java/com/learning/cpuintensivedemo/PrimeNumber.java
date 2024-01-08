package com.learning.cpuintensivedemo;

public class PrimeNumber {

    public static int nthPrime(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Index must be non-negative");
        }

        int count = 0;
        int number = 2;

        while (true) {
            if (isPrime(number)) {
                if (count == n) {
                    return number;
                }
                count++;
            }
            number++;
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {


        long start = System.currentTimeMillis();

        int n = 1000000; // Replace with the desired index
        int result = nthPrime(n);
        System.out.println("The " + n + "th prime number is: " + result + ". time taken: " + (System.currentTimeMillis() - start));
    }
    }
}
