package com.softnovo.algorithm.jvm;

import org.openjdk.jol.info.ClassLayout;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new Object[]{new Object(), new Object()}).toPrintable());
        System.out.println("======");

        System.out.println(
                ClassLayout.parseInstance(new Object()).toPrintable());

        System.out.println("----------------------");

        System.out.println(
                ClassLayout.parseInstance(Date.class).toPrintable());

        System.out.println("======================");
        System.out.println(
                ClassLayout.parseInstance(java.sql.Date.class).toPrintable());
    }
}
