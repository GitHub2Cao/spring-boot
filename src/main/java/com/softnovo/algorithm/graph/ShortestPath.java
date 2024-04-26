package com.softnovo.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShortestPath {
    public static void main(String[] args) {
        List<Vertex> graphList = Test.getGraphList();
        dijkstra(graphList, graphList.get(0));
    }

    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        source.setDist(0);
        int vertexCount = graph.size();
        for (int i = 0; i < vertexCount - 1; i++) {
            AtomicBoolean hasChange = new AtomicBoolean(false);
            graph.forEach(start -> {
                List<Edge> edges = start.getEdges();
                edges.forEach(edge -> {
                    Vertex end = edge.getLinked();
                    if (start.getDist() != Integer.MAX_VALUE && start.getDist() + edge.getWeight() < end.getDist()) {
                        end.setDist(start.getDist() + edge.getWeight());
                        hasChange.set(true);
                        end.setPrev(start);
                    }
                });
            });
            // 类似冒泡排序提前结束.
            if (!hasChange.get()) {
                break;
            }
        }

        AtomicBoolean hasRing = new AtomicBoolean(false);
        graph.forEach(start -> {
            List<Edge> edges = start.getEdges();
            edges.forEach(edge -> {
                Vertex end = edge.getLinked();
                if (start.getDist() != Integer.MAX_VALUE && start.getDist() + edge.getWeight() < end.getDist()) {
                    hasRing.set(true);
                    return;
                }
            });
            if (hasRing.get()) {
                return;
            }
        });

        graph.forEach(vertex -> {
            System.out.println(vertex.getName() + " : " + vertex.getDist() + " | " + getPath(vertex));
        });
    }

    public static void dijkstra(List<Vertex> graph, Vertex source) {
        ArrayList<Vertex> notFoundList = new ArrayList<>(graph);
        source.setDist(0);
        while(!notFoundList.isEmpty()) {
            Vertex vertex = chooseMinDistVertex(notFoundList);
            updateAdjacencyDist(vertex, notFoundList);
            notFoundList.remove(vertex);
        }
        for(Vertex vertex : graph) {
            System.out.println(vertex.getName() + " : " + vertex.getDist() + " : " + getPath(vertex));
        }
    }

    private static String getPath(Vertex vertex) {
        if (vertex.getPrev() == null) {
            return vertex.getName();
        }
        return getPath(vertex.getPrev()) + " -> " + vertex.getName();
    }

    private static void updateAdjacencyDist(Vertex vertex, ArrayList<Vertex> notFoundList) {
        List<Edge> edges = vertex.getEdges();

        for(Edge edge : edges) {
            Vertex adjacency = edge.getLinked();
            if (notFoundList.contains(adjacency)) {
                if (vertex.getDist() + edge.getWeight() < adjacency.getDist()) {
                    adjacency.setPrev(vertex);
                    adjacency.setDist(vertex.getDist() + edge.getWeight());
                }
            }
        }
    }

    private static Vertex chooseMinDistVertex(ArrayList<Vertex> list) {
        Vertex min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getDist() < min.getDist()) {
                min = list.get(i);
            }
        }
        return min;
    }
}
