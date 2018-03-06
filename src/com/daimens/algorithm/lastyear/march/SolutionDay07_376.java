package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 376.Wiggle Subsequence
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly 
 * alternate between positive and negative. The first difference (if one exists) may be either positive or negative. 
 * A sequence with fewer than two elements is trivially a wiggle sequence.
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately 
 * positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, 
 * the first because its first two differences are positive and the second because its last difference is zero.
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. 
 * A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, 
 * leaving the remaining elements in their original order.
 * Examples:
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * The entire sequence is a wiggle sequence.
 * 
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 * 
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * 
 * Follow up:
 * Can you do it in O(n) time ?
 * 
 *
 */
public class SolutionDay07_376 {
	
	Class c=SolutionDay07_376.class;
	// not work
//	public int wiggleMaxLength(int[] nums){
//		if(nums.length == 0) return 0;
//		
//		int cnt = 0;
//		
//		int[] wiggle = new int[nums.length-1];
//		for (int i = 1; i < nums.length; i++){
//			wiggle[i-1] = nums[i]-nums[i-1];
//		}
//		
//		//异号比较
//		for (int i = 1; i < wiggle.length; i++) {
//			if(wiggle[i] * wiggle[i-1] <0){
//				cnt ++;
//			}
//		}
//		
//		return cnt;
//	}
	
	//直接生成结果
	public int wiggleMaxLength(int[] nums){
		if(nums.length == 0 || nums.length == 1){
			return nums.length;
		}
		
		int k= 0;
		
		while (k < nums.length-1 && nums[k] == nums[k+1]){ //skip all the same numbers from series beginning
			k++;
		}
		
		if(k == nums.length-1){
			return 1;
		}
		
		int result = 2;
		boolean smallReq = nums[k] < nums[k+1]; //first we find small big
		
		for (int i = k + 1; i < nums.length - 1; i++) {
			if(smallReq && nums[i+1] < nums[i]){ // first small big and big small so ++
				nums[result] = nums[i+1];
				result++;
				smallReq = !smallReq;  //now smallReq are big small
			}else{
				if(!smallReq && nums[i+1] > nums[i]){ // second big small and small big
					nums[result] = nums[i+1];
					result++;
					smallReq = !smallReq; //now smallReq are small big.
				}
			}
		}
		return result;
	}

}
