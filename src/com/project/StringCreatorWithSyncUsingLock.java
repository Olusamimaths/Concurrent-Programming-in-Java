package com.project;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class StringCreatorWithSyncUsingLock {
    public  static MyStringClass myString = new MyStringClass();
    public static void main(String[] args) {
        // Create the executor service
        ExecutorService executor = Executors.newCachedThreadPool();

        // Create 100 task and pass them to the executor to execute
        for(int i = 1; i <= 100; i++) {executor.execute(new AddCharacter());}

        // shutdown when done
        executor.shutdown();

        // wait for the executor to finish
        while(!executor.isTerminated()) {}

        System.out.println("The new string is: " + myString.getString());
        System.out.println("The length of the string is " + myString.getStringLength());

    }

    private static class AddCharacter implements Runnable {
        @Override
        public void run() {
            myString.concatenate('c');
        }
    }

    public static class MyStringClass {
        // create Lock
        private  static Lock myLock = new ReentrantLock();

        private String my_string = "";
        public String getString() {return my_string;}
        public int getStringLength() {return my_string.length();}

        public void  concatenate(char c) {
            // acquire lock
            myLock.lock();
                try {
                    String newString = my_string + c;
                    Thread.sleep(5);
                    my_string = newString;
                } catch (InterruptedException ex) { }
                finally {
                    // release lock
                    myLock.unlock();
                }
            }
    }

}
