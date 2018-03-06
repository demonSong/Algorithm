package com.daimens.algorithm.may;

import java.util.PriorityQueue;

/**
 * 
 * @author DemonSong
 * 
 *         23.Merge k Sorted Lists
 * 
 *         Merge k sorted linked lists and return it as one sorted list. Analyze
 *         and describe its complexity.
 *
 */
public class SolutionDay25_L0023 {
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0 || lists == null) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a,b) -> (a.val - b.val));
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (ListNode list : lists){
        	queue.offer(list);
        }
        while (!queue.isEmpty()){
        	tail.next = queue.poll();
        	tail = tail.next;
        	if (tail != null){
        		queue.offer(tail);
        	}
        }
		return dummy.next;
    }
}

class ListNode{
	int val;
	ListNode next;
	public ListNode(int x) {
		val = x;
	}
}