package com.awesome.wow.generic;

import com.awesome.wow.Car;
import com.awesome.wow.MyCar;
import com.awesome.wow.Vehicle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenericExtendMethod {
    public <T extends Vehicle> T getBrand(T vehicle) {
        System.out.println(vehicle.getBrand());
        return vehicle;
    }

    public <T extends Vehicle> List<T> listBrand(List<T> vehicleList) {
        return vehicleList.stream()
                .peek(vehicle -> System.out.println(vehicle.getBrand()))
                .collect(Collectors.toList());
    }
}
