//package com.awesome.wow;
//
////import com.vmlens.api.AllInterleavings;
//import org.junit.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CarTest {
//    private final Car car = new Car();
//    private long firstId;
//    private long secondId;
//
//    private void updateFirst() {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        firstId = car.counterIncrement();
//    }
//
//    private void updateSecond() {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        secondId = car.counterIncrement();
//    }
//
//    @Test
//    public void counterIncrement() throws InterruptedException{
////        Thread thread1 = new Thread(this::updateFirst);
////        Thread thread2 = new Thread(this::updateSecond);
////
////        thread1.start();
////        thread2.start();
////
////        thread1.join();
////        thread2.join();
////        System.out.println("first: " + firstId);
////        System.out.println("second: " + secondId);
//        try (AllInterleavings allInterleavings = new AllInterleavings("CarTest")) {
//            while (allInterleavings.hasNext()) {
//                Thread thread1 = new Thread(this::updateFirst);
//                Thread thread2 = new Thread(this::updateSecond);
//
//                thread1.start();
//                thread2.start();
//
//                thread1.join();
//                thread2.join();
//
//                assertTrue(firstId == secondId);
//            }
//        }
//    }
//}