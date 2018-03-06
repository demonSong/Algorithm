package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.scenario.effect.LinearConvolveCoreEffect;

import sun.launcher.resources.launcher_fr;

/**
 * 
 * @author DemonSong
 * 
 *         315.Count of Smaller Numbers After self
 * 
 *         You are given an integer array nums and you have to return a new
 *         counts array. The counts array has the property where counts[i] is
 *         the number of smaller elements to the right of nums[i].
 * 
 *         Example:
 * 
 *         Given nums = [5, 2, 6, 1]
 * 
 *         To the right of 5 there are 2 smaller elements (2 and 1). To the
 *         right of 2 there is only 1 smaller element (1). To the right of 6
 *         there is 1 smaller element (1). To the right of 1 there is 0 smaller
 *         element. Return the array [2, 1, 1, 0].
 *
 */
public class SolutionDay22_L0315 {
	
	// TLE
//	public List<Integer> countSmaller(int[] nums) {
//		List<Integer> count = new ArrayList<>();
//		for (int i = 0; i < nums.length; i++){
//			int cnt = 0;
//			for (int j = i + 1; j < nums.length; j++){
//				if (nums[j] < nums[i]) cnt ++;
//			}
//			count.add(cnt);
//		}
//		return count;
//    }
	
	
//	private class BinaryTree{
//		int val;
//		int index;
//		int layer;
//		
//		BinaryTree left;
//		BinaryTree right;
//		
//		public BinaryTree(int val, int index) {
//			this.val = val;
//			this.index = index;
//		}
//		
//		@Override
//		public String toString() {
//			return "["+val+","+index+"]";
//		}
//	}
//	
//	private BinaryTree insert(BinaryTree root, int val, int index, int layer){
//		if (root == null) {
//			root = new BinaryTree(val, index);
//			root.layer = layer;
//		}
//		else if (root.val > val){
//			root.left = insert(root.left, val, index, layer + 1);
//		}
//		else{
//			root.right = insert(root.right, val, index,layer);
//		}
//		return root;
//	}
//	
//	public List<Integer> countSmaller(int[] nums) {
//		int len = nums.length;
//		Integer[] ans = new Integer[len];
//		List<Integer> sorted = new ArrayList<>();
//		for (int i = len - 1; i >= 0; i--){
//			int index = findIndex(sorted, nums[i]);
//			index = index == -1 ? 0 : index;
//			ans[i] = index;
//			sorted.add(index,nums[i]);
//		}
//		return Arrays.asList(ans);
//	}
//	
//	private int findIndex(List<Integer> sorted, int target){
//		if (sorted.size() == 0) return 0;
//		int lf = 0, rt = sorted.size()-1;
//		while (lf < rt){
//			int mid = lf + (rt + 1 - lf) / 2;
//			if (sorted.get(mid) >= target){
//				rt = mid - 1;
//			}else{
//				lf = mid;
//			}
//		}
//		if (sorted.get(lf) < target) return lf+1;
//		return -1;
//	}
	
//	class BstTree{
//		BstTree left;
//		BstTree right;
//		int sum, val, dup = 1;
//		public BstTree(int sum, int val) {
//			this.sum = sum;
//			this.val = val;
//		}
//		
//		@Override
//		public String toString() {
//			return "BstTree [val=" + val + "]";
//		}
//	}
//	
//	private BstTree insert(int num, BstTree node, Integer[] ans, int i, int preSum){
//		if (node == null){
//			node = new BstTree(0,num);
//			ans[i] = preSum;
//		}else if (node.val == num){
//			node.dup++;
//			ans[i] = preSum + node.sum;
//		}else if (node.val > num){
//			node.sum++;
//			node.left = insert(num, node.left, ans, i, preSum);
//		}else{
//			node.right = insert(num, node.right, ans, i, preSum + node.sum + node.dup);
//		}
//		return node;
//	}
//	
//	public List<Integer> countSmaller(int[] nums) {
//		Integer[] ans = new Integer[nums.length];
//		BstTree root = null;
//		for (int i = nums.length - 1; i >= 0; --i){
//			root = insert(nums[i], root, ans, i, 0);
//		}
//		return Arrays.asList(ans);
//	}
	
	public List<Integer> countSmaller(int[] nums) {
		count = new int[nums.length];
		int[] indexes = new int[nums.length];
		for (int i = 0; i < nums.length; i++) indexes[i] = i;
		mergeSort(nums, indexes, 0, nums.length-1);
		
		List<Integer> ans = new ArrayList<>();
		for (int i : count) ans.add(i);
		return ans;
	}
	
	private void mergeSort(int[] nums, int[] indexes, int lo, int hi){
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		mergeSort(nums, indexes, lo, mid);
		mergeSort(nums, indexes, mid+1, hi);
		
		merge(nums, indexes, lo, hi);
	}
	
	int[] count;
	private void merge(int[] nums, int[] indexes, int lo, int hi){
		int mid = lo + (hi - lo) / 2;  
		int lf = lo;
		int rt = mid + 1;
		
		int[] aux = new int[hi - lo + 1];
		
		int idx = 0;
		int cnt = 0;
		while (lf <= mid && rt <= hi){
			int a1 = nums[indexes[lf]];
			int a2 = nums[indexes[rt]];
			if (a2 < a1){
				aux[idx++] = indexes[rt++];
				cnt++;
			}else{
				aux[idx++] = indexes[lf];
				count[indexes[lf]] += cnt;
				lf++;
			}
		}
		
		while (lf <= mid){
			aux[idx++] = indexes[lf];
			count[indexes[lf]] += cnt;
			lf++;
		}
		
		while (rt <= hi){
			aux[idx++] = indexes[rt++];
		}
		
		for (int i = lo; i <= hi; i++){
			indexes[i] = aux[i-lo];
		}
	}
	
//	public List<Integer> countSmaller(int[] nums) {
//		int len = nums.length;
//		Integer[] ans = new Integer[len];
//		List<Integer> sorted = new ArrayList<>();
//		
//		for (int i = len - 1; i >= 0; i--){
//			int index = findIndex(sorted, nums[i]);
//			index = index == -1 ? 0 : index;
//			ans[i] = index;
//			sorted.add(index,nums[i]);
//		}
//		return Arrays.asList(ans);
//	}
//	
//	private int findIndex(List<Integer> sorted, int target){
//		if (sorted.size() == 0) return 0;
//		int lf = 0, rt = sorted.size()-1;
//		while (lf < rt){
//			int mid = lf + (rt + 1 - lf) / 2;
//			if (sorted.get(mid) >= target){
//				rt = mid - 1;
//			}else{
//				lf = mid;
//			}
//		}
//		if (sorted.get(lf) < target) return lf+1;
//		return -1;
//	}
	
	
	
	public static void main(String[] args) {
		SolutionDay22_L0315 day = new SolutionDay22_L0315();
//		int[] nums = {5,2,6,1};
		int[] nums = {6,4,1,8,7,5,2,9};
		day.countSmaller(nums);
	}
	
}
