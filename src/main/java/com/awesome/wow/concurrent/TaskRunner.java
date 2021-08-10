package com.awesome.wow.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner implements Runnable{
    private String command;

    private AtomicInteger counter;
//    private int counter;

    public TaskRunner() {
        this.counter = new AtomicInteger();
    }

    public int getCounter() {
        return counter.get();
//        return counter;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i =0; i < 3; i++) {
            counter.addAndGet(1);
//            counter++;
        }
    }
}
