package com.daimens.algorithm.february;

/**
 * 
 * @author DemonSong
 * 416.Partition Equal Subset Sum
 * Given a non-empty array containing only positive integers,find if the array can be partitioned into two
 * subsets such that the sum of elements in both subsets is equal.
 * Note:
 * 1. Each of the array element will not exceed 100.
 * 2. The array size will not exceed 200.
 * Example 1:
 * Input: [1,5,11,5]
 * Output: True
 * Explanation: The array can be partitioned as [1,5,5] and [11].
 * Example 2:
 * Input: [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 */
public class SolutionDay24_416 {
//	public boolean canPartition(int[] nums) {
//		int sum = 0;
//		for (int i = 0; i < nums.length; i++){
//			sum += nums[i];
//		}
//		if(sum % 2 == 1) return false;
//		
//		int partition = sum / 2;
//		Arrays.sort(nums);
//		
//		//这里还少了哪种没有考虑的情况？
//		for (int i = nums.length-1; i >= 0; i--){
//			if(partition >= nums[i]){
//				partition -= nums[i];
//			}
//		}
//		
//		
//		return partition == 0 ? true : false;
//	}
	
	public boolean canPartition(int[] nums) {
		// check edge case
        if (nums == null || nums.length == 0) {
            return true;
        }
        // preprocess
        int volumn = 0;
        for (int num : nums) {
            volumn += num;
        }
        if (volumn % 2 != 0) {
            return false;
        }
        
        volumn /= 2;
        // volumn 呈动态变化的过程
        boolean[] dp = new boolean[volumn + 1];
        dp[0] = true;
        
        for (int i = 1; i <= nums.length; i++){
        	for (int j = volumn; j >= nums[i-1]; j--){
        		dp[j] = dp[j] || dp[j-nums[i-1]];
        	}
        }
        
        return dp[volumn];
	}
	
	public static void main(String[] args) {
		SolutionDay24_416 day = new SolutionDay24_416();
		int[] nums = { 71, 70, 66, 54, 32, 63, 38, 98, 4, 22, 61, 40, 6, 8, 6, 21, 71, 36, 30, 34, 44, 60, 89, 53, 60,
				56, 73, 14, 63, 37, 15, 58, 51, 88, 88, 32, 80, 32, 10, 89, 67, 29, 68, 65, 34, 15, 88, 8, 57, 78, 37,
				63, 73, 65, 47, 39, 32, 74, 31, 44, 43, 4, 10, 8, 96, 22, 58, 87, 29, 99, 79, 13, 96, 21, 62, 71, 34,
				55, 72, 3, 96, 7, 36, 64, 30, 6, 14, 87, 12, 90, 40, 13, 29, 21, 94, 33, 99, 86, 4, 100 };
		day.canPartition(nums);
	}
	
}
