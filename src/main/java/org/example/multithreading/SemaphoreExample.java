package org.example.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreExample {

    public static void main(String[] args) {
        int threadsNumber = 10;
        Barrier barrier = new Barrier(threadsNumber);
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < threadsNumber; i++) {
            threads.add(new Thread(new CoordinatedRunner(barrier)));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

    public static class Barrier {
        private final int numberOfWorkers;
        private Semaphore semaphore = new Semaphore(0);
        private int counter = 0;
        private Lock lock = new ReentrantLock();

        public Barrier(int numberOfWorkers) {
            this.numberOfWorkers = numberOfWorkers;
        }

        public void barrier() {
            lock.lock();
            boolean isLastWorker = false;

            try {
                counter++;

                if(counter == numberOfWorkers) {
                    isLastWorker = true;
                }
            } finally {
                lock.unlock();
            }

            if(isLastWorker) {
                semaphore.release(numberOfWorkers - 1);
            } else {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {

                }
            }

        }
    }

    public static class CoordinatedRunner implements Runnable {
        private Barrier barrier;

        public CoordinatedRunner(Barrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                task();
            } catch (InterruptedException e) {

            }
        }

        private void task() throws InterruptedException {
            System.out.println(String.format("Thread %s finished part 1", Thread.currentThread().getName()));

            barrier.barrier();

            System.out.println(String.format("Thread %s finished part 2", Thread.currentThread().getName()));
        }
    }
}
