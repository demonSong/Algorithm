package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 343.Integer Break
 * Given a positive integer n,break it into the sum of at least two positive integers and maximize
 * the product of those integers.Return the maximum product you can get.
 * For example,given n = 2,return 1(2 = 1+ 1);given n = 10,return 36(10 = 3 + 3 + 4).
 * Note:You may assume that n is not less than 2 and not larger than 58.
 *
 */
public class SolutionDay04_343 {
	
	//这个dp 是O(n)
	public int integerBreak(int n) {
		int[] dp = new int[n+1];
		for(int i = 0; i < n+1 ;i++){
			//偶数
			if(i%2 == 0){
				int x = i / 2;
				int m1 = Math.max((x - 1) * (x + 1),
						(x - 1 <= 3 ? x - 1 : dp[x - 1]) * ((x + 1) <= 3 ? (x + 1) : dp[x + 1]));
				int m2 = Math.max(x * x, (x <= 3 ? x : dp[x]) * (x <= 3 ? x : dp[x]));
				dp[i] = Math.max(m1, m2);
			}else{//奇数的情况
				int x = i / 2;
				int m1 = Math.max(x * (x + 1), (x <= 3 ? x : dp[x]) *( (x + 1) <= 3 ? (x + 1) : dp[x + 1]));
				int m2 = Math.max((x-1) * (x + 2), (x-1 <= 3 ? x-1 : dp[x-1]) *( (x + 2) <= 3 ? (x + 2) : dp[x + 2]));
				dp[i] = Math.max(m1,m2);
			}
		}
        return dp[n];
        
    }
}
