package com.daimens.algorithm.april;

import sun.launcher.resources.launcher;

/**
 * 
 * @author DemonSong
 * 92.Reverse Linked List II
 * Reverse a linked list from position m to n. Do It in-place and in one-pass.
 * 
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 * 
 */
public class SolutionDay02_092 {
	
	public ListNode reverseBetween(ListNode head, int m, int n) {
		
		if (head == null) return head;
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode pre = dummy;
		for (int i = 0; i < m-1; i++) pre = pre.next;
		
		ListNode start = pre.next;
		ListNode then = start.next;
		
		for (int i = 0; i < n-m; i++){
			start.next = then.next;
			then.next = pre.next;
			pre.next = then;
			then = start.next;
		}
		
        return dummy.next;
    }
	
	public static void main(String[] args) {
		SolutionDay02_092 day = new SolutionDay02_092();
		ListNode head = new ListNode(3);
		head.next = new ListNode(5);
		head.next.next = new ListNode(7);
		
		day.reverseBetween(head, 1, 2);
		
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
		return "["+val+"]";
	}
}

class TreeNode{
	
	int val;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int val){
		this.val = val;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+val+"]";
	}
}