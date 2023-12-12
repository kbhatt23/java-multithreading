package com.learning.scopedvalues;

public class BasicThreadLocalUse {
    public static void main(String[] args) {

        //share it among multiple threads
        //still each thread will have itws own copy
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        StringHolder stringHolder = new StringHolder(null);

        Thread thread1 = Thread.ofPlatform().start(() -> {
            System.out.println("pre1: " + threadLocal.get());
            System.out.println("pre1: " + stringHolder.getValue());

            stringHolder.setValue("first sita rama");
            threadLocal.set("jai shree sita rama lakshman hanuman");

            System.out.println("post1: " + threadLocal.get());
            System.out.println("post1: " + stringHolder.getValue());
        });

        Thread.ofPlatform().start(()->{
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("pre2: " + threadLocal.get());
            System.out.println("pre2: " + stringHolder.getValue());

            stringHolder.setValue("first radhe krishna");
            threadLocal.set("jai shree radhe krishna");

            System.out.println("post2: " + threadLocal.get());
            System.out.println("post2: " + stringHolder.getValue());
        });
    }
}
