package com.daimens.algorithm.april;

import java.util.HashSet;
import java.util.Set;

public class SolutionDay16_L0817 {
	
    public int numComponents(ListNode head, int[] G) {
    	Set<Integer> seen = new HashSet<>();
    	for (int num : G) seen.add(num);
    	
    	int cnt = 0;
    	int rep = 0;
    	for (ListNode cur = head; cur != null; cur = cur.next) {
    		int val = cur.val;
    		if (seen.contains(val)) {
    			if (rep == 0) cnt ++;
    			rep ++;
    		}
    		else {
    			rep = 0;
    		}
    	}
    	
    	return cnt;
    }
    
	public static void main(String[] args) {
		SolutionDay16_L0817 day = new SolutionDay16_L0817();
		ListNode head = new ListNode(0);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next = new ListNode(3);
		int[] G = {0, 1, 3};
		System.out.println(day.numComponents(head, G));
	}
}
