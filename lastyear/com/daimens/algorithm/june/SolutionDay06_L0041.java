package com.daimens.algorithm.june;

/**
 * 
 * @author DemonSong
 * 
 *         41. First Missing Positive
 * 
 *         Given an unsorted integer array, find the first missing positive
 *         integer.
 * 
 *         For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * 
 *         Your algorithm should run in O(n) time and uses constant space.
 *
 */
public class SolutionDay06_L0041 {
	
//	public int firstMissingPositive(int[] nums) {
//		Arrays.sort(nums);
//		int cnt = 1;
//		int pre = 0;
//		for (int num : nums){
//			if (num == pre) continue;
//			if (num == cnt){
//				cnt++;
//			}else{
//				if (cnt > 1) return cnt;
//			}
//			pre = num;
//		}
//		return cnt;
//    }
	
//	public int firstMissingPositive(int[] nums) {
//		if (nums.length == 0) return 1;
//		if (nums.length == 1) return nums[0] == 1 ? 2 : 1;
//		int lf = 0;
//		while (lf < nums.length){
//			int num = nums[lf];
//			if (num < 0 || num >= nums.length || lf == nums[lf]) lf++;
//			else{
//				swap(nums, lf, nums[lf]);
//			}
//		}
//		for (int i = 1; i < nums.length; i++){
//			if (nums[i] != i){
//				return i;
//			}
//		}
//		return nums.length;
//	}
	
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n; ++i){
			while (nums[i] > 0 && nums[i] < n && nums[nums[i]-1] != nums[i])
				swap(nums, i, nums[i]-1);
		}
		for(int i = 0; i < n; ++i)
            if(nums[i] != i + 1)
                return i + 1;
        return n + 1;
	}
	
	private void swap(int[] nums, int i, int j){
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
	public static void main(String[] args) {
		SolutionDay06_L0041 day = new SolutionDay06_L0041();
		int[] nums = {1,1};
		day.firstMissingPositive(nums);
	}

}
