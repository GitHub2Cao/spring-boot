package com.softnovo.algorithm.graph;

import java.util.Arrays;

public class UnionFindTreeOp {
    private final int[] eleAndGroup;

    private final int[] sz;

    private int count;

    public UnionFindTreeOp(int size) {
        this.count = size;
        this.eleAndGroup = new int[size];
        for (int i = 0; i < eleAndGroup.length; i++) {
            this.eleAndGroup[i] = i;
        }
        this.sz = new int[size];
        Arrays.fill(this.sz, 1);
    }

    public int count() {
        return this.count;
    }

    public int find(int p) {
        if (eleAndGroup[p] == p) {
            return p;
        }
        return find(eleAndGroup[p]);
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        // 判断pRoot对应的树大，还是qRoot对应的树大，最终需要吧较小的树合并到较大的树中
        if (sz[pRoot] < sz[qRoot]) {
            eleAndGroup[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            eleAndGroup[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count--;
    }

    public static void main(String[] args) {
        UnionFindTreeOp unionFindTreeOp = new UnionFindTreeOp(10);
        System.out.println(unionFindTreeOp);


        unionFindTreeOp.union(0,1);
        System.out.println(unionFindTreeOp);

        unionFindTreeOp.union(1, 2);
        System.out.println(unionFindTreeOp);

        unionFindTreeOp.union(3, 0);
        System.out.println(unionFindTreeOp);

        unionFindTreeOp.union(8, 9);
        System.out.println(unionFindTreeOp);
        unionFindTreeOp.union(7,8);
        System.out.println(unionFindTreeOp);

        unionFindTreeOp.union(7, 0);
        System.out.println(unionFindTreeOp);
    }

    @Override
    public String toString() {
        return "UnionFindTreeOp{" +
                "eleAndGroup=" + Arrays.toString(eleAndGroup) +
                ", sz=" + Arrays.toString(sz) +
                ", count=" + count +
                '}';
    }
}
