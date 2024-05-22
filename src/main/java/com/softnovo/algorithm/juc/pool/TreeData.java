package com.softnovo.algorithm.juc.pool;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.List;

public class TreeData {
    private String id;
    private String parentId;
    private List<TreeData> children;
    public String getParentId() {
        return this.parentId;
    }

    public String getId() {
        return this.id;
    }

    public void setChildList(List<TreeData> arrayList) {
        this.children = arrayList;
    }

    public List<TreeData> getChildList() {
        return this.children;
    }

    public TreeData(String id, String parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "TreeData{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", children=" + children +
                '}';
    }
}
