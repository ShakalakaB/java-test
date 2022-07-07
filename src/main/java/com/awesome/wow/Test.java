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
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    public static void main(String[] args) throws UnknownHostException {
        Test test = new Test();

        Car car1 = Car.builder().keyA(1).keyB(2).name("car2").build();
        Car car2 = Car.builder().keyA(2).keyB(1).name("car1").build();
        Car car3 = Car.builder().keyA(1).keyB(3).name("car2").build();
        Car car4 = Car.builder().keyA(2).keyB(2).name("car2").build();
        Car car5 = Car.builder().keyA(2).keyB(3).name("car2").build();
        List<Car> carList = Arrays.asList(car1, car2, car3, car4, car5);

        String json = "{\"name\":\"car1\",\"keyA\":1,\"keyB\":2,\"wheels\":null,\"atomicCounter\":0,\"counter\":0,\"brand\":\"car brand\"}\n";
        String json2 = "{\"name\":\"car2\",\"keyA\":2,\"keyB\":2,\"wheels\":null,\"atomicCounter\":0,\"counter\":0,\"brand\":\"car brand\"}\n";
        String json3 = "{\"name\":\"car3\",\"keyA\":3,\"keyB\":3,\"wheels\":null,\"atomicCounter\":0,\"counter\":0,\"brand\":\"car brand\"}\n";

        ThreadPoolExecutor executor2 = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        Stopwatch stopwatch2 = Stopwatch.createStarted();
        List<CompletableFuture<Car>> futureList2 = new ArrayList<>();
        for (int i =0; i < 9; i++) {
            CompletableFuture<Car> future = CompletableFuture.supplyAsync(() -> {
                Car car = null;
                try {
                    car = new ObjectMapper().readValue(json2, new TypeReference<Car>() {});
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return car;
            }, executor2).exceptionally(e -> {throw new RuntimeException(e.getMessage());});
            futureList2.add(future);
        }

        CompletableFuture.allOf(futureList2.toArray(new CompletableFuture[]{})).join();
        System.out.println("finish2 " + stopwatch2.toString());

        ThreadPoolExecutor executor3 = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        Stopwatch stopwatch3 = Stopwatch.createStarted();
        List<CompletableFuture<Car>> futureList3 = new ArrayList<>();
        for (int i =0; i < 9; i++) {
            CompletableFuture<Car> future = CompletableFuture.supplyAsync(() -> {

                Car car = null;
                try {
                    car = JsonUtil.getObjectReader(new TypeReference<Car>() {}).readValue(json3);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return car;
            }, executor3).exceptionally(e -> {throw new RuntimeException(e.getMessage());});
            futureList3.add(future);
        }

        CompletableFuture.allOf(futureList3.toArray(new CompletableFuture[]{})).join();
        System.out.println("finish3 " + stopwatch3.toString());

        ThreadPoolExecutor executor1 = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<CompletableFuture<Car>> futureList = new ArrayList<>();
        for (int i =0; i < 9; i++) {
            CompletableFuture<Car> future = CompletableFuture.supplyAsync(() -> {
                return JsonUtil.toObject(json, new TypeReference<Car>() {});
            }, executor1);
            futureList.add(future);
        }

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        System.out.println("finish1 " + stopwatch.toString());
    }

}
