package com.ithawk.learn.middle;

public class SwapPairs {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    static class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode temp = head;
            ListNode prev = null;
            ListNode next;
            while (temp != null) {
                next = temp.next;
                if (next == null) {
                    break; //if odd no of nodes, then next would be null
                }
                if (prev != null) {
                    prev.next = next;
                }
                temp.next = next.next;
                next.next = temp;
                if (head == temp) {
                    head = next;
                }
                prev = temp;
                temp = temp.next;

            }
            return head;
        }
    }
}
