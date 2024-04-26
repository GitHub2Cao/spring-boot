package com.softnovo.algorithm.graph;

import java.util.List;
import java.util.Objects;

/**
 * 图的顶点
 */
public class Vertex {
    /**
     * 顶点名称
     */
    private String name;
    /**
     * 与顶点相关的边集合.
     */
    private List<Edge> edges;
    /**
     * 是否被访问过，用在 BFS 和 DFS
     */
    private boolean visited;
    /**
     * 入度，用在拓扑排序
     */
    private int inDegree;
    /**
     * 状态 0-未访问 1-访问中 2-访问过，用在拓扑排序
     */
    private int status;
    /**
     * 距离
     */
    private int dist = INF;

    private static final Integer INF = Integer.MAX_VALUE;
    /**
     * 记录从何而来
     */
    private Vertex prev = null;

    public Vertex(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
        //String n = name;
//        if (visited) {
//            n = "\u001B[31m" + name + "\u001B[0m";
//        }
//        return n + '(' + (dist == Integer.MAX_VALUE ? "∞" : String.valueOf(dist)) + ") <- " + (prev == null ? "null" : prev.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getInDegree() {
        return inDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    public boolean isZeroInDegree() {
        return inDegree == 0;
    }

    public void addOneInDegree() {
        this.inDegree += 1;
    }

    public void minusOneInDegree() {
        this.inDegree -= 1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }
}
