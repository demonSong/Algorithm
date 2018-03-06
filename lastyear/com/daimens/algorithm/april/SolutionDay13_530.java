package com.daimens.algorithm.april;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 530.Minimum Absolute Difference in BST
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 * Example:
 *   1
 *    \
 *     3
 *    /
 *   2
 *
 * Output:
 * 1
 * 
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 * Note: There are at least two nodes in this BST.
 *
 */
public class SolutionDay13_530 {
	
	public int getMinimumDifference(TreeNode root) {
		if(root == null) return 0;
		dfs(root);

		Integer[] array = ans.toArray(new Integer[0]);
		Arrays.sort(array);
		
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < array.length; i++){
			min = Math.min(min, Math.abs(array[i]-array[i-1]));
		}

		return min;
    }
	
	List<Integer> ans = new ArrayList<>();
	private void dfs(TreeNode root){
		if (root == null) return;
		ans.add(root.val);
		dfs(root.left);
		dfs(root.right);
	}

}
