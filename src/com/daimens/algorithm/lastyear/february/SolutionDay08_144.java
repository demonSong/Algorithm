package com.daimens.algorithm.february;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Demon Song
 * 144.Binary Tree Preorder Traversal
 * Given a binary tree,return the preorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3}
 *  1
 *   \
 *    2
 *   /
 *  3
 * return [1,2,3]
 * Note recursive solution is trivial,could you do it iteratively?
 *
 */
public class SolutionDay08_144 {
	
//	public List<Integer> preorderTraversal(TreeNode root){
//		List<Integer> list = new ArrayList<Integer>();
//		Stack<TreeNode> stack = new Stack<TreeNode>();
//		TreeNode cur = root;
//		while(cur != null){
//			list.add(cur.val);
//			if(cur.right !=null){
//				stack.push(cur.right);
//			}
//			
//			cur = cur.left;
//			if(cur == null && !stack.empty()){
//				cur = stack.pop();
//			}
//		}
//		return list;
//	}
	
	public List<Integer> preorderTraversal(TreeNode root){
		
		if(root == null) return new ArrayList<>();
		
		Stack<TreeNode> stack = new Stack<>();
		List<Integer> ans = new ArrayList<>();
		
		TreeNode curr = root;

		while(!stack.isEmpty() || curr != null){

			if(curr != null){ //curr 还可以用来检测当前curr节点是否含有左节点和右节点
			    stack.push(curr);
				ans.add(curr.val);
				curr = curr.left;
				
			}else{
				TreeNode node = stack.pop();
				curr = node.right; 
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay08_144 day = new SolutionDay08_144();
		day.preorderTraversal(null);
	}

}
