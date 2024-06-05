package com.softnovo.algorithm.juc;



import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;


public class ReferenceTest {
    public static void main(String[] args) {
        SoftReference<String> wrf = new SoftReference<String>(new String("str"));
        System.out.println(wrf.get());

        WeakReference<SoftReference<String>> weak = new WeakReference(wrf);
        String s = weak.get().get();
        System.out.println(s);
    }


}
