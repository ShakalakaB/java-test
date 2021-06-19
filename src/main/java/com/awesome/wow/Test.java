package com.awesome.wow;

import java.util.*;
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
        List<Car> list = new ArrayList<>();
        Car car1 = new Car();
        Car car2 = new Car();

        Wheel wheel1 = new Wheel(1L, "wheel1");
        Wheel wheel2 = new Wheel(2L, "wheel2");
        Wheel wheel3 = new Wheel(3L, "wheel3");

        car1.setWheels(Arrays.asList(wheel1));
        car2.setWheels(Arrays.asList(wheel2, wheel3));

        list.add(car1);
        list.add(car2);

        List<Wheel> wheels = list.stream()
                .flatMap(car -> car.getWheels().stream())
                .collect(Collectors.toList());

        wheels.stream()
                .filter(wheel -> wheel.getId() == 1L)
                .forEach(wheel -> wheel.setName("after " + wheel.getName()));

        System.out.println("santa");

//        System.out.println(map1.put("claus", "uno"));


//        Stream<String[]> str = Stream.of(new String[][]{
//                {"GFG", "GeeksForGeeks"},
//                {"g", "geeks"},
//                {"GFG", "geeksforgeeks"}
//        });
//
//        Map<String, String> map = str.collect(Collectors.toMap(
//                p -> p[0], p -> p[1], (s, a) -> s + ", " + a
//        ));

//        Map<String, String> map = str.collect(Collectors.toMap(
//                p -> p[0], p -> p[1]
//        ));

//        System.out.println("Map:" + map);

//        List<String> stringList = Arrays.asList("lemon", "santa", "Yada");
//
//        Optional<List<String>> result = Optional.ofNullable(stringList).filter(strings -> {
//            System.out.println(strings);
//            return true;
//        });
//
//        System.out.println("result: " + result.get());
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
