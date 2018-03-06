package com.daimens.algorithm.june;

import java.util.LinkedHashSet;

import javax.security.auth.login.FailedLoginException;
import javax.swing.text.AbstractDocument.LeafElement;

/**
 * 
 * @author DemonSong
 * 
 *         2.Add Two Numbers
 * 
 *         You are given two non-empty linked lists representing two
 *         non-negative integers. The digits are stored in reverse order and
 *         each of their nodes contain a single digit. Add the two numbers and
 *         return it as a linked list.
 * 
 *         You may assume the two numbers do not contain any leading zero,
 *         except the number 0 itself.
 * 
 *         Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
 *
 */
public class SolutionDay07_L0002 {
//	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//		ListNode dummy = new ListNode(0);
//		ListNode cur = dummy;
//		
//		int carry = 0;
//		while (l1 != null && l2 != null){
//			int a1 = l1.val;
//			int a2 = l2.val;
//			int a3 = a1 + a2 + carry;
//			carry = a3 / 10;
//			ListNode node = new ListNode(a3 % 10);
//			cur.next = node;
//			cur = cur.next;
//			l1 = l1.next;
//			l2 = l2.next;
//		}
//		
//		if (l1 != null){
//			cur.next = plus(carry, l1);
//		}
//		
//		if (l2 != null){
//			cur.next = plus(carry, l2);
//		}
//		
//		if (l1 == null && l2 == null && carry == 1){
//			cur.next = new ListNode(1);
//		}
//		
//        return dummy.next;
//    }
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		
		int carry = 0;
		while (l1 != null || l2 != null){
			int a1 = l1 != null ? l1.val : 0;
			int a2 = l2 != null ? l2.val : 0;
			int a3 = a1 + a2 + carry;
			carry = a3 / 10;
			ListNode node = new ListNode(a3 % 10);
			cur.next = node;
			cur = cur.next;
			if (l1 != null) l1 = l1.next;
			if (l2 != null) l2 = l2.next;
		}
		
		if (carry == 1) cur.next = new ListNode(1);
		
		return dummy.next;
	}
	
	private ListNode plus(int val,ListNode l1){
		if (l1 == null){
			return new ListNode(val);
		}
		if (l1.val + val != 10){
			l1.val = l1.val + val;
			return l1;
		}else{
			ListNode ans = new ListNode(0);
			ans.next = plus(1, l1.next);
			return ans;
		}
	}
	
	
	public static void main(String[] args) {
		SolutionDay07_L0002 day = new SolutionDay07_L0002();
	}
}
