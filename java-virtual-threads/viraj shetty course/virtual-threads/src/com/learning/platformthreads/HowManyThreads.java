package com.learning.platformthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class HowManyThreads {
    public static void main(String[] args) {
        System.out.println("main started");

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            threads.add(new Thread(HowManyThreads::handle));
        }

        threads.forEach(Thread::start);

        System.out.println("main ended");
    }

    private static void handle() {
        System.out.println("handle started for thread: " + Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("handle ended for thread: " + Thread.currentThread().getName());
    }
}