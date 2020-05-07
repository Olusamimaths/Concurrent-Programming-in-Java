package com.project;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ThreadCooperation {
    public static Account account = new Account();
    public static void main(String[] args) {
        // Create a thread pool with two threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());

        System.out.println("Thread 1\t\tThread 2\t\tBalance");
    }

    public static class DepositTask implements Runnable {
        @Override
        public void run() {
            // delay in order to let withdraw task proceed
            try{
                while(true) {
                    account.deposit((int)(Math.random() * 10)+ 1);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static class WithdrawTask implements Runnable {
        @Override
        public void run() {
            while(true) {
                account.withdraw((int)(Math.random()*10) + 1);
            }
        }
    }

    private static class Account {
        // Create a new lock
        private static Lock lock = new ReentrantLock();
        //Create a condition
        private static Condition newDeposit = lock.newCondition();
        private int balance = 0;
        public int getBalance() {
            return balance;
        }
        public void withdraw(int amount) {
            lock.lock(); // Acquire the lock
            try {
                while(balance < amount) {
                    System.out.printf("\t\tWait for deposit");
                    newDeposit.await();
                }
                balance -= amount;
                System.out.println("\t\tWithdraw " + amount + "\t\t" + getBalance());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            finally {
                lock.unlock(); // Release the lock
            }
        }

        public void deposit(int amount) {
            //Before waiting or signalling the condition, a thread must first
            //acquire the lock for the condition
            lock.lock();
            try {
                balance += amount;
                System.out.println("\nDeposit " + amount + "\t\t\t\t" + getBalance());
                // Signal the Thread waiting for condition
                newDeposit.signalAll();
            }
            finally {
                lock.unlock();
            }
        }

    }
}
