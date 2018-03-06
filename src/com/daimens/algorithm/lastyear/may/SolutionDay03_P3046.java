package com.daimens.algorithm.may;

import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3046. Ant Counting
 * 
 *         Description
 * 
 *         Bessie was poking around the ant hill one day watching the ants march
 *         to and fro while gathering food. She realized that many of the ants
 *         were siblings, indistinguishable from one another. She also realized
 *         the sometimes only one ant would go for food, sometimes a few, and
 *         sometimes all of them. This made for a large number of different sets
 *         of ants!
 * 
 *         Being a bit mathematical, Bessie started wondering. Bessie noted that
 *         the hive has T (1 <= T <= 1,000) families of ants which she labeled
 *         1..T (A ants altogether). Each family had some number Ni (1 <= Ni <=
 *         100) of ants.
 * 
 *         How many groups of sizes S, S+1, ..., B (1 <= S <= B <= A) can be
 *         formed?
 * 
 *         While observing one group, the set of three ant families was seen as
 *         {1, 1, 2, 2, 3}, though rarely in that order. The possible sets of
 *         marching ants were:
 * 
 *         3 sets with 1 ant: {1} {2} {3} 5 sets with 2 ants: {1,1} {1,2} {1,3}
 *         {2,2} {2,3} 5 sets with 3 ants: {1,1,2} {1,1,3} {1,2,2} {1,2,3}
 *         {2,2,3} 3 sets with 4 ants: {1,2,2,3} {1,1,2,2} {1,1,2,3} 1 set with
 *         5 ants: {1,1,2,2,3}
 * 
 *         Your job is to count the number of possible sets of ants given the
 *         data above. Input
 * 
 *         Line 1: 4 space-separated integers: T, A, S, and B
 * 
 *         Lines 2..A+1: Each line contains a single integer that is an ant type
 *         present in the hive Output
 * 
 *         Line 1: The number of sets of size S..B (inclusive) that can be
 *         created. A set like {1,2} is the same as the set {2,1} and should not
 *         be double-counted. Print only the LAST SIX DIGITS of this number,
 *         with no leading zeroes or spaces. Sample Input
 * 
 *         3 5 2 3 1 2 2 1 3 Sample Output
 * 
 *         10 Hint
 * 
 *         INPUT DETAILS:
 * 
 *         Three types of ants (1..3); 5 ants altogether. How many sets of size
 *         2 or size 3 can be made?
 * 
 * 
 *         OUTPUT DETAILS:
 * 
 *         5 sets of ants with two members; 5 more sets of ants with three
 *         members
 *
 */
public class SolutionDay03_P3046 {
	
	static final int MOD = 1000000;
	static int[] family = new int[1000+16];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		int A = in.nextInt();
		int S = in.nextInt();
		int B = in.nextInt();
		
		for (int i = 0; i < A; i++){
			int index = in.nextInt();
			++family[index];
		}
		System.out.println(solve(T,A,S,B,family));
		in.close();
	}
	
//	public static int solve(int T, int A, int S, int B, int[] family){
//		int[][] dp = new int[T+1][10000 + 16];
//		dp[0][0] = 1;
//		int total = family[0];
//		for (int i = 1; i <= T; ++i){
//			total += family[i];
//			for (int k = 0; k <= family[i]; ++k){
//				for (int j = total; j >= k; --j){
//					dp[i][j] = (dp[i][j] + dp[i-1][j-k]) % MOD;
//				}
//			}
//		}
//		
//		int ans = 0;
//		for (int i = S; i <= B; ++i){
//			ans = (ans + dp[T][i]) % MOD;
//		}
//		return ans;
//	}
	
	public static int solve(int T, int A, int S, int B, int[] family){
		int[][] dp = new int[2][A+1];

		dp[0][0] = dp[1][0] = 1;
		
		int total = A;
		for (int i = 1; i <= T; ++i){
			for (int j = 1; j <= total; ++j){
				if (j - 1 - family[i] >= 0){
					dp[i%2][j] = (dp[i%2][j-1] - dp[(i-1)%2][j-1-family[i]] + dp[(i-1)%2][j] + MOD) % MOD;
				}else{
					dp[i%2][j] = (dp[i%2][j-1] + dp[(i-1)%2][j] ) % MOD;
				}
			}
		}
		
		int ans = 0;
		for (int i = S; i <= B; ++i){
			ans = (ans + dp[T%2][i]) % MOD;
		}
		return ans;
	}

}
