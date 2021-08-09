package com.awesome.wow.concurrent;

public class TaskRunner {
    private static int number;
    private static boolean ready;

    private static class Reader extends Thread {

        @Override
        public void run() {
//            ready = false;
//            System.out.println("set ready: " + ready);
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("child thread" + ready);
            while (!ready) {
                System.out.println("inside loop");
                Thread.yield();
            }

            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new Reader().start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        number = 42;
        ready = true;
        System.out.println("main thread " + ready);
    }
}
