package com.softnovo.algorithm.graph;

public class Edge {
    /**
     * 边对应出口的顶点.
     */
    private Vertex linked;
    /**
     * 边的权重
     */
    private int weight;

    public Edge(Vertex linked) {
        this(linked, 1);
    }

    public Edge(Vertex linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }

    public Vertex getLinked() {
        return linked;
    }

    public void setLinked(Vertex linked) {
        this.linked = linked;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
