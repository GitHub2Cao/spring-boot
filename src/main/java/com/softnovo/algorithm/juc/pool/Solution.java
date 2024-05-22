package com.softnovo.algorithm.juc.pool;

import java.util.*;

class Solution {
    //NodeDao nodeDao;
    /**
     * 正常返回：返回树状结构；
     * 非正常返回：空节点
     */

    /**
     模拟 nodeDao query 方法
     */
    Set<Node> allAreasFromDb() {
        Set<Node> set = new HashSet<>();
        set.add(new Node("中国", ""));
        set.add(new Node("浙江", "中国"));
        set.add(new Node("郑州", "河南"));
        set.add(new Node("杭州", "浙江"));
        set.add(new Node("宁波", "浙江"));
        set.add(new Node("开封", "河南"));
        return set;
    }

    Area getAreaTree() {
        Set<Node> dbNodes = allAreasFromDb();
        Area area = new Area();
        // 可以使用一些utils 比如 apache等
        if (dbNodes == null || dbNodes.size() <= 0) {
            return area; // 最好不要返回null.
        }

        Iterator<Node> iterator = dbNodes.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            area.name = node.name;

        }

        Map<String, String> a = new HashMap<>();
        a.entrySet();


        return null;

    }
}

class Node {
    String name;
    String parentName;

    Node(String name, String parentName) {
        this.name = name;
        this.parentName = parentName;
    }
}

class Area {
    String name;
    List<Area> children;
}
