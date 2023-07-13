package org.example.multithreading;


public class SimpleWaitNotifyExample {

    public static void main(String[] args) {
        SimpleTask task = new SimpleTask();

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
        SimpleTask task;

        public WorkFinisher(SimpleTask task) {
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

    public static class SimpleTask {
        boolean isCompleted = false;

        //executed in thread 1
        public synchronized void declareSuccess() throws InterruptedException {
            while(!isCompleted) {
                wait();
            }

            System.out.println("Success!");
        }

        //executed in thread 2
        public synchronized void finishWork() {
            isCompleted = true;
            notifyAll();
        }
    }
}
