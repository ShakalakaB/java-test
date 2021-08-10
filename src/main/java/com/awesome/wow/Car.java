package com.awesome.wow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Builder
public class Car implements Vehicle{
    protected String name;

    protected Integer keyA;

    protected Integer keyB;

    protected List<Wheel> wheels = new ArrayList<>();

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public String getBrand() {
        return null;
    }

    @Override
    public String speedUp() {
        return null;
    }

    @Override
    public String slowDown() {
        return null;
    }

    private static void run(String command) {
        System.out.println("car command: " + command);
    }

    public synchronized void increment() {
        this.keyA++;
    }

    public int getCounter() {
        return counter.get();
    }

    public void atomicIncrement() {
        counter.addAndGet(1);
//        while (true) {
//            int existingValue = getCounter();
//            int newValue = existingValue + 1;
//
//            if (counter.compareAndSet(existingValue, newValue)) {
//                counter.set(newValue);
//                return;
//            }
//        }
    }
}
