package com.awesome.wow;

import com.awesome.wow.annotation.AnnotationProcess;
import com.awesome.wow.dto.Person;
import com.awesome.wow.treesearch.OrderStatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.util.List;
import java.util.function.Function;

public class Test {

    private String value = "value";
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    public static void main(String[] args) throws JsonProcessingException {
        Test test = new Test();

        Car car1 = Car.builder().keyA(1).keyB(2).name("car1").build();
        Car car2 = Car.builder().keyA(2).keyB(1).name("car2").build();
        Car car3 = Car.builder().keyA(1).keyB(2).name("car3").build();
        Car car4 = Car.builder().keyA(2).keyB(2).name("car4").build();
        Car car5 = Car.builder().keyA(2).keyB(2).name("car5").build();
        List<Car> carList = Arrays.asList(car1, car2, car3, car4, car5);


        System.out.println(OrderStatusEnum.getStates().length);


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
