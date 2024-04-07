package com.softnovo.algorithm.tree.binarytree;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IteraTree {
    public static void main(String[] args) {
        /**
         *              5
         *            /   \
         *           7     3
         *         / \    / \
         *        1   4  9   8
         */
        TreeNode<String> node8 = new TreeNode<>(null, "8", null);
        TreeNode<String> node9 = new TreeNode<>(null, "9", null);
        TreeNode<String> node3 = new TreeNode<>(node9, "3", node8);
        TreeNode<String> node4 = new TreeNode<>(null, "4", null);
        TreeNode<String> node1 = new TreeNode<>(null, "1", null);
        TreeNode<String> node7 = new TreeNode<>(node1, "7", node4);
        TreeNode<String> node5 = new TreeNode<>(node7, "5", node3);

        List<String> list = new ArrayList<>();
        //inOrder(node5, list);
//        postOrder(node5);
        //level(node5);
        //System.out.println(list);
//        preOrderIte(node5);
        preOrderIteSelf(node5);
        System.out.println();
        inOrderIteSelf(node5);
        System.out.println();
        postOrderIteSelf(node5);

    }

    private static void preInPost(TreeNode root) {
        List<String> preList = new ArrayList<>();
        List<String> inList = new ArrayList<>();
        List<String> postList = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root; // 代表当前节点
        TreeNode last = null; // 最近一次弹栈的元素
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                preList.add(current.getValue().toString());
                stack.push(current);
                current = current.getLeft();
            }

            TreeNode peeked = stack.peekFirst();
            if (peeked.getRight() == null) {
                inList.add(peeked.getValue().toString());
                last = stack.pop();
                postList.add(peeked.getValue().toString());
            } else if (peeked.getRight() == last) {
                last = stack.pop();
                postList.add(peeked.getValue().toString());
            } else {
                inList.add(peeked.getValue().toString());
                current = peeked.getRight();
            }
        }
        System.out.println(preList);
        System.out.println(inList);
        System.out.println(postList);
    }

    private static void preOrderIteSelf(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                System.out.print(current.getValue() + " ");
                stack.offerFirst(current);
                current = current.getLeft();
            }
            // polled 肯定不会null
            TreeNode polled = stack.pollFirst();
            current = polled.getRight();
        }
    }

    private static void inOrderIteSelf(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.offerFirst(current);
                current = current.getLeft();
            }

            // polled 肯定不会null
            TreeNode polled = stack.pollFirst();
            System.out.print(polled.getValue() + " ");
            current = polled.getRight();
        }
    }

    /**
     *              5
     *            /   \
     *           7     3
     *         / \    / \
     *        1   4  9   8
     */
    private static void postOrderIteSelf(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        TreeNode last = null;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.offerFirst(current);
                current = current.getLeft();
            }
            TreeNode peeked = stack.peekFirst();
            if (peeked.getRight() == null || peeked.getRight() == last) {
                stack.pop();
                System.out.print(peeked.getValue() + " ");
                last = peeked;
            } else {
                current = peeked.getRight();
            }
        }
    }

    /**
     * 先根序, 5,7,1,4,3,9,8
     * @param node
     */
    private static void preOrderRec(TreeNode node, List<String> resultList) {
        if (node == null) {
            return;
        }
        System.out.println(node.getValue());
        resultList.add(node.getValue().toString());
        preOrderRec(node.getLeft(), resultList);
        preOrderRec(node.getRight(), resultList);
    }

    /**
     * 5,7,1,4,3,9,8
     * @param node
     */
    /**
     *              5
     *            /   \
     *           7     3
     *         / \    / \
     *        1   4  9   8
     */
    private static void preOrderIte(TreeNode node) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode current = node;

        while (current != null || !linkedList.isEmpty()) {
            if (current != null) {
                System.out.println(current.getValue());
                linkedList.offerFirst(current);
                current = current.getLeft();
            } else {
                TreeNode polled = linkedList.pollFirst();
                current = polled.getRight();
            }
        }
    }

    private static void inOrderIte(TreeNode node) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode current = node;
        while (current != null || !linkedList.isEmpty()) {
            if (current != null) {
                linkedList.offerFirst(current);
                current = current.getLeft();
            } else {
                TreeNode polled = linkedList.pollFirst();
                System.out.println(polled.getValue());
                current = polled.getRight();
            }
        }
    }

    /**
     * 不具备普适性.
     * 5,7,1,4,3,9,8
     * @param root
     */
    private static void preOrderIte1(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.offerFirst(root);
        while (!linkedList.isEmpty()) {
            TreeNode polled = linkedList.pollFirst();
            System.out.println(polled.getValue());

            if (polled.getRight() != null) {
                linkedList.offerFirst(polled.getRight());
            }

            if (polled.getLeft() != null) {
                linkedList.offerFirst(polled.getLeft());
            }
        }
    }

    private static void postOrderIte1(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
//        while (!stack.isEmpty()) {
//            TreeNode polled = linkedList.pollFirst();
//            System.out.println(polled.getValue());
//
//            if (polled.getRight() != null) {
//                linkedList.offerFirst(polled.getRight());
//            }
//
//            if (polled.getLeft() != null) {
//                linkedList.offerFirst(polled.getLeft());
//            }
//        }
    }

    /**
     * 中根序, 1,7,4,5,9,3,8
     * @param node
     */
    private static void inOrderRec(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderRec(node.getLeft());
        System.out.println(node.getValue());
        inOrderRec(node.getRight());
    }

    /**
     * 1,7,4,5,9,3,8
     * @param node
     */


    /**
     * 后根序, 1,4,7,9,8,3,5
     * @param node
     */
    private static void postOrderRec(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderRec(node.getLeft());
        postOrderRec(node.getRight());
        System.out.println(node.getValue());
    }

    private static void postOrderIte(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        TreeNode last = null;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.offerFirst(current);
                current = current.getLeft();
            } else {
                TreeNode peeked = stack.peekFirst();
                if (peeked.getRight() != null || peeked == last) {
                    last = stack.pollFirst();
                    System.out.println(last);
                } else {
                    current = peeked.getRight();
                }
            }
        }
    }

    /**
     * 层遍历，5, 7, 3, 1, 4, 9, 8
     * @param node
     */
    private static void level(TreeNode node) {
        if (node == null) {
            return;
        }

        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.offerLast(node);
        while (!linkedList.isEmpty()) {
            TreeNode treeNode = linkedList.pollFirst();
            System.out.println(treeNode.getValue());
            if (treeNode.getLeft() != null) {
                linkedList.offerLast(treeNode.getLeft());
            }
            if (treeNode.getRight() != null) {
                linkedList.offerLast(treeNode.getRight());
            }
        }
    }


}
