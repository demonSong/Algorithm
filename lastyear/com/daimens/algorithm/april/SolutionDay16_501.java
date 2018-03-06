package com.daimens.algorithm.april;

public class SolutionDay16_501 {
	
	public String optimalDivision(int[] nums) {
		
		if (nums.length == 1){
			return nums[0]+"";
		}
		
		if(nums.length == 2){
			return nums[0] + "/" + nums[1];
		}
		
		String ans = nums[0] + "/(";
		for (int i = 1; i < nums.length; i++) {
			ans += nums[i] + "/";
		}
		
		ans = ans.substring(0, ans.length()-1) + ")";
		
		return ans;
    }
	
	
	public static void main(String[] args) {
		SolutionDay16_501 day = new SolutionDay16_501();
	}

}
