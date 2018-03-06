package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 410.Split Array Largest Sum
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous
 * subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 * 
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 * 1 <= n <= 1000
 * 1 <= m <= min(50,n)
 * 
 * Examples:
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 * 
 * Output:
 * 18
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 *
 */
public class SolutionDay06_410 {
	
//	public int splitArray(int[] nums, int m) {
//		
//		if (nums.length == 0) return 0;
//		
//		long[] sums = new long[nums.length];
//		sums[0] = nums[0];
//		for (int i = 1; i < nums.length; i ++){
//			sums[i] = sums[i-1] + nums[i]; //sums 溢出了
//		}
//		
//		if (m == 1) return (int) sums[sums.length-1];
//		
//        return (int) (sums[sums.length-1]-binarySearch(sums, sums[sums.length-1], m));
//    }
//	
//	//m大于3的情况下，不一定max取在最后划分的那sum上
//	private long binarySearch(long[] sums, long maxSum, int m){
//		
//		int lf = m-2, rt = sums.length-1;
//		
//		while (lf < rt){
//			int mid = lf + (rt + 1 - lf) / 2;
//			
//			if(sums[mid] > maxSum / m){
//				rt = mid -1;
//			}else{
//				lf = mid;
//			}
//		}
//		
//		if(sums[lf] <= maxSum / m) return sums[lf];
//		
//		return sums[m-2];
//	}
	
	public int splitArray(int[] nums, int m){
		long sum = 0;
		int max = 0;
		for (int num : nums){
			max = Math.max(max, num);
			sum += num;
		}
		
		
		return (int) binary(nums, m, sum, max);
		
	}
	
	private long binary(int[] nums, int m , long high, long low){
		long mid = 0;
		while (low < high){
			mid = (high + low) /2;
			if(valid(nums, m, mid)){
				high = mid;
			}else{
				low = mid + 1;
			}
		}
		
		return high;
	}
	
	
	private boolean valid(int[] nums, int m , long max){
		int cur = 0;
		int count = 1;
		for (int num : nums){
			cur += num;
			if(cur > max){
				cur = num;
				count ++;
				if(count > m){
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	public static void main(String[] args) {
		SolutionDay06_410 day = new SolutionDay06_410();
		int[] nums = {1,4,4};
		day.splitArray(nums, 1);
	}
}
