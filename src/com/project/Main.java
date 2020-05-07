package com.project;

public class Main {

    public static void main(String[] args) {
        // create the tasks
	 Runnable printA = new PrintChar('a', 1000);
	 Runnable printB = new PrintChar('b', 1000);
	 Runnable print100 = new PrintNum(1000);

	 //Create threads
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

// the task for printing  a character a specified number of times
class PrintChar implements Runnable {
    private char charToPrint;
    private  int times;

    // constructor
    public PrintChar(char c, int t) {
        charToPrint = c;
        times = t;
    }

    @Override // overide the run() method to tell the system what task to perform
    public  void run() {
        for(int i = 0; i < times; i++) {
            System.out.print(charToPrint);
        }
    }
}

class PrintNum implements Runnable {
    private int lastNum;

    public PrintNum(int n) {
        lastNum = n;
    }

    @Override // Tell the thread how to run
    public void run() {
        for(int i = 1; i <= lastNum; i++) {
            System.out.print(" " + i);
        }
    }
}
