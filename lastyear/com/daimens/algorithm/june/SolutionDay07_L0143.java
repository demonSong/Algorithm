package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         143.Reorder List
 * 
 *         Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to:
 *         L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 *         You must do this in-place without altering the nodes' values.
 * 
 *         For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * 
 *
 */
public class SolutionDay07_L0143 {

	public void reorderList(ListNode head) {
		if (head == null || head.next == null)
			return;
		ListNode slow = head, fast = head;
		ListNode prev = null;
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		ListNode reverse = reverse(slow);
		merge(head, reverse);
	}

	private void merge(ListNode l1, ListNode l2) {
		while (l1 != null) {
			ListNode n1 = l1.next, n2 = l2.next;
			l1.next = l2;

			if (n1 == null)
				break;
			l2.next = n1;
			l1 = n1;
			l2 = n2;
		}
	}

	private ListNode reverse(ListNode head) {
		if (head == null)
			return null;
		ListNode newHead = null;
		ListNode pos = head;
		while (pos != null) {
			ListNode next = pos.next;
			pos.next = newHead;
			newHead = pos;
			pos = next;
		}
		return newHead;
	}

	public static void main(String[] args) {
		SolutionDay07_L0143 day = new SolutionDay07_L0143();
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		// head.next.next = new ListNode(3);
		day.reorderList(head);
	}
}
