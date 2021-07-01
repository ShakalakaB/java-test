package com.awesome.wow;

import org.json.JSONObject;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Test {

    private String value = "Enclosing scope value";

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        map1.put("lemon", "sour");
        map1.put("lichee", "sweet");
        map1.put("santa", "claus");
        
        Test test = new Test();
//        test.lambdaTest();

        Car car1 = Car.builder().keyA(1).keyB(2).name("car1").build();
        Car car2 = Car.builder().keyA(2).keyB(1).name("car2").build();
        Car car3 = Car.builder().keyA(1).keyB(2).name("car3").build();
        Car car4 = Car.builder().keyA(2).keyB(2).name("car4").build();
        Car car5 = Car.builder().keyA(2).keyB(2).name("car5").build();

//        Wheel wheel1 = new Wheel(1L, "wheel1");
//        Wheel wheel2 = new Wheel(2L, "wheel2");
//        Wheel wheel3 = new Wheel(3L, "wheel3");
//
//        car1.setWheels(Arrays.asList(wheel1));
//        car2.setWheels(Arrays.asList(wheel2, wheel3));

//        List<Car> list = Arrays.asList(car1,car2, car3, car4, car5);
        List<Car> list = new ArrayList<>();

//        Map<Integer, List<Car>> map = list.stream()
//                .collect(Collectors.groupingBy(Car::getKeyA));
//
//        JSONObject jsonObject = new JSONObject(map);
//
//        System.out.println(jsonObject);

//        Map<Tuple, List<Car>> map2 = list.stream()
//                .collect(Collectors.groupingBy(car -> new Tuple(car.getKeyA(), car.getKeyB())));
//
//        JSONObject json = new JSONObject(map2);
//        System.out.println(json);

        Map<Integer, Map<Integer,List<Car>>> map3 = list.stream()
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(Car::getKeyA), result -> {
                    Map<Integer, Map<Integer, List<Car>>> nestedMap = new HashMap<>();

                    result.entrySet().stream()
                            .forEach(e -> {
                                nestedMap.put(e.getKey(), e.getValue().stream()
                                        .collect(Collectors.groupingBy(Car::getKeyB)));
                            });

                    return nestedMap;
                }));

//        List<Wheel> wheels = list.stream()
//                .flatMap(car -> car.getWheels().stream())
//                .collect(Collectors.toList());

//        wheels.stream()
//                .filter(wheel -> wheel.getId() == 1L)
//                .forEach(wheel -> wheel.setName("after " + wheel.getName()));

        System.out.println(map3.get("santa"));

//        System.out.println(map1.put("claus", "uno"));

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

    public void nullableTest(String a, String b) {
        System.out.println("a " + a + " b: " + b);
    }
}
