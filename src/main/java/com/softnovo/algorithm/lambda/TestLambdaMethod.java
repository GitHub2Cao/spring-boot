package com.softnovo.algorithm.lambda;

import java.util.function.Function;

public class TestLambdaMethod {
    public static void main(String[] args) {
        test(String::toLowerCase);
    }

    static void test(Function<String,String> lambda) {
        System.out.println(lambda.apply("Tom"));
    }
}


