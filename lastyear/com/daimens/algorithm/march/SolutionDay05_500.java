package com.daimens.algorithm.march;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 532. K-diff Pairs in an Array
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array.
 * Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.
 * Example 1:
 * Input: [3, 1, 4, 1, 5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 * 
 * Example 2：
 * Input:[1, 2, 3, 4, 5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 * 
 * Example 3:
 * Input: [1, 3, 1, 5, 4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 * 
 * Note:
 * 1. The pairs (i, j) and (j, i) count as the same pair.
 * 2. The length of the array won't exceed 10,000.
 * 3. All the integers in the given input belong to the range: [-1e7, 1e7].
 *
 */
public class SolutionDay05_500 {
	
//	public int findPairs(int[] nums, int k) {
//		//k == 0是特殊情况
//		if (k == 0){
//			int sum = 0;
//			HashMap<Integer, Integer> map = new HashMap<>();
//			for (int i = 0; i < nums.length;i++){
//				//没有元素
//				if(map.get(nums[i]) ==null ){
//					map.put(nums[i], 1);
//				}
//				else{
//					int count = map.get(nums[i]);
//					map.put(nums[i], ++count);
//				}
//				
//			}
//			
//			for (int num : map.keySet()){
//				if(map.get(num) > 0){
//					sum ++;
//				}
//			}
//			return sum;
//		}
//		else{
//			Set<Integer> set = new HashSet<>();
//			for (int i = 0; i < nums.length;i++){
//				set.add(nums[i]);
//			}
//			
//			
//			int sum = 0;
//			int tmp[] = new int[set.size()];
//			
//			
//			int i = 0;
//			for (int outer : set){
//				tmp[i++] = outer;
//			}
//			
//			for (int j = 0; j < tmp.length;j++){
//				for (int x = j ; x < tmp.length; x++){
//					if(Math.abs(tmp[x]-tmp[j]) == k)
//						sum++;
//				}
//			}
//			
//			return sum;
//		}
//    }
	
	public int findPairs(int[] nums, int k) {
		if(k < 0) return 0; // border
		Map<Integer,Integer> map = new HashMap<>();
		int count = 0, n = nums.length;
		//双指针遍历
		for (int i = 0, j = 0; j < 2 * n; j++, i = j % n) { //为什么要扫描两遍
			
			if(!map.containsKey(nums[i])) map.put(nums[i], i);
			Integer upper = map.get(nums[i] + k);
			
			
			if(upper != null && upper != -1 && upper != i){ //upper != i 针对的是k=0 的情况吧?
				count++;
				map.put(nums[i] + k, -1);
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		SolutionDay05_500 day = new SolutionDay05_500();
	}

}
