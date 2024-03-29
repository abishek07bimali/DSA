
//Given a linked list containing an integer value, return the number of steps required to sort an array in
//        ascending order by eliminating elements at each step


// A class to sort a linked list and count the number of steps required to sort it.

 public class Question4B {

// A nested class to represent the nodes of the linked list.
static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}



    public int sortList(ListNode head) {
        if (head == null || head.next == null)
            return 0;

        int count = 0;
        ListNode current = head;
        while (current.next != null) {
            if (current.val > current.next.val) {
                current.next = current.next.next;
                count++;
            } else {
                current = current.next;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Question4B obj = new Question4B();
        ListNode head = new ListNode(7);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next = new ListNode(3);

        System.out.println("Number of steps required to sort the linked list: " + obj.sortList(head));
    }
}








