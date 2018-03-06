package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 477.Total Hamming Distance
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are
 * different.
 * Now your job is to find the total Hamming distance between all pairs of the given numbers.
 * Example:
 * Input: 4,14,2
 * Output: 6
 * 
 * Explanation:In binary representation,the 4 is 0100,14 is 1110,and 2 is 0010 (just showing the four bits 
 * relevant in this case).So the answer will be:
 * HammingDistance(4,14) + HammingDistance(4,2) + HammingDistance(14,2) = 2 + 2 + 2 = 6.
 * 
 * Note:
 * 1. Element of the given array are in the range of 0 to 10^9
 * 2. Length of the array will not exceed 10^4
 *
 */
public class SolutionDay03_477 {
	
//	public int totalHammingDistance(int[] nums){
//		int count = 0;
//		for (int i = 0; i < nums.length; i ++){
//			for(int j = i; j < nums.length; j ++){
//				count += hammingDistance(nums[i], nums[j]);
//			}
//		}
//		return count;
//	}
//	
//	//求HammingDistance是否需要优化？
//	private int hammingDistance(int a,int b){
//		int hammingDistance = a ^ b;
//		int count = 0;
//		for (int i = 0; i < 32; i ++){
//			count += (hammingDistance & 0x01) == 1 ? 1:0 ;
//			hammingDistance >>= 1;
//		}
//		return count;
//	}
	
	public int totalHammingDistance(int[] nums){
		int total = 0, n = nums.length;
		for (int i = 0; i < 32; i++){
			int bitCount = 0;
			for (int j = 0; j < nums.length; j++){
				bitCount += (nums[j] >> i & 1);
			}
			total += bitCount * (n - bitCount);
		}
		return total;
	}
	
	public static void main(String[] args) {
		SolutionDay03_477 day03_447 = new SolutionDay03_477();
		//System.out.println(day03_447.hammingDistance(4, 2));
	}
}
