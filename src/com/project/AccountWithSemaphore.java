package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class AccountWithSemaphore {
    public static Account account = new Account();

    public static void main(String[] args) {
        // create an executor with a thread pool
        ExecutorService executor = Executors.newCachedThreadPool();

        // Create 100 threads and execute them
        for(int i = 1; i <= 100; i++) executor.execute(new AddPennyTask());

        // shutdown the executor
        executor.shutdown();

        // wait for the tasks to complete
        while(!executor.isTerminated()) {
            // do nothing
            // this loop is 'skipped' only when executor.isTerminated() returns true
        }
        System.out.println("What is the balance? " + account.getBalance());
    }
    // Define task for adding a penny to the account
    private static class AddPennyTask implements Runnable {
        @Override // Override the run class
        public void run() {
            account.deposit(1);
        }
    }

    // inner Class for Account
    private static  class  Account {
        private static Semaphore semaphore = new Semaphore(1); // Create a Semaphore
        private int balance = 0;

        // getter
        public int getBalance() {
            return balance;
        }
        public void deposit(int amount) {
            // create a delay to make data-corruption more obvious
            try {
                semaphore.acquire();
                int newBalance = balance + amount;

                Thread.sleep(5);
                balance = newBalance;
            } catch (InterruptedException ex) {}
            finally {
                semaphore.release();
            }

        }
    }


}
