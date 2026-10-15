
//Definition for singly-linked list.
class ListNode {
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

class L19RemoveNthNodeFromEndOfList {
    // Approach: Fast & Slow pointers with range n
    // Intuition: Go forward n times with the first pointer, when the first pointer
    // reaches the nth node, put a second pointer at head and a third ponter that
    // tracks the pervious of the second pointer, move both pointers together until
    // the first pointer reaches null, the second pointer is at the nth node from
    // the end, it is the one to remove, and the previous tracker helps removing it.
    // Time: O(n);
    // Space: O(1);
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        ListNode slow = head;
        ListNode previous = null;
        while (fast != null) {
            fast = fast.next;
            previous = slow;
            slow = slow.next;
        }
        if (previous != null) {
            previous.next = slow.next;
            slow.next = null;
        } else {
            ListNode tmp = head.next;
            head.next = null;
            head = tmp;
        }
        return head;
    }
}