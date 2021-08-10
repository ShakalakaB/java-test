package com.awesome.wow.concurrent;

import java.util.Date;

public class TaskRunner implements Runnable{
    private String command;

    public TaskRunner(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "TaskRunner{" +
                "command='" + command + '\'' +
                '}';
    }
}
