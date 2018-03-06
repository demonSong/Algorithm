package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 * 
 *         206. Reverse Linked List
 *         
 *         Reverse a singly linked list.
 *
 * 
 */
public class SolutionDay07_L0206 {
	
// 	public ListNode reverseList(ListNode head) {
// 		if (head == null) return null;
// 		ListNode pos = head;
// 		head = null;
// 		while (pos != null){
// 			ListNode tmp = pos.next;
// 			pos.next = head;
// 			head = pos;
// 			pos = tmp;
// 		}
//        return head;
//    }
	
//	public ListNode reverseList(ListNode head){
//		if (head == null) return null;
//		ListNode newHead = null;
//		while (head != null){
//			ListNode next = head.next;
//			head.next = newHead;
//			newHead = head;
//			head = next;
//		}
//		return newHead;
//	}
	
	public ListNode reverseList(ListNode head){
		return recurrsive(head, null);
	}
	
	private ListNode recurrsive(ListNode head, ListNode newHead){
		if (head == null) return newHead;
		ListNode next = head.next;
		head.next = newHead;
		return recurrsive(next, head);
	}
	
	
 	
 	public static void main(String[] args) {
 		SolutionDay07_L0206 day = new SolutionDay07_L0206();
 		ListNode head = new ListNode(1);
 		head.next = new ListNode(2);
 		head.next.next = new ListNode(3);
 		day.reverseList(head);
 		System.out.println();
	}

}

class ListNode{
	int val;
	ListNode next;
	public ListNode(int x) {
		val = x;
	}
	@Override
	public String toString() {
		return "ListNode [val=" + val + "]";
	}
}