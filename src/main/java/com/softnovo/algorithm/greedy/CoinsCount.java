package com.softnovo.algorithm.greedy;

import com.google.common.collect.Lists;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class CoinsCount {
    public static void main(String[] args) {
        int split = split(0,10, Lists.newArrayList(2,1), 0, new LinkedList<>(), true);
        System.out.println(split);
    }

    public static int split(int index, int total, List<Integer> list, int subTotal, LinkedList<Integer> stack, boolean isFirst) {
        if (!isFirst) {
            stack.push(list.get(index));
        }

        if (subTotal == total) {
            System.out.println(Lists.reverse(stack));
            if (!stack.isEmpty()) {
                stack.pop();
            }
            return 1;
        } else if (subTotal > total) {
            System.out.println(Lists.reverse(stack));
            if (!stack.isEmpty()) {
                stack.pop();
            }
            return 0;
        } else {
            int count = 0;
            for (int i = index; i < list.size(); i++) {
                if (subTotal + list.get(i) <= total) {
                    count = count + split(i, total, list, subTotal + list.get(i), stack, false);
                } else {
                    count = count + 0;
                }
            }
            if (!stack.isEmpty()) {
                stack.pop();
            }
            return count;
        }
    }
}
