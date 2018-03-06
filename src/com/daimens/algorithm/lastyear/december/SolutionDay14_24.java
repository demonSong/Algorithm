package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 24.Swap Nodes in Pairs
 * Given a linked list, swap every two adjacent nodes and returns its head
 * 
 * For example,
 * Given 1->2->3->4,you should return the list as 2->1->4->3
 * 
 * You algorithm should use only constant space.You may not modify the values in the
 * list,only nodes itself can be changed.
 *
 */
public class SolutionDay14_24 {
	public ListNode swapPairs(ListNode head) {
		//偶数 奇数要考虑嘛？暂时不需要
		if(head ==null || head.next ==null) return head;
		
		ListNode temp = head.next;
		//递归思想从何而来，数学上能否推导得出
		head.next = swapPairs(temp.next);
		temp.next = head;
		return temp;
	}
}
