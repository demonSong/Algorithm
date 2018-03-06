package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 103.Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order traversal of its node's values.(ie,from left to right,then right to left for
 * the next level and alternate between.)
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *    
 * return its zigzag level order traversal as:
 * [
 * 	[3],
 * 	[20,9],
 * 	[15,7]
 * ]
 *
 */
public class SolutionDay15_103 {
	
//	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//		if(root == null) return null;
//		
//		List<List<Integer>> ans = new ArrayList<>();
//		
//		Stack<TreeNode> stack = new Stack<>();
//		
//		List<Integer> tmp = new ArrayList<>();
//		stack.push(root);
//		tmp.add(root.val);
//		ans.add(tmp);
//		
//		while(!stack.isEmpty()){
//			
//			tmp.clear();
//			TreeNode node = stack.pop();
//			
//			if(node.left != null){
//				tmp.add(node.left.val);
//			}
//			
//			if(node.right != null){
//				tmp.add(node.right.val);
//			}
//			
//			if(!tmp.isEmpty()){
//				ans.add(tmp);
//			}
//		}
//		
//		
//		return null;
//    }
	
	public List<List<Integer>> zigzagLevelOrder(TreeNode root){
		List<List<Integer>> sol = new ArrayList<>();
        travel(root, sol, 0);
        return sol;
	}
	
	
	//pre-order 的变形，把 list 和 level 绑定在了一块，从而当我遍历到 对应level时，该思路是比较新的
	private void travel(TreeNode curr,List<List<Integer>> sol,int level){
		if(curr == null) return;
		
		if(sol.size() <= level){
			List<Integer> newLevel = new LinkedList<>();
			sol.add(newLevel);
		}
		
		List<Integer> collection = sol.get(level);
		if(level % 2 == 0) collection.add(curr.val);
		else{
			collection.add(0,curr.val);  //永远插入在头元素,那么就是逆序
		}
		
		travel(curr.left, sol, level+1);
		travel(curr.right, sol, level+1);
		
	}
}

