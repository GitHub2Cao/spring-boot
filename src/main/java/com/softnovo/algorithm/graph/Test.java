package com.softnovo.algorithm.graph;

import com.google.common.collect.Lists;

import java.util.List;

public class Test {
    /**
     * @param args
     */
    public static void main(String[] args) {
        getGraph1();


        getGraph2();
    }

    public static List<Vertex> getGraphListNegative() {
        Vertex s = new Vertex("s");
        Vertex v = new Vertex("v");
        Vertex w = new Vertex("w");
        s.setEdges(Lists.newArrayList(new Edge(v, 3), new Edge(w, 2)));
        v.setEdges(Lists.newArrayList(new Edge(w, -2)));
        w.setEdges(Lists.newArrayList());

        return Lists.newArrayList(s, v, w);
    }

    public static List<Vertex> getGraphListABC() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        a.setEdges(Lists.newArrayList(new Edge(d, 4), new Edge(b, 5)));
        b.setEdges(Lists.newArrayList(new Edge(a, 5), new Edge(d, 2), new Edge(e, 6), new Edge(c, 8)));
        c.setEdges(Lists.newArrayList(new Edge(b, 8), new Edge(d, 15), new Edge(e, 1), new Edge(f, 5)));
        d.setEdges(Lists.newArrayList(new Edge(a, 4), new Edge(b, 2), new Edge(c, 15), new Edge(e, 6)));
        e.setEdges(Lists.newArrayList(new Edge(d, 6), new Edge(b, 6), new Edge(c, 1), new Edge(f, 12)));
        f.setEdges(Lists.newArrayList(new Edge(e, 12), new Edge(c, 5)));

        return Lists.newArrayList(a, b, c, d, e, f);

    }

    public static List<Vertex> getGraphList() {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.setEdges(Lists.newArrayList(new Edge(v6, 14), new Edge(v2, 7), new Edge(v3, 9)));
        v2.setEdges(Lists.newArrayList(new Edge(v4, 15)));
        v3.setEdges(Lists.newArrayList(new Edge(v6, 2), new Edge(v4, 11)));
        v4.setEdges(Lists.newArrayList(new Edge(v5, 6)));
        v5.setEdges(Lists.newArrayList());
        v6.setEdges(Lists.newArrayList(new Edge(v5, 9)));
        return Lists.newArrayList(v1, v2, v3, v4, v5, v6);
    }

    public static Vertex getGraph2() {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.setEdges(Lists.newArrayList(new Edge(v6, 14), new Edge(v2, 7), new Edge(v3, 9)));
        v2.setEdges(Lists.newArrayList(new Edge(v4, 15)));
        v3.setEdges(Lists.newArrayList(new Edge(v6, 2), new Edge(v4, 11)));
        v4.setEdges(Lists.newArrayList(new Edge(v5, 6)));
        v5.setEdges(Lists.newArrayList());
        v6.setEdges(Lists.newArrayList(new Edge(v5, 9)));
        return v1;
    }

    public static Vertex getGraph1() {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        a.setEdges(Lists.newArrayList(new Edge(b), new Edge(c)));
        b.setEdges(Lists.newArrayList(new Edge(d)));
        c.setEdges(Lists.newArrayList(new Edge(d)));
        d.setEdges(Lists.newArrayList());
        return a;
    }
}
