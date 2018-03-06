package com.daimens.algorithm.january;

/**
 * 
 * @author Demon Song
 * 414.Third Maximum Number
 * Given a non-empty array of integers,return the third maximum number
 * in this array. If it does not exist,return the maximum number. The 
 * time complexity must be in O(n).
 * Example 1:
 * Input: [3,2,1]
 * Output: 1
 * Explanation: The third maximum is 1.
 * 
 * Example 2:
 * Input: [1,2]
 * Output: 2
 * Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 * 
 * Example 3:
 * Input: [2, 2, 3, 1]
 * Output: 1
 * Explanation: Note that the third maximum here means the third maximum distinct number.
 * Both numbers with value 2 are both considered as second maximum.
 *
 */
public class SolutionDay22_414 {
	public int thirdMax(int[] nums) {
		long max = Long.MIN_VALUE,second = Long.MIN_VALUE,third = Long.MIN_VALUE;
		for (int i = 0; i < nums.length; i++){
			if(nums[i] == max || nums[i] == second || nums[i] == third) continue;
			if(nums[i] > max){
				third = second;
				second = max;
				max = nums[i];
			}
			else if(nums[i] > second){
				third = second;
				second = nums[i];
			}
			else if(nums[i] > third){
				third = nums[i];
			}
		}
        return (third  == Long.MIN_VALUE) ? (int)max : (int)third;
    }
}
