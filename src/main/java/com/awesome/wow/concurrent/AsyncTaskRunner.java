package com.awesome.wow.concurrent;

public class AsyncTaskRunner implements Runnable{
    private String command;

    private int counter;

    public AsyncTaskRunner() {
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i =0; i < 3; i++) {
            counter++;
        }
    }
}
