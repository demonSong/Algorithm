package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 147.Insertion Sort List
 * Sort a linked list using insertion sort.
 *
 */
public class SolutionDay18_147 {
	
//	public ListNode insertionSortList(ListNode head) {
//		
//		if(head == null) return null;
//		
//		//循环 找最小元素
//		
//		
//		for (ListNode curr = head; curr != null; curr = curr.next) {
//			int min = head.val;
//			
//			ListNode minNode = head;
//			ListNode preNode = head;
//			
//			ListNode curNode = curr;
//			
//			while(curNode.next != null){  //遍历到链表最后一个点
//				if(curNode.val < min){
//					min = curNode.val;
//					minNode = curNode;
//					
//				}
//				
//				preNode = curNode;
//				curNode = curNode.next;
//			}
//			
//			
//			
//			//插入
//			
//		}
//		
//		return null;
//    }
	
	public ListNode insertionSortList(ListNode head) {
		
		if(head == null){
			return head;
		}
		
		ListNode helper = new ListNode(0);
		
		ListNode cur = head;
		ListNode pre = helper;
		ListNode nxt = null;
		
		while(cur != null){
			nxt = cur.next;
			
			//find the right place to insert
			while(pre.next != null && pre.next.val < cur.val){ //这里的情况可以好好研究下，因为它是比较 pre.next 和当前cur的值，也可以找cur后面的元素吧!
				pre = pre.next;
			}
			
			//insert between pre and pre.next
			cur.next = pre.next;
			pre.next = cur;
			pre = helper;
			cur = nxt;
		}
		
		return helper.next;
	}

}
