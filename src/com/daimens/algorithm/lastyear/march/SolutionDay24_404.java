package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 404.Sum of Left Leaves
 * Find the sum of all left leaves in a given binary tree.
 * Example:
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 * 
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 *
 */
public class SolutionDay24_404 {
	
	public int sumOfLeftLeaves(TreeNode root) {
		if(root == null) return 0;
		dfs(root);
		return sum;
    }
	
	int sum = 0;
	private void dfs(TreeNode root){
		
		
		if(root == null) return;
		
		if(root.left != null && root.left.left == null && root.left.right == null){
			sum += root.left.val;
		}
		
		dfs(root.left);
		dfs(root.right);
	}

}
