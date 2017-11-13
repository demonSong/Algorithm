package com.daimens.algorithm.november;

public class SolutionDay13_L0501 {
	
	
// 	public ListNode[] splitListToParts(ListNode root, int k) {
//		ListNode[] nodes = new ListNode[k];
//		for (int i = 0; i < k; ++i) {
//			nodes[i] = new ListNode(-1);
//		}
//		
//		ListNode cur = root;
//		if (cur == null) {
//			for (int i = 0; i < k; ++i) {
//				nodes[i] = nodes[i].next;
//			}
//			return nodes;
//		}
//		else {
//			ListNode[] curs = new ListNode[k];
//			for (int i = 0; i < k; ++i) {
//				curs[i] = nodes[i];
//			}
//	        
//	        int[] nums = new int[k];
//	        for (int size = 0; cur != null; cur = cur.next, size++) {
//	        	nums[size % k]++;
//	        }
//	        
//	        int j = 0;
//	        for (cur = root; cur != null; cur = cur.next){
//	        	curs[j].next = new ListNode(cur.val);
//                curs[j] = curs[j].next;
//	        	nums[j] --;
//	        	if (nums[j] == 0) {
//	        		j ++;
//	        	}
//	        }
//	        
//			for (int i = 0; i < k; ++i) {
//				nodes[i] = nodes[i].next;
//			}
//	        
//			return nodes;
//		}
//	}
	
	public ListNode[] splitListToParts(ListNode root, int k) {
		ListNode[] nodes = new ListNode[k];
		for (int i = 0; i < k; ++i) {
			nodes[i] = new ListNode(-1);
		}
		
        ListNode[] curs = new ListNode[k];
        for (int i = 0; i < k; ++i) {
            curs[i] = nodes[i];
        }

        int[] nums = new int[k];
        
        int size = 0;
        for (ListNode cur = root; cur != null; cur = cur.next, size++) {
            nums[size % k]++;
        }

        int j = 0;
        for (ListNode cur = root; cur != null; cur = cur.next){
            curs[j].next = new ListNode(cur.val);
            curs[j] = curs[j].next;
            nums[j] --;
            if (nums[j] == 0) {
                j ++;
            }
        }

        for (int i = 0; i < k; ++i) {
            nodes[i] = nodes[i].next;
        }

        return nodes;
	}
 	
 	public static void main(String[] args) {
 		SolutionDay13_L0501 day = new SolutionDay13_L0501();
 		ListNode root = new ListNode(1);
 		root.next = new ListNode(2);
 		root.next.next = new ListNode(3);
 		root.next.next.next = new ListNode(4);
 		System.out.println(day.splitListToParts(root, 5));
	}
	
}
