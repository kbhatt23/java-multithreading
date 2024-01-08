package com.learning.basics;

import java.util.concurrent.ThreadFactory;

public class PlatFormThreadScalabilityDemo {

    private static final int THREAD_COUNT = 100;
    public static void main(String[] args) {

        platformThread2();
    }

    private static void platformThread2() {
        ThreadFactory threadFactory = Thread.ofPlatform().name("kbhatt", 1).factory();

        for (int i = 0; i < THREAD_COUNT; i++) {
            int k = i;
            Thread thread = threadFactory.newThread(() -> Task.ioIntensiveTask(k));
            thread.start();

        }
    }
    private static void platformThread1() {

        for (int i = 0; i < THREAD_COUNT; i++) {
            int k = i;
            Thread thread = new Thread(() -> Task.ioIntensiveTask(k));
            thread.start();

        }
    }
}
