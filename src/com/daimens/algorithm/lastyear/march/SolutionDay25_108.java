package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 108.Convert Sorted Array to Binary Search Tree
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * 
 * 
 *
 */
public class SolutionDay25_108 {	
	
	public TreeNode sortedArrayToBST(int[] nums) {

		if (nums.length == 0) return null;

		return build(nums, 0, nums.length-1);
    }
	
	private TreeNode build(int[] nums, int lo, int hi){
		
		if(lo > hi)
			return null;
		
		int mid = lo + (hi - lo) / 2;
		
		TreeNode  root = new TreeNode(nums[mid]);
		
		root.left = build(nums, lo, mid-1);
		root.right = build(nums, mid+1, hi);
		
		return root;
	}

}
