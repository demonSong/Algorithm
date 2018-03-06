package com.daimens.algorithm.january;


/**
 * 
 * @author Demon Song
 * 19.Remove Nth Node From End of List
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 *
 */
public class SolutionDay06_019 {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if(n == 0)
			return head;
		int num =0;
		ListNode cur = head;
		while(cur !=null){
			cur = cur.next;
			num ++;
		}
		//删除头元素，属于特殊情况
		if(num == n){
			ListNode ret = head.next;
			return ret;
		}else{
			int m = num - n - 1;
			cur = head;
			while((m--)!=0){
				cur = cur.next;
			}
			cur.next = cur.next.next;
			return head;
		}
    }
}

class ListNode{
	int val;
	ListNode next;
	ListNode(int x){
		val = x;
	}
}