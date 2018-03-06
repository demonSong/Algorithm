package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 80.Remove Duplicates from Sorted Array II
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * 
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3]
 * Your function should return length = 5, with the first five elements of nums being 1,1,2,2,and 3,it doesn't
 * matter what you leave beyond the new length.
 *
 */
public class SolutionDay07_080 {
	
	//删除超过3次的元素 注意 它是sorted
	public int removeDuplicates(int[] nums) {
        int i = 0;
        
        for (int n : nums){
        	if(i < 2 || n > nums[i-2])
        		nums[i++] = n;
        }
        
		return i;
    }
}
