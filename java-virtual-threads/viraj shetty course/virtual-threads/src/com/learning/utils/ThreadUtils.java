package com.learning.utils;

public class ThreadUtils {

    public static void sleepGracefully(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {

        }
    }

    public static  void print(String message){
        System.out.println(Thread.currentThread() + " working on:->"+message);
    }
}
