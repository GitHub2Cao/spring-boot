package com.softnovo.algorithm.jvm;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        MyObject o = new MyObject();
        System.out.println(o + " 十六进制哈希：" + Integer.toHexString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println(ClassLayout.parseInstance(o).instanceSize());

        System.out.println("======");
        Thread thread = new Thread(() -> {});
        System.out.println(ClassLayout.parseInstance(thread).toPrintable());
        thread.start();
        System.out.println(ClassLayout.parseInstance(thread).instanceSize());

    }
}
