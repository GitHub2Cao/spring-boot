package com.softnovo.algorithm.tree.binarytree.bst;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要方法
 *    put (putIte, putRec) - put
 *    get (getIte, getRec) - 查询
 *    min (minIte, minRec) - 最小
 *    max (maxIte, maxRec) - 最大
 *    predecessor - 一个节点的前驱(前任)节点是指比它小的节点中，最大的那个
 *    successor - 一个节点的后继(后任)节点是指比它大的节点中，最小的那个
 *
 *
 *
 * @param <K>
 * @param <V>
 */
public class BST<K extends Comparable<K>, V> {
    private BSTNode<K, V> root;
    private int size;

    public BST() {
    }

    /**
     *              5
     *            /   \
     *           2     7
     *         / \    / \
     *        1   4  6   8
     */
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.putIte(5, "5");
        bst.putIte(2, "2");
        bst.putIte(7, "7");
        bst.putIte(1, "1");
        bst.putIte(4, "4");
        bst.putIte(6, "6");
        bst.putIte(8, "8");

        List<String> preList = new ArrayList<>();
        bst.preOrderRec(bst.root, preList);
        // 5 2 1 4 7 6 8
        System.out.println("pre -- " + preList);

        List<String> inList = new ArrayList<>();
        bst.inOrderRec(bst.root, inList);
        // 1 2 4 5 6 7 8
        System.out.println("in -- " + inList);

        List<String> postList = new ArrayList<>();
        bst.postOrderRec(bst.root, postList);
        // 1 4 2 6 8 7 5
        System.out.println("post -- " + postList);

        System.out.println(bst.getRec(6));
        System.out.println(bst.getRec(7));
        System.out.println(bst.getRec(10));

        System.out.println(bst.getIte(2));
        System.out.println(bst.getIte(8));
        System.out.println(bst.getIte(9));

        System.out.println("minRec == " + bst.minRec());
        System.out.println("minIte == " + bst.minIte());

        System.out.println("maxRec == " + bst.maxRec());
        System.out.println("maxIte == " + bst.maxIte());

        System.out.println(bst.isEmpty());
        System.out.println(bst.size());
        System.out.println("---predecessor-----");
        System.out.println(bst.predecessor(6));
        System.out.println(bst.predecessor(5));
        System.out.println(bst.predecessor(8));
        System.out.println(bst.predecessor(1));
        System.out.println(bst.predecessor(9));
        System.out.println("--successor--- ");
        System.out.println(bst.successor(1));
        System.out.println(bst.successor(4));
        System.out.println(bst.successor(6));
        System.out.println(bst.successor(8));
        System.out.println(bst.successor(9));

        System.out.println("--delete--- ");
        /**
         *              5
         *            /   \
         *           2     7
         *         / \    / \
         *        1   4  6   8
         */
//        System.out.println(bst.delete(4));
        System.out.println(bst.delete(6));
        inList = new ArrayList<>();
        bst.inOrderRec(bst.root, inList);
        // 1 2 4 5 6 7 8
        System.out.println("in -- " + inList);
        System.out.println(bst.size());


    }

    /**
     *             5
     *           /  \
     *         2     7
     *        / \   /
     *       1   3 6
     *       	  \
     *       	   4
     * 删除节点左右孩子都没有，已经被涵盖在情况1、情况2当中，把null托孤给Parent - 1
     * 删除节点没有左孩子，将右孩子托孤给Parent - 3
     * 删除节点没有右孩子，将左孩子托孤给Parent - 7
     *
     * 删除节点左右孩子都有，可以将它的后继节点(称为S)托孤给Parent，设S的父亲为SP，又分两种情况
     *      SP就是被删除节点，此时D与S紧邻，只需将S托孤给Parent
     *      SP不是被删除节点，此时D与S不相邻，此时需要将S的后代托孤给SP，再将S托孤给Parent
     */
    public V delete(K key) {
        BSTNode<K, V> current = this.root; // 要删除的节点
        BSTNode<K, V> parent = null;  // 要删除节点的父亲.
        while (current != null) {
            int compared = current.getKey().compareTo(key);
            if (compared > 0) {
                parent = current;
                current = current.getLeft();
            } else if (compared < 0) {
                parent = current;
                current = current.getRight();
            } else {
                break;
            }
        }
        // 没有查询到key值
        if (current == null) {
            return null;
        }

        V value = current.getValue();

        if (current.getLeft() == null) {
            if (current == this.root) {
                root = current.getRight();
            } else if (parent.getLeft() == current) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        } else if (current.getRight() == null) {
            if (current == this.root) {
                root = root.getLeft();
            } else if (parent.getLeft() == current) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
        } else {
            BSTNode<K, V> target = current.getRight();
            BSTNode<K, V> targetParent = current;
            while (target.getLeft() != null) {
                targetParent = target;
                target = target.getLeft();
            }

            current.setValue(target.getValue());
            current.setKey(target.getKey());

            if (target == targetParent.left) {
                targetParent.left = target.right;
            } else {
                targetParent.right = target.right;
            }
        }

        size--;
        return value;
    }

    private BSTNode<K, V> successorNode4Delete(BSTNode<K, V> parent, K key) {
        BSTNode<K, V> ancestorFromRight = null;
        BSTNode<K, V> current = parent;
        while (current != null) {
            int compared = current.getKey().compareTo(key);
            if (compared > 0) {
                ancestorFromRight = parent;
                current = current.getLeft();
            } else if (compared < 0) {
                current = current.getRight();
            } else {
                break;
            }
        }

        if (current == null) {
            return null;
        }

        if (parent.getRight() != null) {
            return minNode(parent.getRight());
        }

        return ancestorFromRight != null ? ancestorFromRight : null;
    }

    /**
     *              5
     *            /   \
     *           2     7
     *         / \    / \
     *        1   4  6   8
     */
    public V successor(K key) {
        BSTNode<K, V> ancestorFromRight = null;
        BSTNode<K, V> parent = root;
        while (parent != null) {
            int compared = parent.getKey().compareTo(key);
            if (compared > 0) {
                ancestorFromRight = parent;
                parent = parent.getLeft();
            } else if (compared < 0) {
                parent = parent.getRight();
            } else {
                break;
            }
        }
        if (parent == null) {
            return null;
        }

        if (parent.getRight() != null) {
            return min(parent.getRight());
        }

        return ancestorFromRight != null ? ancestorFromRight.getValue() : null;
    }

    /**
     *              5
     *            /   \
     *           2     7
     *         / \    / \
     *        1   4  6   8
     */
    public V predecessor(K key) {
        BSTNode<K, V> ancestorFromLeft = null;
        BSTNode<K, V> parant = root;
        while (parant != null) {
            int compared = parant.getKey().compareTo(key);
            if (compared > 0) {
                parant = parant.getLeft();
            } else if (compared < 0) {
                ancestorFromLeft = parant;
                parant = parant.getRight();
            } else {
                break;
            }
        }
        if (parant == null) {
            return null;
        }

        if (parant.getLeft() != null) {
            return max(parant.getLeft());
        }
        return ancestorFromLeft != null ? ancestorFromLeft.getValue() : null;
    }

    public V maxRec() {
        return getMaxRec(this.root);
    }

    private V getMaxRec(BSTNode<K, V> root) {
        if (root == null) {
            return null;
        }

        if (root.getRight() != null) {
            return getMaxRec(root.getRight());
        } else {
            return root.getValue();
        }
    }

    public V maxIte() {
        if (this.root == null) {
            return null;
        }
        BSTNode<K, V> parent = this.root;
        while (parent.getRight() != null) {
            parent = parent.getRight();
        }
        return parent.getValue();
    }

    public V max(BSTNode<K, V> parent) {
        if (parent == null) {
            return null;
        }
        BSTNode<K, V> current = parent;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current.getValue();
    }

    public V minRec() {
        return getMinRec(this.root);
    }

    public V minIte() {
        if (this.root == null) {
            return null;
        }
        BSTNode<K, V> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current.getValue();
    }

    public V min(BSTNode<K, V> parent) {
        if (parent == null) {
            return null;
        }
        BSTNode<K, V> current = parent;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current.getValue();
    }

    public BSTNode<K, V> minNode(BSTNode<K, V> parent) {
        if (parent == null) {
            return null;
        }
        BSTNode<K, V> current = parent;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }

    private V getMinRec(BSTNode<K, V> root) {
        if (root == null) {
            return null;
        }
        if (root.getLeft() == null) {
            return root.getValue();
        } else {
            return getMinRec(root.getLeft());
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void preOrderRec(BSTNode<K, V> node, List<String> resultList) {
        if (node == null) {
            return;
        }
        resultList.add(node.getValue().toString());
        preOrderRec(node.getLeft(), resultList);
        preOrderRec(node.getRight(), resultList);
    }

    private void inOrderRec(BSTNode<K, V> node, List<String> resultList) {
        if (node == null) {
            return;
        }
        inOrderRec(node.getLeft(), resultList);
        resultList.add(node.getValue().toString());
        inOrderRec(node.getRight(), resultList);
    }

    private void postOrderRec(BSTNode<K, V> node, List<String> resultList) {
        if (node == null) {
            return;
        }
        postOrderRec(node.getLeft(), resultList);
        postOrderRec(node.getRight(), resultList);
        resultList.add(node.getValue().toString());
    }

    public void putRec(K key, V value) {
        this.root = putRecursion(this.root, key, value);
        size++;
    }

    public void putIte(K key, V value) {
        if (root == null) {
            root = new BSTNode<>(key, value);
            size++;
            return;
        }
        BSTNode<K, V> current = root;
        BSTNode<K, V> parent = null;
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
        }
        int compared = parent.getKey().compareTo(key);
        if (compared > 0) {
            parent.left = new BSTNode(key, value);
        } else {
            parent.right = new BSTNode(key, value);
        }
        size++;
    }

    private BSTNode<K, V> putRecursion(BSTNode<K, V> root, K key, V value) {
        if (root == null) {
            return new BSTNode<>(key, value, null, null);
        }
        int result = root.getKey().compareTo(key);
        if (result > 0) {
            root.setLeft(putRecursion(root.getLeft(), key, value));
        } else if (result < 0) {
            root.setRight(putRecursion(root.getRight(), key, value));
        } else {
            // 替换 value，key 不能重复。
            root.setValue(value);
        }

        return root;
    }

    public V getIte(K searchKey) {
        BSTNode<K, V> current = root;
        while (current != null) {
            int result = current.getKey().compareTo(searchKey);
            if (result > 0) {
                current = current.getLeft();
            } else if (result < 0) {
                current = current.getRight();
            } else {
                return current.getValue();
            }
        }

        return null;
    }

    public V getRec(K searchKey) {
        return get(root, searchKey);
    }

    private V get(BSTNode<K, V> root, K key) {
        if (root == null) {
            return null;
        }
        int result = root.getKey().compareTo(key);
        if (result > 0) {
            return get(root.getLeft(), key);
        } else if (result < 0) {
            return get(root.getRight(), key);
        } else {
            return root.getValue();
        }
    }

    public V get(K key) {
        BSTNode<K, V> p = root;
        while (p != null) {
            int result = key.compareTo(p.getKey());
            if (result < 0) {
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            } else {
                return p.getValue();
            }
        }
        return null;
    }
}
