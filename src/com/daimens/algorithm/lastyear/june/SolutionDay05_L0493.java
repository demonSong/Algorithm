package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         493. Reverse Pairs
 * 
 *         Given an array nums, we call (i, j) an important reverse pair if i <
 *         j and nums[i] > 2*nums[j].
 * 
 *         You need to return the number of important reverse pairs in the given
 *         array.
 * 
 *         Example1:
 * 
 *         Input: [1,3,2,3,1] Output: 2 Example2:
 * 
 *         Input: [2,4,3,5,1] Output: 3 Note: The length of the given array will
 *         not exceed 50,000. All the numbers in the input array are in the
 *         range of 32-bit integer.
 *
 */
public class SolutionDay05_L0493 {
	
	class BstNode{
		long val;
		int dup;
		int cnt;
		
		BstNode left;
		BstNode right;
		public BstNode(long val) {
			this.val = val;
			dup = 1;
		}
	}
	
	private BstNode insert(BstNode root, long val){
		if (root == null){
			return new BstNode(val);
		}
		else if (root.val == val){
			root.dup++;
		}
		else if (root.val < val){
			root.right = insert(root.right, val);
		}
		else{
			root.cnt++;
			root.left = insert(root.left, val);
		}
		return root;
	}
	
	private int getBound(BstNode root, double val){
		if (root == null) return 0;
		if (root.val == val){
			return root.cnt;
		}
		else if (root.val > val){
			return getBound(root.left, val);
		}
		else{
			return root.cnt + root.dup + getBound(root.right, val);
		}
	}
	
//	public int reversePairs(int[] nums) {
//		if (nums == null || nums.length <= 1) return 0;
//		int n = nums.length;
//        BstNode root = null;
//        int ans = 0;
//        for (int i = n - 1; i >= 0; --i){
//        	ans += getBound(root, nums[i] / 2);
//        	root = insert(root, nums[i]);
//        }
//        return ans;
//    }
	
	public int reversePairs(int[] nums) {
		return mergeSort(nums, 0, nums.length-1);
	}
	
	private int mergeSort(int[] nums, int s, int e){
		if (s >= e) return 0;
		int mid = s + (e - s) / 2;
		int cnt = mergeSort(nums, s, mid) + mergeSort(nums, mid + 1, e);
		for (int i = s, j = mid + 1; i <= mid; ++i) {
			while (j <= e && nums[i] / 2.0 > nums[j]) j++;
			cnt += j - (mid + 1);
		}
		merge(nums, s, e);
		return cnt;
	}
	
	private void merge(int[] nums, int s, int e){
		int[] aux = new int[e - s + 1];
		int mid = s + (e - s) / 2;
		
		int i = s;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= e){
			if (nums[i] < nums[j]) aux[k++] = nums[i++];
			else aux[k++] = nums[j++];
		}
		
		while (i <= mid){
			aux[k++] = nums[i++];
		}
		
		while (j <= e){
			aux[k++] = nums[j++];
		}
		
		for (int l = 0; l < aux.length; ++l){
			nums[l + s] = aux[l];
		}
	}
	
	public static void main(String[] args) {
		SolutionDay05_L0493 day = new SolutionDay05_L0493();
		int[] nums = {1,3,4,1,3};
		day.merge(nums, 0, nums.length-1);
		day.reversePairs(nums);
	}

}
