package org.coursesandsandbox.multithreading;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Coordinating {

    public static void main(String[] args) throws InterruptedException {

        List<Integer> ints = Arrays.asList(10, 2, 44, 8, 999999);
        List<PowerThread> threads = new ArrayList<>();

        for(Integer i : ints) {
            threads.add(new PowerThread(i));
        }

        for(Thread thread : threads) {
            thread.setDaemon(true);
            thread.start();
            thread.join(2000); // how long to wait
        }

        for(PowerThread thread : threads) {
            if(thread.isFinished()) {
                System.out.println(String.format("Result for %s ^ %s is: %s",thread.getI(), thread.getI(), thread.getResult()));
            } else {
                System.out.println(String.format("Result for %s ^ %s is not ready yet", thread.getI(), thread.getI()));
            }
        }

        System.out.println("End of main");

    }

    public static class PowerThread extends Thread {
        private int i;
        private boolean isFinished = false;
        BigInteger result = BigInteger.ONE;

        public PowerThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(String.format("Calculating %s^%s", i, i));
            this.result = BigInteger.valueOf(i).pow(i);
            this.isFinished = true;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }
}
