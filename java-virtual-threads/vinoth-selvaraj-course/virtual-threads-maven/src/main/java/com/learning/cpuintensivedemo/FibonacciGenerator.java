package com.learning.cpuintensivedemo;

public class FibonacciGenerator {

    public static long findFibonnaci(int index){
        if (index < 0)
            throw new IllegalArgumentException("Index must be non-negative");

        if(index == 0 || index == 1)
            return  index;

        long previousToPrevious = 0;
        long previous = 1;
        long current = 0;
        for(int i = 2 ; i <= index ; i++){
            current = previous + previousToPrevious;
            previousToPrevious = previous;
            previous=current;
        }

        return current;
    }

    public static long findFibonnaciRecursion(int index){
        if (index < 0)
            throw new IllegalArgumentException("Index must be non-negative");

        if(index == 0 || index == 1)
            return  index;

        return findFibonnaciRecursion(index - 1) + findFibonnaciRecursion(index - 2);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
       // for(int index = 0 ; index < 10 ; index++){
           // System.out.println(findFibonnaci(index));
           // System.out.println(findFibonnaciRecursion(index));
       // }
       //System.out.println(findFibonnaci(1001));

        System.out.println(findFibonnaciRecursion(45));
        System.out.println("total time taken: "+(System.currentTimeMillis()-start));
    }
}
