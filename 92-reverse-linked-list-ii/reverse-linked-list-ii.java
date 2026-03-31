class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        if (left == right) return head;

        ListNode t = head;        // FIX: define t
        ListNode before = null;   // node before 'left'
        int pos = 1;

        // Step 1: move to position 'left'
        while (pos < left) {
            before = t;
            t = t.next;
            pos++;
        }

        // Step 2: reverse from left to right
        ListNode curr = t;
        ListNode prev = null;
        int times = right - left + 1;

        while (times-- > 0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Step 3: connect remaining parts
        t.next = curr;  // connect end of reversed part to remaining list

        if (before != null) {
            before.next = prev; // connect first part
            return head;
        } else {
            return prev; // when left == 1
        }
    }
}