package com.awesome.wow;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Vehicle{
    protected String name;

    protected Integer keyA;

    protected Integer keyB;

    protected List<Wheel> wheels = new ArrayList<>();

    private final AtomicInteger atomicCounter = new AtomicInteger();

    private volatile long counter = 0;

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

    public int getAtomicCounter() {
        return atomicCounter.get();
    }

    public void atomicIncrement() {
        atomicCounter.addAndGet(1);
    }

    public long counterIncrement() {
        return counter++;
    }
}
