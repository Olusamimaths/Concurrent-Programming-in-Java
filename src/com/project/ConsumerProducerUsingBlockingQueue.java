package com.project;

import java.util.concurrent.*;

public class ConsumerProducerUsingBlockingQueue {
    // you have to set a capacity or and a fairness policy
    private static ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(2);

    public static void main(String[] args) {
        // Create a thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Producer());
        executor.execute(new Consumer());

        executor.shutdown();
    }

    // A task for adding an int to the buffer
    private static class Producer implements Runnable {
        public void run() {
            try {
                int i = 1;
                while(true) {
                    System.out.println("Producer writes " + i);
                    buffer.put(i++); // add any value to the buffer, increment after adding
                    Thread.sleep((int)(Math.random() * 1000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // A task for reading and deleting an int from the buffer
    private static class Consumer implements Runnable {
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\tConsumer takes " + buffer.take());
                    Thread.sleep((int)(Math.random() * 10000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
