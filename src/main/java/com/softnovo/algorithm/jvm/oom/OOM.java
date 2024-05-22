package com.softnovo.algorithm.jvm.oom;

import org.openjdk.jol.info.ClassLayout;

public class OOM {
    static final int SIZE = 2 * 1024 * 1024;

    public static void main(String[] a) {
        int[] i = new int[SIZE];

        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());

        System.out.println(ClassLayout.parseInstance(i).toPrintable());
    }
}
