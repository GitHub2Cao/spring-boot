package com.softnovo.algorithm.jvm.oom;

import java.util.Arrays;

public class Metaspace {
    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String[] args) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("------>" + t.getName());
            StackTraceElement[] stackTrace = e.getStackTrace();
            Arrays.stream(stackTrace).forEach(traceElement -> {
                System.out.println(traceElement.toString());
            });

        });
        for (int i = 0; ; i++) {
            Class c = cp.makeClass("eu.plumbr.demo.Generated" + i).toClass();
        }
    }
}
