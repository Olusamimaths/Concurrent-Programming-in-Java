package com.project;

import java.util.concurrent.*;


public class ThreadPool {
    public static  void main(String[] args) {
        // Using the newFixedThreadPool
        // Create a thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // create tasks
        Runnable Task1 = new PrintChar('a', 200);
        Runnable Task2 = new PrintChar('b', 200);
        Runnable Task3 = new PrintChar('d', 200);

        // Submit the runnable tasks to the executor
        executor.execute(Task1);
        executor.execute(Task2);
        executor.execute(Task3);

        // shutdown the executor (no other task can be accepted when the executor has been shut down)
        executor.shutdown();
    }
}

