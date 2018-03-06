package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 109.Convert Sorted List to Binary Search Tree
 * Given a singly linked list where elements are sorted in ascending order ,convert it to a height balanced BST.
 *
 */
public class SolutionDay15_109 {
	
	public TreeNode sortedListToBST(ListNode head){
		if(head == null) return null;
		return toBST(head,null);
	}
	
	private TreeNode toBST(ListNode head, ListNode tail){
		ListNode slow = head;
		ListNode fast = head;
		
		if(head == tail) return null;
		
		while(fast != tail && fast.next != tail){
			fast = fast.next.next;
			slow = slow.next;
		}
		
		TreeNode thead = new TreeNode(slow.val);
		thead.left = toBST(head, slow);
		thead.right = toBST(slow.next,tail);
		
		return thead;
	}

}

class ListNode{
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
	}
}
