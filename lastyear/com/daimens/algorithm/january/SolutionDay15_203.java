package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 203.Remove Linked List Elements
 * Remove all elements from a linked list of integers that have value val.
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 *
 */
public class SolutionDay15_203 {
	
	public ListNode removeElements(ListNode head, int val) {
		//定义了一个无意义的头结点
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode p = dummy;
		ListNode q = head;
		
		while(q!=null){
			if(q.val == val){
				p.next = q.next;
			}else{
				p = p.next;
			}
			q = q.next;
		}
		
        return dummy.next;
    }
}
