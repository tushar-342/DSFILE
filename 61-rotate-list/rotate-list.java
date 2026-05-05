class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        // Step 1: Find length and last node
        ListNode last = head;
        int n = 1;
        while (last.next != null) {
            n++;
            last = last.next;
        }

        // Step 2: Reduce k
        k = k % n;
        if (k == 0) return head;

        // Step 3: Find new tail (n-k position)
        ListNode t = head;
        for (int i = 1; i < n - k; i++) {
            t = t.next;
        }

        // Step 4: Rotate
        last.next = head;     // make circular
        ListNode res = t.next; // new head
        t.next = null;         // break link

        return res;
    }
}