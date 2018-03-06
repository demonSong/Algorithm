package com.daimens.algorithm.march;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author DemonSong
 * 525.Contiguous Array
 * Given a binary array,find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0,1] is the longest contiguous subarray with equal number of 0 and 1.
 * 
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0,1] (or [1,0]) is a longest contiguous subarray with equal number of 0 and 1.
 * 
 * Note:
 * The length of the given binary array will not exceed 50,000.
 *
 */
public class SolutionDay14_525 {
	public int findMaxLength(int[] nums) {
		
		for (int i = 0; i < nums.length; i++){
			if(nums[i] == 0) nums[i] = -1;
		}
		
		Map<Integer,Integer> sumToIndex = new HashMap<>();
		
		// value 为可能的MaxLength
		sumToIndex.put(0,-1);
		
		int sum = 0, max = 0;
		for (int i = 0; i < nums.length; i++){
			sum += nums[i];
			if(sumToIndex.containsKey(sum)){
				max = Math.max(max, i-sumToIndex.get(sum));  // 求 的是 差 = 0 所以 只在乎前一个 sum 和 后一个sum 之间的距离。
			}else{
				sumToIndex.put(sum, i);
			}
		}
		
        return max;
    }
}
