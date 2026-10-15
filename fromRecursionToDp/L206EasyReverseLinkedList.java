package fromRecursionToDp;

public class L206EasyReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        //return reverseListRecursive(head);
        return reverseListLinear(head);
    }
    
    private ListNode reverseListLinear(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;

        }
        return prev;
    }
    
    //Recursive contract: This function, called with head, attaches the tail of the list starting from head.next to the head, assuming that the reversion of that sub list is correct.
    private ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        
    }
}



class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
