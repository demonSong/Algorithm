package com.daimens.algorithm.april;

public class SolutionDay07_L0810 {
	
    public boolean xorGame(int[] nums) {
    	int xor = 0;
    	for (int num : nums) xor ^= num;
    	return xor == 0 || nums.length % 2 == 0;
    }
    
    boolean sub(int xor, int[] nums, boolean[] vis) {
    	if (xor == 0) return true;
    	for (int i = 0; i < nums.length; ++i) {
    		if (!vis[i]) {
    			vis[i] = true;
    			if (!sub(xor ^ nums[i], nums, vis)) {
    				return true;
    			}
    			vis[i] = false;
    		}
    	}
    	return false;
    }
    
    public static void main(String[] args) {
    	SolutionDay07_L0810 day = new SolutionDay07_L0810();
    	int[] nums = {1, 1, 2, 3, 4};
    	System.out.println(day.xorGame(nums));
	}
	
}
