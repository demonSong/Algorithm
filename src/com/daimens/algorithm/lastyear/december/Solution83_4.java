package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 83.Remove Duplicates from Sorted List
 * Given a sorted linked list,delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2,return 1->2.
 * Given 1->1->2->3->3,return 1->2->3
 *
 */
public class Solution83_4 {
	public ListNode deleteDuplicates(ListNode head){
		if (head == null || head.next == null){
			return head;
		}
		
		ListNode p = head;//前指针
		ListNode q = p.next;//后指针
		
		while (p !=null && q!=null){
			if(p.val == q.val){ //元素相等
				p.next = q.next;
				q = q.next;
			}else{
				p = p.next;
				q = q.next;
			}
		}
		return head;
	}
}

class ListNode{
	int val;
	ListNode next;
	ListNode(int x){
		val =x;
	}
}