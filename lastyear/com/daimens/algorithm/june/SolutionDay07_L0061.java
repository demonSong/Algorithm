package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         61.Rotate List
 * 
 *         Given a list, rotate the list to the right by k places, where k is
 *         non-negative.
 * 
 *         For example: Given 1->2->3->4->5->NULL and k = 2, return
 *         4->5->1->2->3->NULL.
 *
 */
public class SolutionDay07_L0061 {
//	public ListNode rotateRight(ListNode head, int k) {
//		if (head == null || k == 0) return head;
//		
//		ListNode pos = head;
//		int cnt = 0;
//		while (pos != null){ 
//			cnt++;
//			pos = pos.next;
//		}
//		k = cnt < k ? cnt - k % cnt : k;
//		
//		ListNode n1 = reverse(head);
//		pos = n1;
//		for (int i = 0; i < k-1 && pos != null; ++i){
//			pos = pos.next;
//		}
//		if (pos == null) return n1;
//		ListNode n2 = pos.next;
//		pos.next = null;
//		n1 = reverse(n1);
//		n2 = reverse(n2);
//		
//		pos = n1;
//		while (pos != null && pos.next != null) pos = pos.next;
//		pos.next = n2;
//        return n1;
//    }
//	
//	private ListNode reverse(ListNode head) {
//		if (head == null)
//			return null;
//		ListNode newHead = null;
//		ListNode pos = head;
//		while (pos != null) {
//			ListNode next = pos.next;
//			pos.next = newHead;
//			newHead = pos;
//			pos = next;
//		}
//		return newHead;
//	}
	
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null) return head;
		int i = 1;
		ListNode fast = head, slow = head;
		while (fast != null && fast.next != null){
			fast = fast.next;
			i++;
		}
		
		for (int j = i - k % i; j > 1; --j){
			slow = slow.next;
		}
		
		ListNode dummy = new ListNode(0);
		fast.next = head;
		dummy.next = slow.next;
		slow.next = null;
		return dummy.next;
	}
	
	public static void main(String[] args) {
		SolutionDay07_L0061 day = new SolutionDay07_L0061();
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		day.rotateRight(head, 2);
	}
}
