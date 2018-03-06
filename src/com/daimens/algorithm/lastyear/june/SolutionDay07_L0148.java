package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         148. Sort List
 * 
 *         Sort a linked list in O(n log n) time using constant space
 *         complexity.
 *
 */
public class SolutionDay07_L0148 {
	
//	public ListNode sortList(ListNode head) {
//		if (head == null || head.next == null) return head;
//		ListNode split1 = head;
//		ListNode split2 = head;
//		int cnt = 0;
//		ListNode pos = head;
//		while (pos != null){
//			cnt++;
//			pos = pos.next;
//		}
//		int mid = cnt / 2;
//		for (int i = 0; i < mid - 1; i++){
//			split2 = split2.next;
//		}
//		ListNode tmp = split2.next;
//		split2.next = null;
//		split2 = tmp;
//		ListNode sorted1 = sortList(split1);
//		ListNode sorted2 = sortList(split2);
//		
//		return merge(sorted1, sorted2);
//    }
	
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode slow = head;
		ListNode fast = head;
		ListNode prev = null;
		
		while (fast != null && fast.next != null){
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null;
		return merge(sortList(head), sortList(slow));
    }
	
	private ListNode merge(ListNode sorted1, ListNode sorted2){
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		while (sorted1 != null && sorted2 != null){
			if (sorted1.val < sorted2.val){ 
				curr.next = sorted1;
				sorted1 = sorted1.next;
			}else{
				curr.next = sorted2;
				sorted2 = sorted2.next;
			}
			curr = curr.next;
		}
		
		if (sorted1 != null){
			curr.next = sorted1;
		}
		
		if (sorted2 != null){
			curr.next = sorted2;
		}
		
		return dummy.next;
	}
	
	public static void main(String[] args) {
		SolutionDay07_L0148 day = new SolutionDay07_L0148();
		ListNode head = new ListNode(2);
		head.next = new ListNode(1);
		day.sortList(head);
	}

}
