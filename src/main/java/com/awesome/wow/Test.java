package com.awesome.wow;

import com.awesome.wow.annotation.AnnotationProcess;
import com.awesome.wow.dto.Person;
import com.google.common.util.concurrent.MoreExecutors;

import javax.print.DocFlavor;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Test {

    private String value = "value";
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        map1.put("lemon", "sour");
        map1.put("lichee", "sweet");
        map1.put("santa", "claus");

        Test test = new Test();
//        test.annotationTest();

        Car car1 = Car.builder().keyA(1).keyB(2).name("car1").build();
        Car car2 = Car.builder().keyA(2).keyB(1).name("car2").build();
        Car car3 = Car.builder().keyA(1).keyB(2).name("car3").build();
        Car car4 = Car.builder().keyA(2).keyB(2).name("car4").build();
        Car car5 = Car.builder().keyA(2).keyB(2).name("car5").build();

        List<Car> carList = Arrays.asList(car1, car2, car3, car4, car5);

//        Wheel wheel1 = new Wheel(1L, "wheel1");
//        Wheel wheel2 = new Wheel(2L, "wheel2");
//        Wheel wheel3 = new Wheel(3L, "wheel3");
//
//        car1.setWheels(Arrays.asList(wheel1));
//        car2.setWheels(Arrays.asList(wheel2, wheel3));

//        Executor executor = Executors.newSingleThreadExecutor();
//        executor.execute(() -> System.out.println("from executor"));

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        Future<String> future = executorService.submit(() -> "from executor service");
//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
//
//        threadPoolExecutor.submit(() -> {
//            System.out.println("inside 1st");
//            Thread.sleep(1000);
//            return null;
//        });
//        threadPoolExecutor.submit(() -> {
//            System.out.println("inside 2nd");
//            Thread.sleep(1000);
//            return null;
//        });
//        threadPoolExecutor.submit(() -> {
//            System.out.println("inside 3rd");
//            Thread.sleep(1000);
//            return null;
//        });


    }

    private int swap(int value) {
        try {
            System.out.println("inside try");
            return value * value;
        } finally {
            System.out.println("inside final");
            if (value == 2) {
                return 0;
            }
        }
    }

    public void lambdaTest() {
        Lambda lambda = param -> param + " from lambda";
        this.foo("santa", lambda);

        Lambda lambda1 = new Lambda() {
            String value = "Inner class value";

            @Override
            public String method(String string) {
                return "anoymous class " + this.value;
            }
        };
        this.foo("santa", lambda1);

        Lambda lambda2 = param -> {
            String value = "Lambda value";
            return this.value;
        };

        this.foo("santa", lambda2);
        Function<String, String> function = param -> param + " from function";

    }

    public void foo(String string, Lambda lambda) {
        System.out.println(lambda.method(string));
    }

    public void annotationTest() {
        Person person = new Person();
        person.setFirstName("first");
        person.setLastName("last");
        person.setAge("11");
        person.setAddress("address");

        AnnotationProcess annotationProcess = new AnnotationProcess();
        String json = annotationProcess.convertToJson(person);
        System.out.println(json);
    }
}
