package com.awesome.wow.concurrent;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class MyCounterTest {
    @Rule
    public ConcurrentRule concurrentRule = new ConcurrentRule();

    @Rule
    public RepeatingRule repeatingRule = new RepeatingRule();

    private static final MyCounter myCounter = new MyCounter();

    private static final AtomicInteger atomicCounter = new AtomicInteger();


    @Test
    @Concurrent(count = 10)
    @Repeating(repetition = 10)
    public void increment() {
        myCounter.increment();
//        atomicCounter.getAndIncrement();

    }

    @AfterClass
    public static void afterAll() {
        assertEquals(100, myCounter.getCounter());
        System.out.println("mycounter: " + myCounter.getCounter());
//        System.out.println("atomic: " + atomicCounter.get());
    }
}