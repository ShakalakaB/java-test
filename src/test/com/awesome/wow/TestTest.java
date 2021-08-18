package com.awesome.wow;

import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;
import com.google.code.tempusfugit.concurrency.IntermittentTestRunner;
import com.google.code.tempusfugit.concurrency.annotations.Intermittent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(ConcurrentTestRunner.class)
public class TestTest {
    private static int testCounter = 0;
    private static int afterCounter = 0;
    private static int afterClassCounter = 0;

    @org.junit.Test
    public void shouldRunInParallel1() {
        System.out.println("I'm running on thread " + Thread.currentThread().getName());
    }

    @org.junit.Test
    public void shouldRunInParallel2() {
        System.out.println("I'm running on thread " + Thread.currentThread().getName());
    }

    @org.junit.Test
    public void shouldRunInParallel3() {
        System.out.println("I'm running on thread " + Thread.currentThread().getName());
    }

    @org.junit.Test
    public void junit4Test() {
        System.out.println("junit 4 test");
        assertEquals(1, 1);
    }

    @After
    public void assertAfterIsCalledRepeatedlyForAnnotatedTests() {
        System.out.println("after method");
        assertEquals(afterCounter, testCounter);
    }

//    @AfterEach
//    void tearDown() {
//        System.out.println("teardown method");
//        assertEquals(afterCounter, testCounter);
//    }

//    @AfterAll
//    public static void assertAfterClassIsCalledOnce() {
//        System.out.println("after all");
//        assertEquals(0, afterClassCounter);
//        assertEquals(99, testCounter);
//    }


}