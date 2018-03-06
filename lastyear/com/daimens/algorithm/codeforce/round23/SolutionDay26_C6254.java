package com.daimens.algorithm.codeforce.round23;

import java.util.Scanner;

public class SolutionDay26_C6254 {
	
	static int[][] dir = {{1,1},{1,-1},{1,0}};
	static int MOD = 1000000000 + 7;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[][] lines = new int[n][3];
		for (int i = 0; i < n; ++i){
			lines[i][0] = in.nextInt(); // ai
			lines[i][1] = in.nextInt(); // bi
			lines[i][2] = in.nextInt(); // ci
		}
		long[][]  dp = new long[k + 16][16 + 16];
		dp[0][0] = 1;
		for (int i = 1; i <= k; ++i){
			
		}
		in.close();
	}
}
