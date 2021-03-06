package com.java.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Swarn Singh.
 */
public class MultiThreadingDemo implements Runnable {

    private volatile int count = 0;
    AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) {
        MultiThreadingDemo multiThreadingDemo = new MultiThreadingDemo();

        Thread t1 = new Thread(multiThreadingDemo);
        t1.start();

        Thread t2 = new Thread(multiThreadingDemo);
        t2.start();

        Thread t3 = new Thread(multiThreadingDemo);
        t3.start();

        Thread t4 = new Thread(multiThreadingDemo);
        t4.start();

    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            count++;
            value.getAndIncrement();
        }
        /**
         * Here final count value should be 40,000 but it is not guaranteed even you use volatile variable
         * To fix this issue we can synchronized the count++ block
         *      synchronized (this) {
         *          count++;
         *      }
         *      or we can use AtomicInteger value = new AtomicInteger(1);
         *      value.increment(); // It will ensure each thread do their compound operations(read & write) atomically
         */
        System.out.println("Current Thread : " + Thread.currentThread().getId() + " Count : " + count);
        System.out.println("Current Thread : " + Thread.currentThread().getId() + " Atomic Count : " + value);
    }
}
