package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong 3176. Cow Bowling
 * 
 *         Description
 * 
 *         The cows don't use actual bowling balls when they go bowling. They
 *         each take a number (in the range 0..99), though, and line up in a
 *         standard bowling-pin-like triangle like this:
 * 
 *         7
 * 
 * 
 * 
 *         3 8
 * 
 * 
 * 
 *         8 1 0
 * 
 * 
 * 
 *         2 7 4 4
 * 
 * 
 * 
 *         4 5 2 6 5 Then the other cows traverse the triangle starting from its
 *         tip and moving "down" to one of the two diagonally adjacent cows
 *         until the "bottom" row is reached. The cow's score is the sum of the
 *         numbers of the cows visited along the way. The cow with the highest
 *         score wins that frame.
 * 
 *         Given a triangle with N (1 <= N <= 350) rows, determine the highest
 *         possible sum achievable. Input
 * 
 *         Line 1: A single integer, N
 * 
 *         Lines 2..N+1: Line i+1 contains i space-separated integers that
 *         represent row i of the triangle. Output
 * 
 *         Line 1: The largest sum achievable using the traversal rules Sample
 *         Input
 * 
 *         5 7 3 8 8 1 0 2 7 4 4 4 5 2 6 5 Sample Output
 * 
 *         30 Hint
 * 
 *         Explanation of the sample:
 * 
 *         7
 *
 * 
 * 
 *         3 8
 *
 * 
 * 
 *         8 1 0
 *
 * 
 * 
 *         2 7 4 4
 *
 * 
 * 
 *         4 5 2 6 5 The highest score is achievable by traversing the cows as
 *         shown above.
 *
 */
public class SolutionDay02_P3176 {
	
	static int N = 350;
	static int[][] dp = new int[N][N];
	static int[][] score = new int[N][N];
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j <= i; j++){
				score[i][j] = in.nextInt();
			}
		}
		
		dp[0][0] = score[0][0];
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0){
					dp[i][0] = dp[i-1][0] + score[i][j];
					continue;
				}
				if (j == i){
					dp[i][j] = dp[i-1][j-1] + score[i][j];
					continue;
				}
				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + score[i][j];
			}
		}
		
		int max = 0;
		for (int j = 0; j < n; j++){
			max = Math.max(max,dp[n-1][j]);
		}
		
		System.out.println(max);
		
		in.close();
	}	
}
