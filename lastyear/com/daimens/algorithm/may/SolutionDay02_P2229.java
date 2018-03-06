package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         2229.Sumsets
 * 
 *         Description
 * 
 *         Farmer John commanded his cows to search for different sets of
 *         numbers that sum to a given number. The cows use only numbers that
 *         are an integer power of 2. Here are the possible sets of numbers that
 *         sum to 7:
 * 
 *         1) 1+1+1+1+1+1+1 2) 1+1+1+1+1+2 3) 1+1+1+2+2 4) 1+1+1+4 5) 1+2+2+2 6)
 *         1+2+4
 * 
 *         Help FJ count all possible representations for a given integer N (1
 *         <= N <= 1,000,000). Input
 * 
 *         A single line with a single integer, N. Output
 * 
 *         The number of ways to represent N as the indicated sum. Due to the
 *         potential huge size of this number, print only last 9 digits (in base
 *         10 representation). Sample Input
 * 
 *         7 Sample Output
 * 
 *         6
 *
 */
public class SolutionDay02_P2229 {
	
	static int MAX_N = 1000000;
	static int[] dp = new int[MAX_N+1];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		dp[0] = 1;
		for (int i = 1; i <= n;i++){
			if ((i & 0x01) == 0){
				dp[i] = dp[i/2];
			}
			dp[i] += dp[i-1];
			dp[i] %= 1000000000;
		}
		System.out.println(dp[n]);
		in.close();
	}
}
