package com.softnovo.algorithm.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class CAS {
    private static int state = 0;

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<Unsafe> unsafeClass = Unsafe.class;
        //方法一：通过反射构造一个Unsafe对象
        Constructor<Unsafe> constructor = unsafeClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Unsafe unsafe = constructor.newInstance();

        CAS cas = new CAS();

        long stateOffset = unsafe.objectFieldOffset(cas.getClass().getDeclaredField("state"));

        // Object var1, long var2, int var4, int var5);
        System.out.println(cas.state);
        boolean b = unsafe.compareAndSwapInt(cas, stateOffset, 0, 2);
        System.out.println(b);
        System.out.println(cas.state);
    }
}
