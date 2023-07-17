package org.coursesandsandbox.multithreading;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class SynchronizedBlocks {

    public static void main(String[] args) throws InterruptedException {

        SynchronizedInventoryCounter syncCounter = new SynchronizedInventoryCounter();
        AsynchronizedInventoryCounter asyncCounter = new AsynchronizedInventoryCounter();

        IncrementingThread syncIncrementThread = new IncrementingThread(syncCounter);
        DecrementingThread syncDecrementThread = new DecrementingThread(syncCounter);

        IncrementingThread asyncIncrementThread = new IncrementingThread(asyncCounter);
        DecrementingThread asyncDecrementThread = new DecrementingThread(asyncCounter);

        syncIncrementThread.start();
        syncDecrementThread.start();
        asyncIncrementThread.start();
        asyncDecrementThread.start();
        syncIncrementThread.join();
        syncDecrementThread.join();
        asyncIncrementThread.join();
        asyncDecrementThread.join();

        System.out.println("Current synchronized items count: " + syncCounter.getItems());
        System.out.println("Current not synchronized items count: " + asyncCounter.getItems());
    }
    @AllArgsConstructor
    public static class DecrementingThread extends Thread {
        private Counter counter;

        @Override
        public void run() {
            for(int i = 0; i < 10000; i++) {
                counter.decrement();
            }
        }
    }

    @AllArgsConstructor
    public static class IncrementingThread extends Thread {
        private Counter counter;

        @Override
        public void run() {
            for(int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }

    public interface Counter {
        void increment();
        void decrement();
    }

    private static class AsynchronizedInventoryCounter implements Counter {
        @Getter
        private Integer items = 0;

        public void increment() {
            items++;
        }

        public void decrement() {
            items--;
        }
    }
    private static class SynchronizedInventoryCounter implements Counter{
        private Integer items = 0;
        Object lock = new Object();

        public void increment() {
            synchronized(lock) {
                items++;
            }
        }

        public void decrement() {
            synchronized(lock) {
                items--;
            }
        }

        public Integer getItems() {
            synchronized(lock) {
                return items;
            }
        }
    }
}
