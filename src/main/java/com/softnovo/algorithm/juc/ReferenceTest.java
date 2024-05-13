package com.softnovo.algorithm.juc;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class ReferenceTest {
    public static void main(String[] args) {
        SoftReference<String> wrf = new SoftReference<String>(new String("str"));
        System.out.println(wrf.get());

        WeakReference<SoftReference<String>> weak = new WeakReference(wrf);
        String s = weak.get().get();
        System.out.println(s);
    }


}
