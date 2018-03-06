package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 142.Linked List Cycle II
 * Given a linked list,return the node where the cycle begins. If there is no cycle, return null.
 * Node: Do not modify the linked list.
 *
 */
public class SolutionDay30_142 {

	public ListNode detectCycle(ListNode head) {
		
		if(head == null) return null;
		
		ListNode fast = head;
		ListNode slow = head;
		
		boolean isCycle = false;
		
		while(fast.next != null && fast.next.next != null){
			fast = fast.next.next;
			slow = slow.next;
			
			if(fast == slow){ 
				isCycle = true;
				break;
			}
		}
		
		if (!isCycle) return null;
		
		slow = head;
		while(slow != fast){
			slow = slow.next;
			fast = fast.next;
		}
		
		return slow;
    }
}
