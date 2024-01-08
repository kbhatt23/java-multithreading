package com.learning.basics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedIssue {

    private  static final Object lock = new Object();

    private static  final Lock lockReentrant = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = Thread.ofVirtual().name("sync", 1).factory();
        int count = 10;
        CountDownLatch latch = new CountDownLatch(count);
       // syncDemo(threadFactory,count,latch);
        lockDemo(threadFactory,count,latch);
        latch.await();
    }

    private static void syncDemo(ThreadFactory threadFactory, int count, CountDownLatch latch) {
        for (int i = 0; i < count; i++) {
            int k=i;
            threadFactory.newThread(() -> {
                synchronized (lock){
                    Task.ioIntensiveTask(k);
                    latch.countDown();
                }
            }).start();
        }
    }

    private static void lockDemo(ThreadFactory threadFactory, int count, CountDownLatch latch) {
        for (int i = 0; i < count; i++) {
            int k=i;
            threadFactory.newThread(() -> {
                try{
                    lockReentrant.lock();
                    Task.ioIntensiveTask(k);
                    latch.countDown();
                }finally {
                    lockReentrant.unlock();
                }
            }).start();
        }
    }
}
