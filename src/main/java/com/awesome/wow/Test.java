package com.awesome.wow;


import com.awesome.wow.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Stopwatch;

import java.net.UnknownHostException;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

    public static void main(String[] args) throws UnknownHostException {
        Test test = new Test();

        Car car1 = Car.builder().keyA(1).keyB(2).name("car2").build();
        Car car2 = Car.builder().keyA(2).keyB(1).name("car1").build();
        Car car3 = Car.builder().keyA(1).keyB(3).name("car2").build();
        Car car4 = Car.builder().keyA(2).keyB(2).name("car2").build();
        Car car5 = Car.builder().keyA(2).keyB(3).name("car2").build();
        List<Car> carList = Arrays.asList(car1, car2, car3, car4, car5);

        String json = "{\"name\":\"car2\",\"keyA\":1,\"keyB\":2,\"wheels\":null,\"atomicCounter\":0,\"counter\":0,\"brand\":\"car brand\"}\n";

        Stopwatch stopwatch = Stopwatch.createStarted();
        List<CompletableFuture<Car>> futureList = new ArrayList<>();
        for (int i =0; i < 9; i++) {
            CompletableFuture<Car> future = CompletableFuture.supplyAsync(() -> {
                return JsonUtil.toObject(json, new TypeReference<Car>() {});
            });
            futureList.add(future);
        }

//        Car car = JsonUtil.toObject(json, new TypeReference<Car>() {});
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        System.out.println("finish " + stopwatch.toString());

        Stopwatch stopwatch1 = Stopwatch.createStarted();
        List<CompletableFuture<Car>> futureList2 = new ArrayList<>();
        for (int i =0; i < 9; i++) {
            CompletableFuture<Car> future = CompletableFuture.supplyAsync(() -> {
                Car car = null;
                try {
                    car = new ObjectMapper().readValue(json, new TypeReference<Car>() {});
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return car;
            }).exceptionally(e -> {throw new RuntimeException(e.getMessage());});
            futureList2.add(future);
        }

//        Car car = JsonUtil.toObject(json, new TypeReference<Car>() {});
        CompletableFuture.allOf(futureList2.toArray(new CompletableFuture[]{})).join();
        System.out.println("finish " + stopwatch1.toString());


    }
}
