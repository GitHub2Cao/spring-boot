package com.softnovo.algorithm.jvm.oom;

import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class TestWrapper {
    public static void main(String args[]) throws Exception {
        Properties map = System.getProperties();
        Random r = new Random();
        while (true) {
            map.put(r.nextInt(), "value");
        }
    }
}
