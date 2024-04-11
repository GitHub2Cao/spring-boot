package com.softnovo.algorithm.tree.binarytree.bst;

public class BSTNode<K, V> {
    private K key;
    private V value;
    BSTNode<K, V> left;
    BSTNode<K, V> right;

    public BSTNode(K key) {
        this.key = key;
    }

    public BSTNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public BSTNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<K, V> left) {
        this.left = left;
    }

    public BSTNode<K, V> getRight() {
        return right;
    }

    public void setRight(BSTNode<K, V> right) {
        this.right = right;
    }
}
