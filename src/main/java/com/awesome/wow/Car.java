package com.awesome.wow;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Car implements Vehicle{
    protected String name;

    protected Integer keyA;

    protected Integer keyB;

    protected List<Wheel> wheels = new ArrayList<>();

    @Override
    public String getBrand() {
        return null;
    }

    @Override
    public String speedUp() {
        return null;
    }

    @Override
    public String slowDown() {
        return null;
    }

    private static void run(String command) {
        System.out.println("car command: " + command);
    }
}
