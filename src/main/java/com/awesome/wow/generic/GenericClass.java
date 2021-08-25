package com.awesome.wow.generic;

import com.awesome.wow.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class GenericClass<T extends Vehicle> {
    private T t;

    public T getBrand(T vehicle) {
        System.out.println(vehicle.getBrand());
        return vehicle;
    }

    public List<T> listBrand(List<T> vehicleList) {
        return vehicleList.stream()
                .peek(vehicle -> System.out.println(vehicle.getBrand()))
                .collect(Collectors.toList());
    }
}
