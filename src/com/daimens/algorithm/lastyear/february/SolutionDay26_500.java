package com.daimens.algorithm.february;

import java.util.Arrays;
import java.util.Stack;

/**
 * 
 * @author DemonSong
 * 530.Minimum Absolute Difference in BST
 * Given a binary search tree with non-negative values,find the minimum absolute difference between values of any two nodes.
 * Example:
 * Input:
 *   1
 *    \
 *     3
 *    /
 *   2
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 * Note: There are at least two nodes in this BST.
 * 
 */
public class SolutionDay26_500 {
	
	private Stack<Integer> v = new Stack<>();

	public int getMinimumDifference(TreeNode root) {
		v.clear();
		dfs(root);
		Integer[] nums = new Integer[v.size()];
		v.toArray(nums);
		Arrays.sort(nums);
		int len = v.size();
		int ans = 0x7fffffff;
		for (int i = 1; i < len; i++) {
			ans = Math.min(ans, Math.abs(nums[i] - nums[i - 1]));
		}
		return ans;
	}

	private void dfs(TreeNode root) {
		if (root == null)
			return;
		v.push(root.val);
		dfs(root.left);
		dfs(root.right);
	}	
	
}
