package com.daimens.algorithm.may;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3181.Dollar Dayz
 * 
 *         Description
 * 
 *         Farmer John goes to Dollar Days at The Cow Store and discovers an
 *         unlimited number of tools on sale. During his first visit, the tools
 *         are selling variously for $1, $2, and $3. Farmer John has exactly $5
 *         to spend. He can buy 5 tools at $1 each or 1 tool at $3 and an
 *         additional 1 tool at $2. Of course, there are other combinations for
 *         a total of 5 different ways FJ can spend all his money on tools. Here
 *         they are:
 * 
 *         1 @ US$3 + 1 @ US$2
 * 
 *         1 @ US$3 + 2 @ US$1
 * 
 *         1 @ US$2 + 3 @ US$1
 * 
 *         2 @ US$2 + 1 @ US$1
 * 
 *         5 @ US$1 Write a program than will compute the number of ways FJ can
 *         spend N dollars (1 <= N <= 1000) at The Cow Store for tools on sale
 *         with a cost of $1..$K (1 <= K <= 100). Input
 * 
 *         A single line with two space-separated integers: N and K. Output
 * 
 *         A single line with a single integer that is the number of unique ways
 *         FJ can spend his money. Sample Input
 * 
 *         5 3 Sample Output
 * 
 *         5
 *
 */
public class SolutionDay04_P3181 {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		System.out.println(solve(N, K));
		
		in.close();
	}
	
//	public static BigInteger solve(int N, int K){
//		BigInteger[][] dp = new BigInteger[K + 1][N + 1];
//		for (int i = 0; i < dp.length; i++){
//			for (int j = 0; j < dp[0].length; j++){
//				dp[i][j] = BigInteger.ZERO;
//			}
//		}
//		dp[0][0] = BigInteger.ONE;
//		for (int i = 1; i <= K; ++i) {
//			for (int k = 0; k <= N; k += i) {
//				for (int j = N; j >= k; --j) {
//					dp[i][j] = dp[i][j].add(dp[i - 1][j - k]);
//				}
//			}
//		}
//		return dp[K][N];
//	}
	
	public static BigInteger solve(int N, int K){
		BigInteger[][] dp = new BigInteger[K + 1][N + 1];
		for (int i = 0; i < dp.length; i++){
			for (int j = 0; j < dp[0].length; j++){
				dp[i][j] = BigInteger.ZERO;
			}
		}
		dp[0][0] = BigInteger.ONE;
		for (int i = 1; i <= K; ++i) {
			dp[i][0] = BigInteger.ONE;
			for (int j = 1; j <= N; ++j){
				if (j < i){
					dp[i][j] = dp[i-1][j];
				}
				else{
					dp[i][j] = dp[i-1][j].add(dp[i][j-i]);
				}
			}
		}
		return dp[K][N];
	}
}
