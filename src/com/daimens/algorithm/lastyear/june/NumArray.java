package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         307. Range Sum Query - Mutable
 * 
 *         Given an integer array nums, find the sum of the elements between
 *         indices i and j (i ≤ j), inclusive.
 * 
 *         The update(i, val) function modifies nums by updating the element at
 *         index i to val. Example: Given nums = [1, 3, 5]
 * 
 *         sumRange(0, 2) -> 9 update(1, 2) sumRange(0, 2) -> 8 Note: The array
 *         is only modifiable by the update function. You may assume the number
 *         of calls to update and sumRange function is distributed evenly.
 *
 */
//public class NumArray {
//	
//	int[] nums;
//	public NumArray(int[] nums) {
//        this.nums = nums;
//        sum();
//    }
//    
//	
//    public void update(int i, int val) {
//        nums[i] = val;
//        sum();
//    }
//    
//    int[] sums;
//    private void sum(){
//    	sums = new int[nums.length + 1];
//        for (int j = 0; j < nums.length; ++j){
//        	sums[j+1] = sums[j] + nums[j];
//        }
//    }
//    
//    public int sumRange(int i, int j) {
//        return sums[j+1] - sums[i];
//    }
//
//}

public class NumArray {
	
	class SegmentTreeNode{
		int start, end;
		SegmentTreeNode left, right;
		int sum;
		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			sum = 0;
		}
		
		@Override
		public String toString() {
			return "SegmentTreeNode [start=" + start + ", end=" + end + "]";
		}
	}
	
	SegmentTreeNode root = null;
	
	private SegmentTreeNode build(int[] nums, int start, int end){
		if (end < start) return null;
		SegmentTreeNode ret = new SegmentTreeNode(start, end);
		if (start == end) ret.sum = nums[start];
		else{
			int mid = start + (end - start) / 2;
			ret.left = build(nums, start, mid);
			ret.right = build(nums, mid + 1, end);
			ret.sum = ret.left.sum + ret.right.sum;
		}
		return ret;
	}
	
	private void update(SegmentTreeNode root, int pos, int val){
		if (root.start == root.end){
			root.sum = val;
			return;
		}
		int mid = root.start + (root.end - root.start) / 2;
		if (pos <= mid) update(root.left, pos, val);
		else update(root.right, pos, val);
		
		root.sum = root.left.sum + root.right.sum;
	}
	
	private int sumRange(SegmentTreeNode root, int start, int end){
		if (root.start == start && root.end == end) return root.sum;
		else{
			int mid = root.start + (root.end - root.start) / 2;
			if (end <= mid){
				return sumRange(root.left, start, end);
			}else if (start >= mid + 1){
				return sumRange(root.right, start, end);
			}
			else{
				return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
			}
		}
	}
	
	
	public NumArray(int[] nums) {
		root = build(nums, 0, nums.length-1);
    }
    
	
    public void update(int i, int val) {
    	update(root, i, val);
    }
    
    public int sumRange(int i, int j) {
    	 return sumRange(root, i, j);
    }

}
