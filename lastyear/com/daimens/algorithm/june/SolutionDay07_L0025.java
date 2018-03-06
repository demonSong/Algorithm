package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         25. Reverse Nodes in k-Group
 * 
 *         Given a linked list, reverse the nodes of a linked list k at a time
 *         and return its modified list.
 * 
 *         k is a positive integer and is less than or equal to the length of
 *         the linked list. If the number of nodes is not a multiple of k then
 *         left-out nodes in the end should remain as it is.
 * 
 *         You may not alter the values in the nodes, only nodes itself may be
 *         changed.
 * 
 *         Only constant memory is allowed.
 * 
 *         For example, Given this linked list: 1->2->3->4->5
 * 
 *         For k = 2, you should return: 2->1->4->3->5
 * 
 *         For k = 3, you should return: 3->2->1->4->5
 *
 */
public class SolutionDay07_L0025 {
	
	public ListNode reverseKGroup(ListNode head, int k) {
		return recurrsive(head, k);
    }
	
	private ListNode recurrsive(ListNode head, int k) {
		ListNode last = head;
		int count = 0;
		while (last != null && count != k){
			last = last.next;
			count++;
		}
		if (count == k){
			ListNode newHead = recurrsive(last, k);
			for (int i = 0; i < k; i++){
				ListNode next = head.next;
				head.next = newHead;
				newHead = head;
				head = next;
			}
			head = newHead;
		}
		return head;
	}
	
//	private ListNode recurrsive(ListNode head, int k) {
//	if (head == null) return null;
//	int cnt = 0;
//	for (ListNode i = head; i != null; i = i.next){
//		cnt++;
//	}
//	if (cnt < k) return head;
//	
//	ListNode last = head;
//	for (int i = 0; i < k; i++){
//		last = last.next;
//	}
//	
//	// reverse k
//	ListNode newHead = recurrsive(last, k);
//	
//	for (int i = 0; i < k; i++){
//		ListNode next = head.next;
//		head.next = newHead;
//		newHead = head;
//		head = next;
//	}
//	
//	return newHead;
//}
	
	public static void main(String[] args) {
		SolutionDay07_L0025 day = new SolutionDay07_L0025();
		ListNode head = new ListNode(1);
 		head.next = new ListNode(2);
 		head.next.next = new ListNode(3);
 		day.reverseKGroup(head, 3);
 		System.out.println();
	}

}
