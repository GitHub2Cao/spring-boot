package com.softnovo.algorithm.jvm;

public class DemoFalseSharing {
    volatile long a;
    long p1, p2, p3, p4, p5, p6; //对齐填充
    volatile long b;
    long p7, p8, p9, p10, p11, p12, p13; //对齐填充
}
