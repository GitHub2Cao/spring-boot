package com.softnovo.algorithm.test;

public class Solution {
    public static void main(String[] args) {
        //[1,2,6,3,4,5,6]
//        ListNode listNode6 = new ListNode(6, null);
//        ListNode listNode5 = new ListNode(5, listNode6);
//        ListNode listNode4 = new ListNode(4, listNode5);
//        ListNode listNode3 = new ListNode(3, listNode4);
//        ListNode listNode66 = new ListNode(6, listNode3);
//        ListNode listNode2 = new ListNode(2, listNode66);
//        ListNode listNode1 = new ListNode(1, listNode2);

        ListNode listNode7 = new ListNode(7, null);
        ListNode listNode77 = new ListNode(7, listNode7);
        ListNode listNode777 = new ListNode(7, listNode77);
        ListNode listNode7777 = new ListNode(7, listNode777);

        System.out.println(listNode7777);
        ListNode newListNode = removeElements(listNode7777, 7);
        System.out.println(newListNode);
    }

    /**
     * 203
     * https://leetcode.cn/problems/remove-linked-list-elements/description/
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode sentinal = new ListNode();
        sentinal.next = head;


        ListNode current = sentinal.next;
        ListNode pre = sentinal;
        while (current != null) {
            if (current.val == val) {
                pre.next = current.next;
                current = current.next;
            } else {
                pre = current;
                current = current.next;
            }
        }
        return sentinal.next;
    }
}
