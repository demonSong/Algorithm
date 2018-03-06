package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 86.Partition List
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 *
 */
public class SolutionDay20_086 {
	
	public ListNode partition(ListNode head, int x) {
		if(head == null) return null;
		
		ListNode first = new ListNode(-1);
		ListNode second = new ListNode(-1);
		
		ListNode curr1 = first;
		ListNode curr2 = second;
		
		while(head != null){
			if(head.val < x){
				curr1.next = head;
				curr1 = head;
			}else{
				curr2.next = head;
				curr2 = head;
			}
			head = head.next;
		}
		curr2.next = null;
		curr1.next = second.next;
		
		return first.next;
    }

}
