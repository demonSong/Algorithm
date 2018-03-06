package com.daimens.algorithm.february;

import java.awt.font.NumericShaper;

/**
 * 
 * @author Demon Song
 * 287.Find the Duplicate Number
 * Given an array nums containing n+1 integers where each integer is between 1 and n  (inclusive),
 * prove that at least one duplicate number must exist.Assume that there is only one duplicate number,
 * find the duplicate one.
 * Note:
 * 1. You must not modify the array(assume the array is read only).
 * 2. You must use only constant,O(1) extra space.
 * 3. You runtime complexity should be less than O(n^2)
 * 4. There is only one duplicate number in the array,but it could be repeated more than once.
 *
 */
public class SolutionDay13_287 {
	
//	public int findDuplicate(int[] nums) {
//		for(int i = 0; i < nums.length; i++){
//			for(int j = i+1; j < nums.length; j++){
//				if(nums[i] == nums[j])
//					return nums[i];
//			}
//		}
//        return 0;
//    }
	
//	public int findDuplicate(int[] nums) {
//		int[] count = new int[nums.length+1];
//		for(int i =0 ;i < nums.length; i++){
//			count[nums[i]] ++;
//			if(count[nums[i]] >=2){
//				return nums[i];
//			}
//		}
//        return 0;
//    }
	
	//证明是个好问题
	public int findDuplicate(int[] nums){
		if(nums.length > 1){
			int slow = nums[0];
			int fast = nums[nums[0]];
			//循环，有点类似链表
			while(slow != fast){
				slow = nums[slow];
				fast = nums[nums[fast]];
			}
			fast = 0;
			while(fast != slow){
				fast = nums[fast];
				slow = nums[slow];
			}
			return slow;
		}
		
		return -1;
	}

}
