package com.daimens.algorithm.december;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Demon Song
 * 107. Binary Tree Level Order Traversal 2
 * Given a binary tree,return the bottom-up level order traversal of
 * its nodes' values.(ie,from left to right,level by level from leaf to root).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7]
 *
 */
public class SolutionDay10_107 {
	public List<List<Integer>> levelOrderBottom(TreeNode root){
		List<List<Integer>> list = new LinkedList<>();
		if (root ==null) return list;
		
		//同二叉树的层次遍历
		Deque<TreeNode> cur = new LinkedList<TreeNode>();
		Deque<TreeNode> nxt = new LinkedList<TreeNode>();
		Deque<TreeNode> exc = new LinkedList<TreeNode>();
		
		TreeNode tmp;
		cur.addLast(root);
		while(!cur.isEmpty()){
			List<Integer> layout = new ArrayList<>();
			while(!cur.isEmpty()){
				tmp = cur.remove();
				if(tmp.left !=null){
					nxt.add(tmp.left);
				}
				if(tmp.right !=null){
					nxt.add(tmp.right);
				}
				layout.add(tmp.val);
			}
			exc = cur;
			cur = nxt;
			nxt = exc;
			list.add(0,layout);
		}
		
		return list;
	}
}

