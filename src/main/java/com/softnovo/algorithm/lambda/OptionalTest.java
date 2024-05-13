package com.softnovo.algorithm.lambda;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.orElse("empty"));

        Optional<String> hello = Optional.of("Hello");
        System.out.println(hello.orElse("empty"));

        Optional<String> name = Optional.ofNullable("Hello---");
        System.out.println(name.orElse("empty"));

        name.ifPresent(a -> {
            System.out.println(a.toUpperCase() + "TTT");
        });

        System.out.println(name.isPresent());
    }
}
