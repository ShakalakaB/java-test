package com.awesome.wow.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AnnotationProcess {
    private void checkIfSerializable(Object object) {
        if (object == null) {
            throw new RuntimeException("The object to serialize is null");
        }

        Class<?> clazz = object.getClass();

        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new RuntimeException("The class " + clazz.getSimpleName() + " is not annotated with JsonSerializable");
        }

    }

    private void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    private String getJsonString(Object object) throws Exception {
        Class<?> clazz = object.getClass();

        Map<String, String> jsonElementMap = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                jsonElementMap.put(getKey(field), (String) field.get(object));
            }
        }

        String jsonString = jsonElementMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));

        return "{" + jsonString + "}";
    }

    public String convertToJson(Object object) throws RuntimeException {
        try {
            checkIfSerializable(object);
            initializeObject(object);
            return getJsonString(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getKey(Field field) {
        String value = field.getAnnotation(JsonElement.class).key();
        return value.isEmpty() ? field.getName() : value;
    }
}
