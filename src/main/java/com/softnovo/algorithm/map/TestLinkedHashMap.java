package com.softnovo.algorithm.map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class TestLinkedHashMap {
    public static void main(String[] args) {
        Map<Integer, String> lmap = new LinkedHashMap<>(16, 0.75f, true);
        lmap.put(2, "a"); // 2
        lmap.put(5, "b"); // 2->5
        lmap.put(18, "c"); // 2->5->18
        lmap.put(55, "d"); // 2->18->5
        lmap.get(18); // 18->5->2
        lmap.get(5);
        lmap.put(1, "aaa");
        Set<Map.Entry<Integer, String>> entrySet = lmap.entrySet();
        
        for (Map.Entry<Integer, String> entry : entrySet) {
            System.out.println(entry.toString());
        }

        System.out.println("==========");

        Set<Integer> integers = lmap.keySet();
        for (Integer integer : integers) {
            lmap.remove(integer);
            break;
        }
        
        for (Integer integer : integers) {
            System.out.println(integer);
        }



    }
}
