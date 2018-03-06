package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 21.Merge Two Sorted Lists
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class SolutionDay9_21 {
	
	//是否可以总结出ListNode遍历和数组遍历的一般通式
	public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if(l1 ==null && l2 ==null) return null;
        if(l1 ==null && l2 !=null) return l2;
        if(l1 !=null && l2 ==null) return l1;
        
        
		ListNode result;
		if (l2.val < l1.val){
			result = l2;
			l2 = l2.next;
		}else{
		    result = l1;
		    l1 = l1.next;
		}
		
		ListNode p =result;
		while(l1 !=null && l2!=null){
			if (l1.val <= l2.val){
				p.next = l1;
				p = p.next;
				l1 = l1.next;
			}else{
				p.next = l2;
				p = p.next;
				l2 =l2.next;
			}
		}
		//为什么不直接链接呢？
//		while(l2 !=null){
//			p.next = l2;
//			p = p.next;
//			l2 = l2.next;
//		}
//	
//		
//		
//		while(l1 !=null){
//			p.next = l1;
//			p = p.next;
//			l1 = l1.next;
//		}
		
		if (l1 !=null){
		    p.next = l1;
		}
		if (l2 !=null){
		   p.next =l2; 
		}

		
		
		
		return result;
	}
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		ListNode l2 = new ListNode(1);
		SolutionDay9_21 day9_21 = new SolutionDay9_21();
		day9_21.mergeTwoLists(l1, l2);
		
	}
}


