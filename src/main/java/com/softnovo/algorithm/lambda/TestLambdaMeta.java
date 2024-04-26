package com.softnovo.algorithm.lambda;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.util.function.BinaryOperator;

public class TestLambdaMeta {
    public static void main(String[] args) throws Throwable {
        test((a, b) -> a + b);
    }

    static void test(BinaryOperator<Integer> lambda) {
        System.out.println(lambda.apply(1, 2));
    }
}
