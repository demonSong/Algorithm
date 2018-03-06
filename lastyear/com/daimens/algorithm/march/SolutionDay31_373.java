package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * @author DemonSong
 * 373.Find K pairs with Smallest Sums
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * 
 * Example 1:
 * Given nums1 = [1,7,11],nums2 = [2,4,6], k= 3
 * Return: [1,2],[1,4],[1,6]
 * The first 3 pairs are returned from the sequence:
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 
 * Example 2:
 * Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
 * 
 * Return: [1,1],[1,1]
 * 
 * The first 2 pairs are returned from the sequence:
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 
 * Example 3:
 * Given nums1 = [1,2], nums2 = [3],  k = 3 
 * 
 * Return: [1,3],[2,3]
 * 
 * All possible pairs are returned from the sequence:
 * [1,3],[2,3]
 * 
 * 
 *
 */
public class SolutionDay31_373 {
	
	//heap 的思想
//	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
//
//		List<int[]> ans = new ArrayList<>();
//		int count = 0;
//		int index1 = 0;
//		int index2 = 0, index3 = 0;
//		while (index1 + 1 < nums1.length) {
//
//			while (index2 < nums2.length && nums1[index1] + nums2[index2] <= nums1[index1 + 1] + nums2[index3]) { // 相等取前面那个元素
//				if (count == k)
//					break;
//				ans.add(new int[] { nums1[index1], nums2[index2] });
//				index2++;
//				count++;
//			}
//
//			if (index2 == nums2.length) {
//				index1++;
//				index2 = index3;
//				index3 = 0;
//				continue;
//			}
//
//			while (index3 < nums2.length && nums1[index1] + nums2[index2] > nums1[index1 + 1] + nums2[index3]) {
//				if (count == k)
//					break;
//				ans.add(new int[] { nums1[index1 + 1], nums2[index3] });
//				index3++;
//				count++;
//			}
//			if (count == k)
//				break;
//			if (index3 == nums2.length) {
//				index1++;
//				index3 = 0;
//			}
//		}
//
//		return ans;
//	}
	
	
	
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		
		PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[0]+a[1] - b[0]-b[1]);
		List<int[]> ans = new ArrayList<>();
		if(nums1.length == 0 || nums2.length == 0 || k == 0) return ans;
		
		for (int i = 0; i < nums2.length; i++){
			queue.add(new int[]{nums1[0],nums2[i]});
		}
		
		int index = 1;
		while (!queue.isEmpty()){
			int[] nums = queue.peek();
			if(index < nums1.length && nums[0] + nums[1] > nums1[index] + nums2[0]){
				for (int i = 0; i < nums2.length; i++){
					queue.add(new int[]{nums1[index],nums2[i]});
				}
				index++;
				ans.add(queue.poll());
				if (ans.size() == k) return ans;
			}
			else{ 
				ans.add(queue.poll());
				if (ans.size() == k) return ans;
			}
			
		}
		
		
		return ans;
	}
	
	public static void main(String[] args) {
		SolutionDay31_373 day = new SolutionDay31_373();
		
		int[] nums1 = {1,7,11};
		int[] nums2 = {2,4,6};
		
		int k = 3;
		
		day.kSmallestPairs(nums1, nums2, k);
	}
	
}
