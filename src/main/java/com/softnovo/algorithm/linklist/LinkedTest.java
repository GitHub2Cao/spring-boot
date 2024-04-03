package com.softnovo.algorithm.linklist;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedTest {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        ListNode sentinel = new ListNode(0, null);
        ListNode current = sentinel;

        for (int i = 0; i < integers.size(); i++) {
            ListNode listNode = new ListNode(integers.get(i), null);
            current.next = listNode;
            current = listNode;
        }
        ListNode listNode = reverseListRecursion(sentinel.next);
        System.out.println(listNode.val);
    }

    public static ListNode reverseList(ListNode listNode) {
        System.out.println("++++ " + listNode.val);
        if (listNode == null || listNode.next == null) { // 不足两个节点
            System.out.println("---- " + listNode.val);
            return listNode; // 最后一个节点
        }
        ListNode last = reverseList(listNode.next);
        System.out.println("---- " + listNode.val);
        return last;
    }

    public static ListNode reverseListIterate(ListNode head) {
        /**
         * 对于链表中的一个节点(cur)来说，它包含节点的值（value）、其所指向的下一个节点（next）、及指向它的上一个节点（pre）。
         */
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;

        return  newHead;
    }

}
