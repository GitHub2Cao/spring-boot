package com.softnovo.algorithm.graph;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TopologicalOrder {
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static List<String> topologicalOrderKahn(List<Vertex> graph) {
        List<String> result = new LinkedList<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        for(Vertex v : graph) {
            List<Edge> edges = v.getEdges();
            for(Edge e : edges) {
                e.getLinked().addOneInDegree();
            }
        }
        for(Vertex v : graph) {
            System.out.println(v.getName() + " : " + v.getInDegree());
        }

        for (Vertex v : graph) {
            if (v.isZeroInDegree()) {
                queue.addLast(v);
            }
        }

        while (!queue.isEmpty()) {
            Vertex v = queue.removeFirst();
            result.add(v.getName());
            List<Edge> edges = v.getEdges();
            for(Edge e : edges) {
                Vertex adjacency = e.getLinked();
                adjacency.minusOneInDegree();
                if (adjacency.isZeroInDegree()) {
                    queue.addLast(adjacency);
                }
            }
        }
        return result;
    }

    public static List<String> topologicalOrderDfs(List<Vertex> graph, LinkedList<String> result) {
        for (Vertex vertex : graph) {
            doDfs(vertex, result);
        }
        return result;
    }

    private static void doDfs(Vertex vertex, LinkedList<String> result) {
        if (vertex.getStatus() == 2) {
            return;
        }

        if (vertex.getStatus() == 1) {
            throw new RuntimeException("图有环");
        }

        vertex.setStatus(1);

        for (Edge edge : vertex.getEdges()) {
            doDfs(edge.getLinked(), result);
        }
        vertex.setStatus(2);
        result.addFirst(vertex.getName());
    }

    public static void main(String[] args) {
        //System.out.println(topologicalOrderKahn(Test.getGraphList()));
        System.out.println(topologicalOrderDfs(Test.getGraphList(), Lists.newLinkedList()));
    }
}
