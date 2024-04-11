package com.softnovo.algorithm.tree.binarytree.avl;

import com.softnovo.algorithm.tree.binarytree.bst.BSTNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> {
    private AVLNode<K, V> root;
    private int size;

    public AVLTree() {
    }

    public static void main(String[] args) {
        AVLTree<Integer, String> avlTree = new AVLTree<>();
//        avlTree.putRec(1, "1");
//        avlTree.putRec(2, "2");
//        avlTree.putRec(3, "3");
//        avlTree.putRec(4, "4");

        avlTree.putIte(5, "5");
        avlTree.putIte(6, "6");
        avlTree.putIte(7, "7");
        avlTree.putIte(8, "8");
        avlTree.putIte(9, "9");
//        avlTree.putIte(10, "10");
//        avlTree.putIte(11, "11");
//        avlTree.putIte(12, "12");
        List<String> preList = new ArrayList<>();
        List<String> inList = new ArrayList<>();
        List<String> postList = new ArrayList<>();
        avlTree.preOrderRec(avlTree.root, preList);
        System.out.println(preList);

        avlTree.inOrderRec(avlTree.root, inList);
        System.out.println(inList);

        avlTree.postOrderRec(avlTree.root, postList);
        System.out.println(postList);
    }

    private void preOrderRec(AVLNode<K, V> node, List<String> resultList) {
        if (node == null) {
            return;
        }
        resultList.add(node.getValue().toString());
        preOrderRec(node.getLeft(), resultList);
        preOrderRec(node.getRight(), resultList);
    }

    private void inOrderRec(AVLNode<K, V> node, List<String> resultList) {
        if (node == null) {
            return;
        }
        inOrderRec(node.getLeft(), resultList);
        resultList.add(node.getValue().toString());
        inOrderRec(node.getRight(), resultList);
    }

    private void postOrderRec(AVLNode<K, V> node, List<String> resultList) {
        if (node == null) {
            return;
        }
        postOrderRec(node.getLeft(), resultList);
        postOrderRec(node.getRight(), resultList);
        resultList.add(node.getValue().toString());
    }

    public void putIte(K key, V value) {
        if (root == null) {
            root = new AVLNode<>(key, value);
            size++;
            return;
        }
        AVLNode<K, V> current = root;
        AVLNode<K, V> parent = null;
        LinkedList<AVLNode<K, V>> stack = new LinkedList<>();
        while (current != null) {
            parent = current;
            int compared = current.getKey().compareTo(key);
            if (compared > 0) {
                current = current.getLeft();
            } else if (compared < 0) {
                current = current.getRight();
            } else {
                current.setValue(value);
            }
            stack.push(parent);
        }
        int compared = parent.getKey().compareTo(key);
        AVLNode<K, V> newNode = new AVLNode(key, value);
        if (compared > 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        while (!stack.isEmpty()) {
            AVLNode<K, V> pop = stack.pop();
            updateHeight(pop);
            balance(pop);
        }

    }

    public void putRec(K key, V value) {
        root = doPut(root, new AVLNode(key, value));
        size++;
    }

    private AVLNode<K, V> doPut(AVLNode<K, V> parent, AVLNode<K, V> insertNode) {
        if (parent == null) {
            return insertNode;
        }
        if (insertNode.getKey().compareTo(parent.key) == 0) {
            parent.value = insertNode.getValue();
            return parent;
        }
        if (insertNode.getKey().compareTo(parent.key) < 0) {
            parent.left = doPut(parent.left, insertNode);
        } else {
            parent.right = doPut(parent.right, insertNode);
        }
        updateHeight(parent);
        return balance(parent);
    }

    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = bf(node);
        if (bf > 1 && bf(node.left) >= 0) {
            return rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) {
            return rightLeftRotate(node);
        } else if (bf < -1 && bf(node.right) > 0) {
            return leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) <= 0) {
            return leftRotate(node);
        }
        return node;
    }

    private int bf(AVLNode node) {
        return height(node.left) - height(node.right);
    }

    /**
     *    a
     *   / \
     *  b   c
     *     / \
     *    d   e
     *         \
     *          f
     * 左旋：右边高，右边上移，左边下沉；子树左移
     * 失衡节点(a)的 bf < -1，即右边更高；
     * 失衡节点的右孩子(c)的 bf <= 0，即右孩子这边右边更高或等高。
     * @param imbalancor
     * @return
     */
    private AVLNode<K, V> leftRotate(AVLNode<K, V> imbalancor) {
        AVLNode newRoot = imbalancor.getRight();
        AVLNode toLeft = newRoot.getLeft();
        newRoot.setLeft(imbalancor);
        imbalancor.setRight(toLeft);
        updateHeight(imbalancor);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     *       a
     *      / \
     *     c   b
     *    / \
     *   e   d
     *  /
     * f
     * 右旋：左边高，左边上移，右边下沉；子树右移
     * 失衡节点(a)的 bf > 1，即左边更高；
     * 失衡节点的左孩子(c)的 bf（1） >= 0 即左孩子这边也是左边更高或等高。
     * @param imbalancor
     * @return
     */
    private AVLNode<K, V> rightRotate(AVLNode<K, V> imbalancor) {
        AVLNode newRoot = imbalancor.getLeft();
        AVLNode toRight = newRoot.getRight();
        newRoot.setRight(imbalancor);
        imbalancor.setLeft(toRight);
        updateHeight(imbalancor);
        updateHeight(newRoot);
        return newRoot;
    }

    /**
     *      a
     *     / \
     *    c   b
     *   / \
     *  d   d
     *     / \
     *    e   f
     * 指先左旋左子树，再右旋根节点(失衡)
     * 失衡节点(a)的 bf > 1，即左边更高；
     * 失衡节点的左孩子(c)的 bf < 0 即左孩子这边是右边更高。
     * @param imbalancor
     * @return
     */
    private AVLNode<K, V> leftRightRotate(AVLNode<K, V> imbalancor) {
        imbalancor.setLeft(imbalancor.getLeft());
        return rightRotate(imbalancor);
    }

    /**
     * 	   a
     * 	  / \
     *   b   c
     * 	    / \
     * 	   d   e
     * 	  / \
     *   f   g
     * 指先右旋右子树，再左旋根节点(失衡)
     * 失衡节点(a)的 bf < -1，即右边更高；
     * 失衡节点的右孩子(c)的 bf > 0，即右孩子这边左边更高。
     * @param imbalancor
     * @return
     */
    private AVLNode<K, V> rightLeftRotate(AVLNode<K, V> imbalancor) {
        imbalancor.setRight(imbalancor.getRight());
        return leftRotate(imbalancor);
    }

    private void updateHeight(AVLNode<K, V> node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    private int height(AVLNode<K, V> node) {
        return node == null ? 0 : node.height;
    }





    static class AVLNode<K, V> {
        private int height = 1;
        private K key;
        private V value;
        private AVLNode left;
        private AVLNode right;

        public AVLNode(K key) {
            this.key = key;
        }

        public AVLNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(K key, V value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
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

        public AVLNode getLeft() {
            return left;
        }

        public void setLeft(AVLNode left) {
            this.left = left;
        }

        public AVLNode getRight() {
            return right;
        }

        public void setRight(AVLNode right) {
            this.right = right;
        }
    }


}
