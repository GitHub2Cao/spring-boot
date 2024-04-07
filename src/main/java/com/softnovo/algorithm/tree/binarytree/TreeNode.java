package com.softnovo.algorithm.tree.binarytree;

public class TreeNode<E> {
    private TreeNode<E> left;
    private E value;
    private TreeNode<E> right;

    public TreeNode(TreeNode<E> left, E value, TreeNode<E> right) {
        this.left = left;
        this.value = value;
        this.right = right;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
