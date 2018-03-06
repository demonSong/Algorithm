package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 234.Palindrome Linked List
 * Given a singly linked list,determine if it is a palindrome.
 *
 */
public class SolutionDay10_234 {
	//反转链表法，将链表后半段原地翻转，再将前半段、后半段依次比较，判断是否相等，时间复杂度O（n），空间复杂度为O（1）
	public boolean isPalindrome(ListNode head) {
		if(head == null || head.next == null){
			return true;
		}
		
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast.next != null && fast.next.next !=null){
			fast = fast.next.next;
			slow = slow.next;
		}
		
		ListNode secondHead = slow.next;
		slow.next = null;
		
		//reverse second part of the list
		ListNode p1 = secondHead;
		ListNode p2 = p1.next;
		
		while(p1!=null && p2!=null){
			ListNode temp = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = temp;
		}
		
		secondHead.next = null;
		
		//compare two sublists now
		ListNode p = (p2 ==null ? p1:p2); //related with reverse operation
		ListNode q = head;
		while(p!=null){
			if(p.val != q.val){
				return false;
			}
			
			p = p.next;
			q = q.next;
		}
		
		return true;
		
    }
	
}
