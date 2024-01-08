package com.learning.drawbacks;

import com.learning.basics.Task;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CPUIntensiveReentrantLock {
    private static final List<Integer> data= new ArrayList<>();

    private static final Lock lock = new ReentrantLock();

    static {
        //to trace pinned threads
        //in spring boot app we can pass this and see if anyplace in app pinned thread issue exist
        //System.setProperty("jdk.tracePinnedThreads", "full");
        System.setProperty("jdk.tracePinnedThreads", "short");
    }
    public static void main(String[] args) throws InterruptedException {

        ThreadFactory threadFactory = Thread.ofVirtual().factory();

        int iterations = 10;
        int threads =50;
        syncCPUTask(threadFactory,iterations,threads);

        Thread.ofPlatform().start(() ->{
            System.out.println("text message");
        });

        Thread.sleep(Duration.ofSeconds(20));

        System.out.println("final size: "+data.size());
    }

    private static void syncCPUTask(ThreadFactory threadFactory, int iterations, int threads) {

        for (int i = 0; i < threads; i++) {
            threadFactory.newThread(() -> cpuTask(iterations)).start();
        }

    }

    private static void cpuTask(int iterations) {
        for (int i = 0; i < iterations; i++) {
            addDataToList();
        }

    }

    private static  void  addDataToList() {
        try{
            lock.lock();
            data.add(1);

            //remember this issue occurs only when there is blocking i/o call
            //only cpu task like data.add wont cause any issue
            Task.ioIntensiveTask(0);
        }finally {
            lock.unlock();
        }

    }
}
