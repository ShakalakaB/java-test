package com.awesome.wow;

@FunctionalInterface
public interface Lambda {
    String method(String string);

    default String defaultAdd() {
        return "from defaultAdd: ";
    }
}
