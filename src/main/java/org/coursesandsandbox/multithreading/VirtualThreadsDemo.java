package org.coursesandsandbox.multithreading;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreadsDemo {
    private static final int NUMBER_OF_THREADS = 1000;

    public static void main(String[] args) throws InterruptedException {
        /**
         * commented out because virtual threads not available yet
         */
//        Runnable runnable = () -> System.out.println("Inside thread: " + Thread.currentThread());
//
//        List<Thread> virtualThreads = new ArrayList<>();
//
//        for(int i = 0; i< NUMBER_OF_THREADS; i++) {
//            Thread virtualThread = Thread.ofVirtual().unstarted(runnable);
//            virtualThreads.add(virtualThread);
//        }
//
//        for(Thread thread : virtualThreads) {
//            thread.start();
//            thread.join();
//        }
    }
}
