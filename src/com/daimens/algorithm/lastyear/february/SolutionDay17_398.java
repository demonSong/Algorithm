package com.daimens.algorithm.february;

import java.util.Random;

/**
 * 
 * @author Demon Song
 * 398.Random Pick Index
 * Given an array of integers with possible duplicates,randomly output the index of a given target number.
 * You can assume that the given target number must exist in the array.
 * Note:
 * The array size can be very large.Solution that uses too much extra space will not pass the judge.
 * 
 * Example:
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * 
 * solution.pick(3);
 * 
 * solution.pick(1);
 *
 */
public class SolutionDay17_398 {
	
//	private int[] nums;
//	
//	public SolutionDay17_398(int[] nums) {
//        this.nums = nums;
//    }
//    
//    public int pick(int target) {
//    	List<Integer> res = new ArrayList<>();
//    	for (int i = 0; i < nums.length; i++){
//    		
//    		if(nums[i] == target){
//    			res.add(i);
//    		}
//    	}
//    	
//    	int len = res.size();
//    	int index = res.get(0);
//    	for (int i = 1; i < len; i++){
//    		if(randInt(0,i) == i) index = res.get(i);
//    	}
//        return index;
//    }
//    
//    private int randInt(int min, int max) {
//		return min + (int) (Math.random() * (max - min + 1));
//	}
	
	private int[] nums;
	private Random random;
	
	public SolutionDay17_398(int[] nums) {
      this.nums = nums;
      this.random = new Random();
	}
	
	public int pick(int target){
		int result = -1;
		int count = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] != target){
				continue;
			}
			if(random.nextInt(++count) == 0){
				result = i;
			}
		}
		return result;
	}
}
