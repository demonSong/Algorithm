package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 124.Binary Tree Maximum Path Sum
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the
 * parent-child connections. The path must contain at least one node and dose not need to go through the root.
 * 
 * For example:
 * Given the below binary tree,
 *       1
 *      / \
 *     2   3
 * 
 * Return 6
 *
 */
public class SolutionDay29_124 {
	
	int maxValue = Integer.MIN_VALUE;
	
	public int maxPathSum(TreeNode root) {
		maxPathDown(root);
		return maxValue;
	}
	
	private int maxPathDown(TreeNode root){
		if (root == null) return 0;
		
		int left = Math.max(0, maxPathDown(root.left)); //这是向下走？
		int right = Math.max(0, maxPathDown(root.right));
		
		maxValue = Math.max(maxValue, left + right + root.val);
		return Math.max(left, right) + root.val;
	}
}
