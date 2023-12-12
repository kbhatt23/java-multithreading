package com.learning.utils;

public class TaskUtil {
    public static String task1(){
        ThreadUtils.print("task1 started");

        ThreadUtils.sleepGracefully(2000);

        ThreadUtils.print("task1 completed");
        return "jai shree sita rama lakshman hanuman";
    }

    public static String task2(String input){
        ThreadUtils.print("task2 started");

        ThreadUtils.sleepGracefully(1000);

        ThreadUtils.print("task2 completed");
        return input.toUpperCase();
    }
}
