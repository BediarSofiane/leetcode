
//Definition for singly-linked list.
public class ListNode {
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

class Solution {
    // Approach: Fast slow two pointers
    // Intuition: Find the middle node, reverse the list between the middle and
    // insert each element of the inverted second part between the nodes of the
    // first part.
    public void reorderList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode previous = null;
        if (head.next == null) {
            return;
        }
        // Find the middle.
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            previous = slow;
            slow = slow.next;
        }
        // The middle is in slow pointer after the while loop ends.
        previous.next = null;
        ListNode middle = slow;
        previous = null;
        ListNode tmp = null;
        ListNode current = middle;
        while (current != null) {
            tmp = current.next;
            current.next = previous;
            previous = current;
            current = tmp;
        }
        // The head of reversed list is in previous.
        ListNode current1 = head;
        ListNode current2 = previous;
        // merge the two parts
        while (current1 != null && current2 != null) {
            ListNode tmp1 = current1.next;
            ListNode tmp2 = current2.next;
            current1.next = current2;
            if (tmp1 != null) {
                current2.next = tmp1;
            }
            current2 = tmp2;
            current1 = tmp1;
        }
    }
}