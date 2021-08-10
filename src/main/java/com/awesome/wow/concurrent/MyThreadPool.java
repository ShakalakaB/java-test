//package com.awesome.wow.concurrent;
//
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//public class MyThreadPool {
//    private static final int CORE_POOL_SIZE = 5;
//    private static final int MAX_POOL_SIZE = 5;
//    private static final int QUEUE_CAPACITY = 5;
//    private static final long KEEP_ALIVE_TIME = 1L;
//
//    public static void startUp() {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                CORE_POOL_SIZE,
//                MAX_POOL_SIZE,
//                KEEP_ALIVE_TIME,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
////                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.AbortPolicy()
//        );
//
//        for (int i = 0; i < 12; i++) {
//            Runnable worker = new TaskRunner("" + i);
//            threadPoolExecutor.execute(worker);
//        }
//
//        threadPoolExecutor.shutdown();
//        System.out.println("1st shutdown");
//
//        while (!threadPoolExecutor.isTerminated()) {
//            threadPoolExecutor.shutdown();
//            System.out.println("loop shutdown");
//        }
//
//        System.out.println("All threads finished");
//    }
//}
