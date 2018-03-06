package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         2385.Apple Catching
 * 
 *         Description
 * 
 *         It is a little known fact that cows love apples. Farmer John has two
 *         apple trees (which are conveniently numbered 1 and 2) in his field,
 *         each full of apples. Bessie cannot reach the apples when they are on
 *         the tree, so she must wait for them to fall. However, she must catch
 *         them in the air since the apples bruise when they hit the ground (and
 *         no one wants to eat bruised apples). Bessie is a quick eater, so an
 *         apple she does catch is eaten in just a few seconds.
 * 
 *         Each minute, one of the two apple trees drops an apple. Bessie,
 *         having much practice, can catch an apple if she is standing under a
 *         tree from which one falls. While Bessie can walk between the two
 *         trees quickly (in much less than a minute), she can stand under only
 *         one tree at any time. Moreover, cows do not get a lot of exercise, so
 *         she is not willing to walk back and forth between the trees endlessly
 *         (and thus misses some apples).
 * 
 *         Apples fall (one each minute) for T (1 <= T <= 1,000) minutes. Bessie
 *         is willing to walk back and forth at most W (1 <= W <= 30) times.
 *         Given which tree will drop an apple each minute, determine the
 *         maximum number of apples which Bessie can catch. Bessie starts at
 *         tree 1. Input
 * 
 *         Line 1: Two space separated integers: T and W
 * 
 *         Lines 2..T+1: 1 or 2: the tree that will drop an apple each minute.
 *         Output
 * 
 *         Line 1: The maximum number of apples Bessie can catch without walking
 *         more than W times. Sample Input
 * 
 *         7 2 2 1 1 2 2 1 1 Sample Output
 * 
 *         6 Hint
 * 
 *         INPUT DETAILS:
 * 
 *         Seven apples fall - one from tree 2, then two in a row from tree 1,
 *         then two in a row from tree 2, then two in a row from tree 1. Bessie
 *         is willing to walk from one tree to the other twice.
 * 
 *         OUTPUT DETAILS:
 * 
 *         Bessie can catch six apples by staying under tree 1 until the first
 *         two have dropped, then moving to tree 2 for the next two, then
 *         returning back to tree 1 for the final two.
 *
 */
public class SolutionDay02_P2385 {
	static int MAX_T = 1000;
	static int[][][] dp = new int[MAX_T][32][2];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int W = in.nextInt();
		
		int[] nums = new int[T];
		for (int i = 0; i < T; i++){
			nums[i] = in.nextInt();
		}
		
//		int[] nums = {2,1,1,2,2,1,1};
//		int W = 2;
		
		//阶段0 移动0次 位置1时 最大值
		dp[0][0][0] = nums[0] == 1 ? 1 : 0;
		
		//阶段0 移动1次 位置2时 最大值
		dp[0][1][1] = nums[0] == 1 ? 0 : 1;
		
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j <= W; j++) {
				for (int k = 1; k <= 2; k++) {
					if (k == nums[i]){
						// not move and eat
						dp[i][j][k-1] = Math.max(dp[i][j][k-1], dp[i-1][j][k-1] + 1);
						// move and not eat
						dp[i][j+1][move(k)-1] = Math.max(dp[i][j+1][move(k)-1], dp[i-1][j][k-1]);
					}else{
						// not move and not eat
						dp[i][j][k-1] = Math.max(dp[i][j][k-1], dp[i-1][j][k-1]);
						// move and eat
						dp[i][j+1][move(k)-1] = Math.max(dp[i][j+1][move(k)-1], dp[i-1][j][k-1]+1);
					}
				}
			}
		}
		System.out.println(Math.max(dp[nums.length-1][W][0],dp[nums.length-1][W][1]));
	}
	
	private static int move (int n){
		return n == 1 ? 2 : 1;
	}
}
