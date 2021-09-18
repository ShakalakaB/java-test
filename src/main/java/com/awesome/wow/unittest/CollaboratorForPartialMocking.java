package com.awesome.wow.unittest;

class CollaboratorForPartialMocking {

    static String staticMethod() {
        return "static method here!";
    }

    final String finalMethod() {
        return "final method here";
    }

    private String privateMethod() {
        return "private method";
//        System.out.println("private method");
    }

    String privateMethodCaller() {
        return privateMethod() + " is being called";
//        return "privateMethod()  is being called";
    }
}