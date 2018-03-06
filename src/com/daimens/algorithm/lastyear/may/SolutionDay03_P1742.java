package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         1742.Coins
 * 
 *         Description
 * 
 *         People in Silverland use coins.They have coins of value A1,A2,A3...An
 *         Silverland dollar.One day Tony opened his money-box and found there
 *         were some coins.He decided to buy a very nice watch in a nearby shop.
 *         He wanted to pay the exact price(without change) and he known the
 *         price would not more than m.But he didn't know the exact price of the
 *         watch. You are to write a program which reads n,m,A1,A2,A3...An and
 *         C1,C2,C3...Cn corresponding to the number of Tony's coins of value
 *         A1,A2,A3...An then calculate how many prices(form 1 to m) Tony can
 *         pay use these coins. Input
 * 
 *         The input contains several test cases. The first line of each test
 *         case contains two integers n(1<=n<=100),m(m<=100000).The second line
 *         contains 2n integers, denoting A1,A2,A3...An,C1,C2,C3...Cn
 *         (1<=Ai<=100000,1<=Ci<=1000). The last test case is followed by two
 *         zeros. Output
 * 
 *         For each test case output the answer on a single line. Sample Input
 * 
 *         3 10 1 2 4 2 1 1 2 5 1 4 2 1 0 0 Sample Output
 * 
 *         8 4
 *
 */
public class SolutionDay03_P1742 {
//	static int MAX_N = 100;
//	static int MAX_M = 100000;
//	static boolean[][] dp = new boolean[MAX_N+1][MAX_M+1];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (true){
			int n = in.nextInt();
			int m = in.nextInt();
			
			if (n == 0 && m == 0) break;
			
			int[] A = new int[n];
			int[] C = new int[n];
			
			for (int i = 0; i < n;  i++){
				A[i] = in.nextInt();
			}
			for (int i = 0; i < n;  i++){
				C[i] = in.nextInt();
			}
			
//			for (int i = 0; i < dp.length;i++){
//				Arrays.fill(dp[i], false);
//			}
			
//			boolean[][] dp = new boolean[n+1][m+1];
//			
//			dp[0][0] = true;
//			for (int i = 0; i < n; i++){
//				for (int j = 0; j <= m; j++){
//					for (int k = 0; k <= C[i]; k++){
//						if (k * A[i] <= j){
//							dp[i+1][j] |= dp[i][j-k*A[i]];
//						}
//					}
//				}
//			}
//			
//			int count = 0;
//			for (int i = 1; i <= m; i++){
//				if (dp[n][i]) count ++;
//			}
//			System.out.println(count);
			
			System.out.println(solve(n, m, A, C));
		}
		
		in.close();
	}
	
	//MLE
//	public static int solve(int n, int m, int[] A, int[] C){
//		int[][] dp = new int[n+1][m+1];
//		for (int i = 0; i < dp.length; i++){
//			Arrays.fill(dp[i], -1);
//		}
//		dp[0][0] = 0;
//		for (int i = 0; i < n; i++){
//			for (int j = 0; j <= m; j++){
//				if (dp[i][j] >= 0){
//					dp[i+1][j] = C[i];
//				}
//				else if (j < A[i] || dp[i+1][j - A[i]] <= 0){
//					dp[i+1][j] = -1;
//				}
//				else{
//					dp[i+1][j] = dp[i+1][j-A[i]] - 1;
//				}
//			}
//		}
//		
//		int answer = 0;
//		for (int i = 1; i <= m; i++){
//			if (dp[n][i] >= 0) answer++;
//		}
//		
//		return answer;
//	}
	
	public static int solve(int n, int m, int[] A, int[] C){
		int[] dp = new int[m+1];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j <= m; j++){
				if (dp[j] >= 0){
					dp[j] = C[i];
				}
				else if (j < A[i] || dp[j-A[i]] <= 0){
					dp[j] = -1;
				}
				else{
					dp[j] = dp[j-A[i]] - 1;
				}
			}
		}
		
		int answer = 0;
		for (int i = 1; i <= m; i++){
			if (dp[i] >= 0) answer++;
		}
		return answer;
	}

}
