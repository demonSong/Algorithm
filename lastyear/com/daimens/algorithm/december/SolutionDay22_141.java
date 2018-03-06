package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 141.Linked List Cycle
 * Given a linked list,determine if it has a cycle in it.
 *
 */
public class SolutionDay22_141 {
	
	//如何检查自环,加入hash表能够解决问题
	//快慢引用思路
	public boolean hasCycle(ListNode head){
		if(head ==null) return false;

		ListNode slow = head;
		ListNode fast = head;
		while(fast.next !=null && fast.next.next !=null){
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast) return true;
			
		}
		return false;
	}

}
