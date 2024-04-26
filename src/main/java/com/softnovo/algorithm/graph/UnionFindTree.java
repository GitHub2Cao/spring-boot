package com.softnovo.algorithm.graph;

import java.util.Arrays;

public class UnionFindTree {
    private final int[] eleAndGroup;

    private int count;

    public UnionFindTree(int size) {
        this.count = size;
        this.eleAndGroup = new int[size];
        for (int i = 0; i < this.count; i++) {
            eleAndGroup[i] = i;
        }
    }

    public int groupCount() {
        return this.count;
    }

    /**
     * 同一个父节点.
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    public int find(int p) {
        int temp = p;
        while (true) {
            if (temp == eleAndGroup[temp]) {
                return temp;
            }

            temp = eleAndGroup[temp];
        }
    }

    public void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }
        int pRoot = find(p);
        int qRoot = find(q);

        eleAndGroup[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        UnionFindTree unionFindTree = new UnionFindTree(10);
        System.out.println(unionFindTree);

        unionFindTree.union(1, 2);
        System.out.println(unionFindTree);
        unionFindTree.union(2, 3);
        System.out.println(unionFindTree);

        unionFindTree.union(1, 4);
        System.out.println(unionFindTree);

        unionFindTree.union(0, 1);
        System.out.println(unionFindTree);

        unionFindTree.union(5, 4);
        System.out.println(unionFindTree);

        unionFindTree.union(6, 0);
        System.out.println(unionFindTree);

        unionFindTree.union(7, 6);
        System.out.println(unionFindTree);

        unionFindTree.union(4, 8);
        System.out.println(unionFindTree);



    }

    @Override
    public String toString() {
        return "UnionFindTree{" +
                "eleAndGroup=" + Arrays.toString(eleAndGroup) +
                ", count=" + count +
                '}';
    }
}
