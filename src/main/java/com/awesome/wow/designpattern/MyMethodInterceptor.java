package com.awesome.wow.designpattern;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("intercept before method: " + method.getName());
        Object object = methodProxy.invokeSuper(o, args);
        System.out.println("intercept after method: " + method.getName());
        return object;
    }
}
