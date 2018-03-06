package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Demon Song
 * 94.Binary Tree Inorder Traversal
 * Given a binary tree,return the inorder traversal of its nodes' values.
 * For example:
 * Given binary tree [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [1,3,2]
 *
 */
public class SolutionDay05_094 {
	
//	public List<Integer> inorderTraversal(TreeNode root) {
//		
//		List<Integer> list = new ArrayList<Integer>();
//		Stack<TreeNode> stack = new Stack<TreeNode>();
//		TreeNode cur = root;
//		while(cur !=null || !stack.empty()){
//			while(cur !=null){
//				stack.add(cur);
//				cur = cur.left;
//			}
//			cur = stack.pop();
//			list.add(cur.val);
//			cur = cur.right;
//		}
//        return list;
//    }
	
	public List<Integer> inorderTraversal(TreeNode root) {
		if(root == null) return new ArrayList<>();
		List<Integer> ans = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		
		TreeNode curr = root;
		
		while(curr != null || !stack.isEmpty()){
			
			if(curr != null){
				stack.push(curr);
				curr = curr.left;
			}else{
				TreeNode node = stack.pop();
				ans.add(node.val);
				curr = node.right;
			}
			
		}
		
		
		return ans;
	}
	
	
	
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x){
		val = x;
	}
}
