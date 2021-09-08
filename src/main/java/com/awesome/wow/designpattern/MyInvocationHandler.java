package com.awesome.wow.designpattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private final Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke before method: " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("invoke after method " + method.getName());

        return result;
    }
}
