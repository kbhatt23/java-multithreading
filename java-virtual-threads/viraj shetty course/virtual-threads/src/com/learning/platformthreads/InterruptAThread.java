package com.learning.platformthreads;


public class InterruptAThread {
    public static void main(String[] args) {
       // interruptApproach1();
        interruptApproach2();
    }

    //there is no sleep
    //only cpu work , so after each step of possible next step we should check for interrupted flag
    //then cleanup and let thread die
    private static void interruptApproach2() {
        Thread thread = new Thread(() -> {
            System.out.println("Task started by: " + Thread.currentThread());
            boolean cleanupRequired = false;
            for(int id = 0 ; id< 1000; id++){
                if(Thread.currentThread().isInterrupted()){
                    cleanupRequired = true;
                    break;
                }

                System.out.println("working taskid: "+id);
            }
            if(!cleanupRequired)
             System.out.println("Task completed by: " + Thread.currentThread());
            else
                System.out.println("Thread interrupted, hence doing cleanup");

        }, "approach-2");

        thread.start();

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {

        }

        thread.interrupt();

    }

    //in case worker thread is using sleep or join then automatically in middle Interruptedexception occurs
    //we can clean up in catch block
    private static void interruptApproach1() {
        Thread thread = new Thread(() -> {
            System.out.println("Task started by: " + Thread.currentThread());

            //sleep or join method gets interruptedexcception in middle itself
            try {
                Thread.sleep(4000);
                System.out.println("Task completed by: " + Thread.currentThread());
            } catch (InterruptedException e) {
                //cleanup code
                System.err.println("interruptApproach1 : exception occurred: " + e);
                //resource cleanup code
                System.err.println("interruptApproach1: doing cleanup");
            }
        }, "approach-1");

        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

        thread.interrupt();
    }
}
