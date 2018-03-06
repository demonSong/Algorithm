package com.daimens.algorithm.february;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Demon Song
 * 421.Maximum XOR of Two Numbers in an Array
 * Given a non-empty array of numbers,a0,a1,a2,...,an-1,where 0 ≤ ai < 2^31.
 * Find the maximum result of ai XOR aj, where where 0 ≤ i, j < n.
 * Could you do this in O(n) runtime?
 * Example:
 * Input: [ 3, 10, 5, 25, 2, 8]
 * Output: 28
 * Explanation:
 * The maximum result is 5 ^ 25 = 28.
 *
 */
public class SolutionDay07_421 {
	
	public int findMaximumXOR(int[] nums){
		int max = 0, mask = 0;
		for (int i = 31; i >= 0; i--){
			mask = mask | ( 1 << i);
			Set<Integer> set = new HashSet<>();
			for (int num : nums){
				set.add(num & mask);
			}
			/**
			 * Use 0 to keep the bit,1 to find XOR
			 * 0 ^ 0 = 0
			 * 0 ^ 1 = 1
			 * 1 ^ 0 = 1
			 * 1 ^ 1 = 0
			 */
			int tmp = max | (1 << i); //in each iteration ,there are pair(s) whose left bits can XOR to max
			for (int prefix : set){
				if(set.contains(tmp ^ prefix)){
					max = tmp;
					break;
				}
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		SolutionDay07_421 day = new SolutionDay07_421();
		int[] nums = {3,10,5,25,2,8};
		day.findMaximumXOR(nums);
	}

}
