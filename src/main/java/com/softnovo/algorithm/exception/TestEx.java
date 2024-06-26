package com.softnovo.algorithm.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.util.Arrays;

public class TestEx {
    private void fun1() throws IOException {
        throw new IOException("level 1 exception");
    }
    private void fun2() throws IOException {
        try {
            fun1();
            System.out.println("2");
        } catch (IOException e) {
            throw new IOException("level 2 exception", e);
        }
    }
    private void fun3() {
        try {
            fun2();
            System.out.println("3");
        } catch (IOException e) {
            throw new RuntimeException("level 3 exception", e);
        }
    }
    public static void main(String[] args) {
        try {
            new TestEx().fun3();
            System.out.println("0");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("=========");

            Arrays.stream(ExceptionUtils.getStackFrames(e)).forEach(System.out::println);
        }
    }
}
