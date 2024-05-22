package com.softnovo.algorithm.jvm.oom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DumpTest {
    public static void main(String[] args) throws IOException {
        List<Object> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("bdfsjlfjskldfjskldjfklsdjfklsdjfklsdjfklsdjfklsdjfklsdjfklsdjfklsdjfklsdjfklsdjfkldsjfklsdjfklsdjfskldjflskdjflksdjfklsdjf");
        list.add(new String(new char[]{'a', 'b', 'c', 'd', 'e', 'f'}));
        System.out.println(1);
        System.in.read();

        list=null;
        System.out.println(2);
        System.in.read();
        System.out.println("end...");
    }
}
