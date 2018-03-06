package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 102.Binary Tree Level Order Traversal
 * Given a binary tree,return the level order traversal of its nodes' values.
 * (ie,from left to right,level by level).
 * For examples:
 * Given binary tree[3,9,20,null,15,7]
 * return its level order traversal as:
 * [[3],[9,20],[15,7]]
 *
 */

public class SolutionDay18_102 {
	public List<List<Integer>> levelOrder(TreeNode root) {
		//queue
		
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root ==null) return res;
		Deque<TreeNode> queue =new  LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()){
			List<Integer> tmp = new ArrayList<Integer>();
			for (TreeNode child : queue){
				tmp.add(child.val);
			}
			res.add(tmp);
			Deque<TreeNode> queue2 = queue;
			queue = new LinkedList<TreeNode>();
			for(TreeNode child : queue2){
				if(child.left !=null){
					queue.add(child.left);
				}
				if(child.right !=null){
					queue.add(child.right);
				}
			}
		}
        return res;
    }
}
