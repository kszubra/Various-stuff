package org.coursesandsandbox.multithreading;

import java.util.Random;

public class OperationMetrics {


    public static void main(String[] args) {
        Metrics metrics = new Metrics();

        SomeLogic logic1 = new SomeLogic(metrics);
        SomeLogic logic2 = new SomeLogic(metrics);
        Printer printer = new Printer(metrics);

        logic1.start();
        logic2.start();
        printer.start();
    }



    public static class Metrics {
        private long count = 0;
        private volatile double average = 0.0;

        public void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count; //volatile guarantees this line is atomic
        }

        public double getAverage() {
            return average; //volatile guarantees this line is atomic
        }
    }

    public static class Printer extends Thread {
        private Metrics metrics;

        public Printer(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();

            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                double currentAverage = metrics.getAverage();
                System.out.println("Current average is: " + currentAverage);
            }
        }
    }
    public static class SomeLogic extends Thread {
        private Metrics metrics;
        private Random random = new Random();

        public SomeLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();

            while (true) {
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                }

                long end = System.currentTimeMillis();
                metrics.addSample(end - start);
            }
        }
    }
}
