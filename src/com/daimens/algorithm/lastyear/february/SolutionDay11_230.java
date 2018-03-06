package com.daimens.algorithm.february;

import java.util.Stack;

/**
 * 
 * @author Demon Song
 * 230.Kth Smallest Element in a BST
 * Given a binary search tree,write a function kthSmallest to find the kth smallest element in it.
 * 
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * 
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest 
 * frequently? How would you optimize the kthSmallest routine?
 * 
 * Hint:
 * 1. Try to utilize the property of a BST
 * 2. What if you could modify the BST node's structure?
 * 3. The optimal runtime complexity is O(heigh of BST).
 * 
 * 
 */
public class SolutionDay11_230 {
	
//	public int kthSmallest(TreeNode root, int k) {
//		//找最小的，然后删除结点，继续查找，直到找到第k个
//		for(int i = 1; i <= k; i++){
//			TreeNode cur = root;
//			TreeNode pre = cur;
//			while(cur != null){
//				while(cur.left != null){
//					pre = cur;
//					cur = cur.left;
//				}
//				
//				if(cur.right != null){
//					//删它干嘛
//					pre.left = cur.right;
//				}
//
//				if(k==i) return cur.val;
//				cur = null;
//			}
//		}
//        return 0;
//    }
	
	//in-order遍历
	public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> st = new Stack<>();
		while (root != null){
			st.push(root);
			root = root.left;
		}
		
		while(k != 0){
			TreeNode n = st.pop();
			k--;
			if(k == 0) return n.val;
			TreeNode right = n.right;
			while(right != null){
				st.push(right);
				right = right.left; //一些细节问题有待探究
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		SolutionDay11_230 day = new SolutionDay11_230();
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		
		day.kthSmallest(root, 2);
	}
	

}
