package com.softnovo.algorithm.graph;

import java.util.Arrays;

public class UnionFind {
    /**
     * 记录结点元素和该元素所在分组的标识
     */
    private final int[] eleAndGroup;

    /**
     * 记录并查集中数据的分组个数
     */
    private int count;

    public UnionFind( int size ) {
        this.count = size;
        this.eleAndGroup = new int[size];
        for (int i = 0; i < this.count; i++) {
            this.eleAndGroup[i] = i;
        }
    }

    public int find(int v) {
        return eleAndGroup[v];
    }

    public boolean connected(int v, int w) {
        return find(v) == find(w);
    }

    public void union(int v, int w) {
        if (connected(v, w)) {
            return;
        }

        int vGroup = find(v);
        int wGroup = find(w);

        for (int i = 0; i < eleAndGroup.length; i++) {
            if (eleAndGroup[i] == vGroup) {
                eleAndGroup[i] = wGroup;
            }
        }
        count--;
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind( 10 );
        System.out.println(unionFind);
        System.out.println(unionFind.groupCount());
        System.out.println(unionFind.find(5));
        System.out.println(unionFind.connected(1, 3));

        unionFind.union(0, 1);
        System.out.println(unionFind);

        unionFind.union(1, 2);
        System.out.println(unionFind);
        System.out.println(unionFind.groupCount());

        unionFind.union(2, 8);
        System.out.println(unionFind.groupCount());
        System.out.println(unionFind);

        unionFind.union(8, 5);
        System.out.println(unionFind.groupCount());
        System.out.println(unionFind);




    }



    public int groupCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return "UnionFind{" +
                "eleAndGroup=" + Arrays.toString(eleAndGroup) +
                ", count=" + count +
                '}';
    }
}
