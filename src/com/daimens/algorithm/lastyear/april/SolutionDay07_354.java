package com.daimens.algorithm.april;

import java.util.Arrays;
import java.util.Comparator;

import sun.reflect.generics.tree.ArrayTypeSignature;

/**
 * 
 * @author DemonSong
 * 354.Russian Doll Envelopes.
 * You have a number of envelopes with widths and heights given as a pair of integers (w,h).One envelop can fit into another if
 * and only if both the width and height of one envelope is greater than the width and height of the other envelop.
 * What is the maximum number of envelops can you Russian doll? (put one inside other)
 * 
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 */
public class SolutionDay07_354 {
//	
//	public int maxEnvelopes(int[][] envelopes) {
//		
//		if(envelopes.length == 0) return 0;
//		if(envelopes[0].length == 0) return 0;
//		
//		Arrays.sort(envelopes, new Comparator<int[]>() {
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				return o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1];
//			}
//
//		});
//		
//		int dp[] = new int[envelopes.length];
//	    int len = 1;
//	    dp[0] = envelopes[0][0];
//	    for(int i = 1; i < envelopes.length; i++){
//	        int index = binarySearch(dp, 0, len-1, envelopes[i][1]);
//	        dp[index+1] = envelopes[i][1];
//	        if(index + 1 == len)
//	            len++;
//	    }
//	    return len;
//    }
	
	private int binarySearch(int[] nums, int lf, int rt, int key){
		
		while (lf < rt){
			int mid = lf + (rt + 1 - lf) / 2;
			
			if(nums[mid] >= key){
				rt = mid - 1;
			}else{
				lf = mid;
			}
		}
		
		if (nums[lf] < key) return lf; 
		
		return -1;
	}
	

	public int maxEnvelopes(int[][] envelopes) {
		if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length != 2)
			return 0;
		Arrays.sort(envelopes, new Comparator<int[]>() {
			public int compare(int[] arr1, int[] arr2) {
				if (arr1[0] == arr2[0])
					return arr2[1] - arr1[1];
				else
					return arr1[0] - arr2[0];
			}
		});
		int dp[] = new int[envelopes.length];
		int len = 0;
		for (int[] envelope : envelopes) {
			int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
			if (index < 0)
				index = -(index + 1);
			dp[index] = envelope[1];
			if (index == len)
				len++;
		}
		return len;
	}
	
//	private int binarySearch(Integer[][] nums, int start, int end,Integer[] target){
//		
//		int tmp = end;
//		
//		while (start < end){
//			
//			int mid = start + (end + 1 - start) / 2;
//			
//			if (nums[mid][0] >= target[0] || nums[mid][1] >= target[1]){
//				end = mid -1;
//			}else{
//				start = mid;
//			}
//			
//		}
//		
//		if (start == tmp) return start - 1;
//		
//		return start;
//		
//	}
	
	public static void main(String[] args) {
//		SolutionDay07_354 day = new SolutionDay07_354();
//		int[][] nums = {{2,100},{3,200},{4,300},{5,250},{5,400},{5,500},{6,360},{6,370},{7,380}};
//		System.out.println(day.maxEnvelopes(nums));
		
		int[] test = new int[6];
		for (int i = 0; i <= 10; i+= 2){
			test[i/2] = i;
		}
		
		for (int i = 1; i <= 11; i+= 2){
			System.out.println(Arrays.binarySearch(test, i));
			System.out.println(Arrays.binarySearch(test,0,0, 4));
		}
		
	}

}
