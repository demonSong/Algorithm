package com.daimens.algorithm.february;

/**
 * 
 * @author DemonSong
 * 75.Sort Colors
 * Given an array with n objects colored red,white or blue,sort them so that objects of the same
 * color are adjacent,with the colors in the order red,white and blue.
 * Here,we will use the integers 0,1,and 2 to represent the color red,white,and blue respectively.
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 *
 */
public class SolutionDay28_075 {
	public void sortColors(int[] nums) {
        int p1 = 0, p2 = nums.length-1,index = 0;
        while (index <= p2){
        	if(nums[index] == 0){
        		nums[index] = nums[p1];
        		nums[p1] = 0;
        		p1++;
        	}
        	
        	if(nums[index] == 2){
        		nums[index] = nums[p2];
        		nums[p2] = 2;
        		p2--;
        		index--;
        	}
        	index++;
        }
    }
}
