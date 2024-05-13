package com.softnovo.algorithm.lambda;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import org.apache.commons.collections4.map.UnmodifiableMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Stream<int[]> iterate = Stream.iterate(new int[]{1, 1}, x -> new int[]{x[1], x[0] + x[1]}).limit(100);
        iterate.forEach(arr -> System.out.println(Arrays.toString(arr)));
//        List<Integer> collect = iterate.map(x -> x[0]).limit(10000).collect(Collectors.toList());
//        System.out.println(collect);

        ImmutableMap<String, Integer> stringStringImmutableMap1 = ImmutableMap.of("1", 1, "b", 2, "3", 3, "d", 4);
        ImmutableMap<String, Integer> stringStringImmutableMap2 = ImmutableMap.of("1", 1, "2", 2, "3", 3, "4", 4);
        Map<String, Integer> collect = Stream.of(stringStringImmutableMap2, stringStringImmutableMap1).flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (v1, v2) -> v1 + v2));
        System.out.println(collect);


        IntStream.range(1, 10).peek(System.out::println).skip(3).peek(System.out::println).forEach(System.out::println);


    }

    public static <E extends Comparable<? super E>> E max(Collection<E> collection) {
        return null;
    }

}
