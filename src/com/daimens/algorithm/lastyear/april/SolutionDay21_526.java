package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong 
 * 526.Beautiful Arrangement
 * 
 *         Suppose you have N integers from 1 to N. We define a beautiful
 *         arrangement as an array that is constructed by these N numbers
 *         successfully if one of the following is true for the ith position (1
 *         ≤ i ≤ N) in this array:
 * 
 *         The number at the ith position is divisible by i. i is divisible by
 *         the number at the ith position. Now given N, how many beautiful
 *         arrangements can you construct?
 * 
 *         Example 1: Input: 2 Output: 2 Explanation:
 * 
 *         The first beautiful arrangement is [1, 2]:
 * 
 *         Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 * 
 *         Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 * 
 *         The second beautiful arrangement is [2, 1]:
 * 
 *         Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 * 
 *         Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 *         Note: N is a positive integer and will not exceed 15.
 *
 */
public class SolutionDay21_526 {
	
	//没什么办法 就是尝试所有可能，backtrack 被访问的元素不能再访问
	public int countArrangement(int N) {
		if (N <= 2) return N; 
		backTrack(N, N, new boolean[N+1]);
		return count;
    }
	
	int count = 0;
	private void backTrack(int N, int start,boolean[] used){
		
		if (start == 0){
			count ++;
			return;
		}
		
		for (int i = 1; i <= N; i++) {

			if (used[i] || i % start != 0 && start % i != 0)
				continue;

			used[i] = true;
			backTrack(N, start - 1, used);
			used[i] = false;
		}
	}
	
//	public int countArrangement(int N) {
//		int[] ans = new int[15+1];
//		ans[0] = 0;
//		ans[1] = 1;
//		ans[2] = 2;
//		ans[3] = 3;
//		ans[4] = 11;
//		ans[5] = 21;
//		ans[6] = 57;
//		ans[7] = 98;
//		ans[8] = 230;
//		ans[9] = 480;
//		ans[10] = 1180;
//		ans[11] = 1930;
//		ans[12] = 5940;
//		ans[13] = 10177;
//		ans[14] = 20857;
//		ans[15] = 45536;
//		
//		return ans[N];
//    }
	
	
	
	public static void main(String[] args) {
		SolutionDay21_526 day = new SolutionDay21_526();
		for (int i = 1; i <= 15; i++){
			System.out.println(day.countArrangement(i));
		}
	}
}
