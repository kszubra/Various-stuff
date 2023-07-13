package org.example.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionLockExample {

    public static void main(String[] args) {
        ConditionLockTask task = new ConditionLockTask();

        Thread thread1 = new Thread(() -> {
            try {
                task.declareSuccess();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        WorkFinisher wf = new WorkFinisher(task);

        thread1.start();
        wf.start();
    }


    public static class WorkFinisher extends Thread {
        ConditionLockTask task;

        public WorkFinisher(ConditionLockTask task) {
            this.task = task;
        }

        @Override
        public void run() {
            try {
                System.out.println("Waiting some before releasing task");
                sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            task.finishWork();
        }
    }


    public static class ConditionLockTask {
        boolean isCompleted = false;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void declareSuccess() throws InterruptedException {
            lock.lock();
            try {
                while(!isCompleted) {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }

            System.out.println("SUCCESSSSSSSSSS");
        }

        public void finishWork() {
            lock.lock();
            try {
                isCompleted = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }


    }
}
