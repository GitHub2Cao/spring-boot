package com.softnovo.algorithm.tree.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        /**
         *              5
         *            /   \
         *           7     3
         *         / \    / \
         *        1   4  9   8
         */
        TreeNode<Integer> node8 = new TreeNode<>(null, 8, null);
        TreeNode<Integer> node9 = new TreeNode<>(null, 9, null);
        TreeNode<Integer> node3 = new TreeNode<>(node9, 3, node8);
        TreeNode<Integer> node4 = new TreeNode<>(null, 4, null);
        TreeNode<Integer> node1 = new TreeNode<>(null, 1, null);
        TreeNode<Integer> node7 = new TreeNode<>(node1, 7, node4);
        TreeNode<Integer> node5 = new TreeNode<>(node7, 5, node3);

        System.out.println(maxDepth(null));
        System.out.println(maxDepth(node5));
        System.out.println(maxDepth(node7));
        System.out.println(maxDepth(node1));

    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        invert(root);
        return root;
    }

    private static void invert(TreeNode node) {
        if (node == null) {
            return;
        }

        TreeNode temp = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(temp);

        invert(node.getLeft());
        invert(node.getRight());

    }

    private static void swap(TreeNode left, TreeNode right) {
        Object temp = left.getValue();
        left.setValue(right.getValue());
        right.setValue(temp);
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMaxDepth = maxDepth(root.getLeft());
        int rightMaxDepth = maxDepth(root.getRight());
        return leftMaxDepth > rightMaxDepth ? leftMaxDepth + 1 : rightMaxDepth + 1;
    }


        public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return symmetric(root.getLeft(), root.getRight());
    }

    private static boolean symmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.getValue() != right.getValue()) {
            return false;
        }
        return symmetric(left.getLeft(), right.getRight()) && symmetric(left.getRight(), right.getLeft());
    }

    public static List<Integer> postorderTraversal(TreeNode<Integer> root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.addAll(postorderTraversal(root.getLeft()));
            list.addAll(postorderTraversal(root.getRight()));
            list.add(root.getValue());
        }

        return list;
    }

    public static List<Integer> preorderTraversal(TreeNode<Integer> root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.add(root.getValue());
            list.addAll(preorderTraversal(root.getLeft()));
            list.addAll(preorderTraversal(root.getRight()));
        }

        return list;
    }

    public static List<Integer> inorderTraversal(TreeNode<Integer> root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.addAll(inorderTraversal(root.getLeft()));
            list.add(root.getValue());
            list.addAll(inorderTraversal(root.getRight()));
        }

        return list;
    }

    public static List<Integer> preorderTraversalIte(TreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode<Integer>> stack = new LinkedList<>();
        TreeNode<Integer> current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                result.add(current.getValue());
                stack.push(current);
                current = current.getLeft();
            }

            TreeNode<Integer> pop = stack.pop();
            current = pop.getRight();
        }

        return result;
    }

    public static List<Integer> inorderTraversalIte(TreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            TreeNode<Integer> pop = stack.pop();
            result.add(pop.getValue());
            current = pop.getRight();
        }

        return result;
    }

    public static List<Integer> postorderTraversalIte(TreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        TreeNode last = null;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            TreeNode<Integer> top = stack.peek();
            if (top.getRight() == null || top.getRight() == last) {
                last = stack.pop();
                result.add(top.getValue());
            } else {
                current = top.getRight();
            }
        }

        return result;
    }


}
