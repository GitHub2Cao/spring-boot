package com.softnovo.algorithm.graph;


import java.util.LinkedList;

public class Dfs {
    public static void main(String[] args) {
        Vertex vertex = Test.getGraph2();
        bfs(vertex);
    }

    private static void bfs(Vertex vertex) {
        if (vertex == null) {
            return;
        }
        int level = 0;
        vertex.setPrev(new Vertex("石头"));
        LinkedList<Vertex> queue = new LinkedList<>();
        queue.addLast(vertex);
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // 当前层节点数
            System.out.println("===== level " + (level++) + " =====" );
            for (int i = 0; i < levelSize; i++) {
                Vertex poll = queue.pollFirst();
                if (!poll.isVisited()) {
                    poll.setVisited(Boolean.TRUE);
                    System.out.println(poll.getName() + " : " + poll.getPrev());
                }
                for (Edge edge : poll.getEdges()) {
                    if (!edge.getLinked().isVisited()) {
                        edge.getLinked().setPrev(poll);
                        queue.addLast(edge.getLinked());
                    }
                }
            }
        }
    }

    private static void dfsRec(Vertex vertex) {
        if (vertex == null) {
            return;
        }
        System.out.println(vertex.getName());
        vertex.setVisited(Boolean.TRUE);
        for (Edge edge : vertex.getEdges()) {
            if (!edge.getLinked().isVisited()) {
                dfsRec(edge.getLinked());
            }
        }
    }

    private static void dfsIte(Vertex vertex) {
        if (vertex == null) {
            return;
        }
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(vertex);
        while (!stack.isEmpty()) {
            Vertex pop = stack.pop();
            if (!pop.isVisited()) {
                System.out.println(pop.getName());
                pop.setVisited(Boolean.TRUE);
            }
            for (Edge edge : pop.getEdges()) {
                if (!edge.getLinked().isVisited()) {
                    stack.push(edge.getLinked());
                }
            }
        }
    }
}
