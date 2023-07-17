package org.coursesandsandbox.multithreading;

import java.math.BigInteger;

public class TerminationAndDaemon {

    public static void main(String[] args) {

        CustomThread thread1 = new CustomThread();
        thread1.setDaemon(true);
        thread1.start();

        Thread thread2 = new Thread(new SleepingThread());
        thread2.start();
        thread2.interrupt();
    }

    public static class CustomThread extends Thread {
        @Override
        public void run() {
            System.out.println("Calculating something huge");
            BigInteger i = BigInteger.valueOf(100000).pow(100000);
            System.out.println("Result: " + i);
        }
    }

    public static class SleepingThread implements Runnable {
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(10000000);
                } catch(InterruptedException e) {
                    System.out.println("Sleeping thread externally aborted");
                    return;
                }
            }
        }
    }
}
