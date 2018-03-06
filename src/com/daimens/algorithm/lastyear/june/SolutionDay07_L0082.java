package com.daimens.algorithm.june;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 
 *         82. Remove Duplicates from Sorted List II
 * 
 *         Given a sorted linked list, delete all nodes that have duplicate
 *         numbers, leaving only distinct numbers from the original list.
 * 
 *         For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given
 *         1->1->1->2->3, return 2->3.
 * 
 *
 */
public class SolutionDay07_L0082 {
	
//	public ListNode deleteDuplicates(ListNode head) {
//		if (head == null) return head;
//		Map<Integer,Integer> map = new HashMap<>();
//		ListNode pos = head;
//		while (pos != null){
//			map.put(pos.val, map.getOrDefault(pos.val, 0) + 1);
//			pos = pos.next;
//		}
//		ListNode dummy = new ListNode(Integer.MAX_VALUE);
//		ListNode cur = dummy;
//		pos = head;
//		
//		while (pos != null){
//			int cnt = map.get(pos.val);
//			if (cnt >= 2){ //duplicate
//				cur.next = pos.next;
//				pos = pos.next;
//			}
//			else{
//				cur.next = pos;
//				pos = pos.next;
//				cur = cur.next;
//			}
//		}
//        return dummy.next;
//    }
	
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) return null;
		ListNode dummy = new ListNode(0);
		ListNode slow = dummy, fast = head;
		slow.next = fast;
		while (fast != null){
			while (fast.next != null && fast.val == fast.next.val) fast = fast.next;
			if (slow.next != fast){ //dup
				slow.next = fast.next;
				fast = fast.next;
			}else{
				slow = slow.next;
				fast = fast.next;
			}
		}
		return dummy.next;
	}

}
