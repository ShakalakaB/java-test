package com.awesome.wow;

import com.vmlens.api.AllInterleavings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private final Car car = new Car();
    private long firstId;
    private long secondId;

    @Test
    void counterIncrement() throws InterruptedException{
        try (AllInterleavings allInterleavings = new AllInterleavings("CarTest")) {
            while (allInterleavings.hasNext()) {
                Thread thread1 = new Thread(() -> {
                    firstId = car.counterIncrement();
                });
                Thread thread2 = new Thread(() -> {
                    secondId = car.counterIncrement();
                });

                thread1.start();
                thread2.start();

                thread1.join();
                thread2.join();

                assertTrue(firstId != secondId);
            }
        }
    }
}