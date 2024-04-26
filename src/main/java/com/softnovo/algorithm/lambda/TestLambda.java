package com.softnovo.algorithm.lambda;

import java.lang.reflect.Method;
import java.util.function.BinaryOperator;

public class TestLambda {
    public static void main(String[] args) {
        test((a, b) -> a + b);
        test((a, b) -> a * b);

        for (Method declaredMethod : TestLambda.class.getDeclaredMethods()) {
            System.out.println(declaredMethod);
        }
    }

//    private static Integer lambda$main$1(Integer a, Integer b) {
//        return a + b;
//    }

    static void test(BinaryOperator<Integer> lambda) {
        System.out.println(lambda.apply(1, 2));
    }
}
