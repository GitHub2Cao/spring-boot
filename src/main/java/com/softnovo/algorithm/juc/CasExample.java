package com.softnovo.algorithm.juc;

import sun.misc.Unsafe;

public class CasExample {
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset(CasExample.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    private volatile int value = 0;

    public void increment() {
        int current;
        do {
            current = unsafe.getIntVolatile(this, valueOffset);
        } while (!unsafe.compareAndSwapInt(this, valueOffset, current, current + 1));
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        CasExample casExample = new CasExample();
        casExample.increment();
    }
}
