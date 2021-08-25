package com.awesome.wow.generic;

import com.awesome.wow.Car;
import com.awesome.wow.MyCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericClassTest {
    Car car;

    List<Car> carList;

    GenericClass<Car> genericClass = new GenericClass<>();

    @BeforeEach
    void setUp() {
        Car car1 = Car.builder().keyA(1).keyB(2).name("car1").build();
        Car car2 = Car.builder().keyA(2).keyB(1).name("car2").build();
        Car car3 = Car.builder().keyA(1).keyB(2).name("car3").build();
        Car car4 = Car.builder().keyA(2).keyB(2).name("car4").build();
        Car car5 = Car.builder().keyA(2).keyB(2).name("car5").build();

        car = car1;
        carList = Arrays.asList(car1, car2, car3, car4, car5);
    }

    @Test
    void getBrand() {
        Car car = genericClass.getBrand(this.car);
    }

    @Test
    void listBrand() {
        List<Car> carList = genericClass.listBrand(this.carList);
    }
}