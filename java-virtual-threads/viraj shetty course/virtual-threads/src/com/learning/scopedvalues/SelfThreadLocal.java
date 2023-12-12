package com.learning.scopedvalues;

public class SelfThreadLocal {
    //private static final ThreadLocal<StringHolder> threadLocal = new ThreadLocal<>();
    private static final ThreadLocal<StringHolder> threadLocal = new InheritableThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {

        System.out.println("main: pre-> "+threadLocal.get());

        threadLocal.set(new StringHolder("main sita rama"));

        System.out.println("main: post-> "+threadLocal.get());

        Thread.ofPlatform().start(() ->{
            System.out.println("internal: pre-> "+threadLocal.get());

            threadLocal.set(new StringHolder("internal sita rama"));

            System.out.println("internal: post-> "+threadLocal.get());

        }).join();

        System.out.println("main: final-post-> "+threadLocal.get());
    }


}
