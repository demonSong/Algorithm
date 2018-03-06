package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/**
 * 
 * @author DemonSong
 * 145.Binary Tree Postorder Traversal
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree {1,#,2,3}
 *   1
 *    \
 *     2
 *    /
 *   3
 * return [3,2,1].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 *
 */
public class SolutionDay25_145 {
	
	//这三种方法还需要进一步理解！！！
	public List<Integer> postorderTraversal(TreeNode root) {
		if(root == null) return new ArrayList<>();
		
		LinkedList<Integer> ans = new LinkedList<>();
		iterTravel(root, ans);
		return ans;
		
    }
	
	private void iterTravel(TreeNode root, LinkedList<Integer> ans){
		
		Stack<TreeNode> stack = new Stack<>();
		
		TreeNode curr = root;
		
		while(!stack.isEmpty() || curr != null){
			
			if(curr != null){
				stack.push(curr);
				ans.addFirst(curr.val);
				curr = curr.right;
			}else{  //没有左就访问右
				TreeNode node = stack.pop();
				curr = node.left;
			}
		}
	}
	
	private void dfs(TreeNode root,List<Integer> ans){
		if(root == null)
			return;
		dfs(root.left, ans);
		dfs(root.right, ans);
		ans.add(root.val);
	}

}
