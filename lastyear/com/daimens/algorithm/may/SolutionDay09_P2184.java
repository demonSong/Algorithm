package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         2184.Cow Exhibition
 * 
 *         Description
 * 
 *         "Fat and docile, big and dumb, they look so stupid, they aren't much
 *         fun..." - Cows with Guns by Dana Lyons
 * 
 *         The cows want to prove to the public that they are both smart and
 *         fun. In order to do this, Bessie has organized an exhibition that
 *         will be put on by the cows. She has given each of the N (1 <= N <=
 *         100) cows a thorough interview and determined two values for each
 *         cow: the smartness Si (-1000 <= Si <= 1000) of the cow and the
 *         funness Fi (-1000 <= Fi <= 1000) of the cow.
 * 
 *         Bessie must choose which cows she wants to bring to her exhibition.
 *         She believes that the total smartness TS of the group is the sum of
 *         the Si's and, likewise, the total funness TF of the group is the sum
 *         of the Fi's. Bessie wants to maximize the sum of TS and TF, but she
 *         also wants both of these values to be non-negative (since she must
 *         also show that the cows are well-rounded; a negative TS or TF would
 *         ruin this). Help Bessie maximize the sum of TS and TF without letting
 *         either of these values become negative. Input
 * 
 *         Line 1: A single integer N, the number of cows
 * 
 *         Lines 2..N+1: Two space-separated integers Si and Fi, respectively
 *         the smartness and funness for each cow. Output
 * 
 *         Line 1: One integer: the optimal sum of TS and TF such that both TS
 *         and TF are non-negative. If no subset of the cows has non-negative TS
 *         and non- negative TF, print 0.
 * 
 *         Sample Input
 * 
 *         5 -5 7 8 -6 6 -3 2 1 -8 -5 Sample Output
 * 
 *         8 Hint
 * 
 *         OUTPUT DETAILS:
 * 
 *         Bessie chooses cows 1, 3, and 4, giving values of TS = -5+6+2 = 3 and
 *         TF = 7-3+1 = 5, so 3+5 = 8. Note that adding cow 2 would improve the
 *         value of TS+TF to 10, but the new value of TF would be negative, so
 *         it is not allowed.
 *
 */
public class SolutionDay09_P2184 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] S = new int[N];
		int[] F = new int[N];
		for (int i = 0; i < N; i++){
			S[i] = in.nextInt();
			F[i] = in.nextInt();
		}
		System.out.println(solve(S, F));
		
		in.close();
	}
	
	static int INF = 1<<30;
	private static int solve(int[] S, int[] F){
		int n = S.length;
		int sum = 0;
		for (int i = 0; i < n; i++){
			sum += S[i] >= 0 ? S[i] : -S[i];
		}
		
		int[] dp = new int[sum * 2 + 1];
		Arrays.fill(dp, -INF);
		
		int center = sum;
		int range = sum * 2;
		dp[center] = 0;
		
		for (int i = 0; i < n; i++){
			if (S[i] >= 0){
				for (int j = range; j >= S[i]; j--){
					dp[j] = Math.max(dp[j], dp[j-S[i]] + F[i]);
				}
			}else{
				for (int j = 0; j <= range + S[i]; j++){
					dp[j] = Math.max(dp[j], dp[j-S[i]] + F[i]);
				}
			}
		}
		
		int res = -INF;
		for (int i = center; i <= range; i++){
			if (dp[i] >= 0){
				res = Math.max(res, dp[i] + i - center);
			}
		}
		return res;
	}
}
