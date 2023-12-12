package com.learning.scopedvalues;

public class NestedThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> "default");
        //ThreadLocal<String> stringThreadLocal = new InheritableThreadLocal<>();

        Thread.ofPlatform().start(() ->{
            System.out.println("pre outerThread: "+stringThreadLocal.get());
            stringThreadLocal.set("sita rama");
            System.out.println("post outerThread: "+stringThreadLocal.get());
            Thread parentThread = Thread.currentThread();
            Thread.ofPlatform().start(()->{
                try {
                    parentThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("pre innerThread: "+stringThreadLocal.get());
                stringThreadLocal.set("inner sita rama");
                System.out.println("post innerThread: "+stringThreadLocal.get());
            });
        });

        System.out.println("finalpost outerThread: "+stringThreadLocal.get());
    }
}
