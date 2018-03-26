package com.daimens.algorithm.march;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionDay24_L0500 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
        	int n = in.nextInt();
        	int[] dp = new int[n + 1];
        	Arrays.fill(dp, Integer.MAX_VALUE);
        	
        	dp[1] = 0;
        	for (int i = 2, k = 1; i <= n; i += 1, ++k) {
    			dp[i] = Math.min(dp[i], k);
    		}
        	
        	dp[2] = 1;
        	int j = 2;
        	for (int i = j, k = 0; i <= n; i += j / 2, ++k) {
    			dp[i] = Math.min(dp[i], dp[j] + k);
    		}
        	
        	while (j <= n) {
        		j += j;
        		if (j <= n) dp[j] = dp[j / 2] + 1;
        		for (int i = j, k = 0; i <= n; i += j / 2, ++k) {
        			dp[i] = Math.min(dp[i], dp[j] + k);
        		}
        	}
        	System.out.println(dp[n]);
        }
        in.close();
	}
}
