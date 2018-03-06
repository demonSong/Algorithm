package com.daimens.algorithm.april;

import java.util.Arrays;

/**
 * 
 * @author DemonSong 322.Coin Change You are given coins of different
 *         denominations and a total amount of money amount. Write a function to
 *         compute the fewest number of coins that you need to make up that
 *         amount. If that amount of money cannot be made up by any combination
 *         of the coins, return -1.
 * 
 *         Example 1: coins = [1, 2, 5], amount = 11 return 3 (11 = 5 + 5 + 1)
 * 
 *         Example 2: coins = [2], amount = 3 return -1.
 * 
 *         Note: You may assume that you have an infinite number of each kind of
 *         coin.
 *
 */
public class SolutionDay18_322 {

//	public int coinChange(int[] coins, int amount) {
//		return findMinCoins(coins, amount,new int[amount+1]);
//	}
//	
//	//如何证明rem是复杂依赖，子问题是重复的？
//	private int findMinCoins(int[] coins, int rem,int[] count){
//		
//		if(rem < 0) return -1;
//		if (rem == 0) return 0;
//		
//		if(count[rem] != 0) return count[rem];
//		
//		int min = Integer.MAX_VALUE;
//		for (int coin : coins){
//			int res = findMinCoins(coins, rem - coin,count);
//			if (res >= 0 && res < min){
//				min = 1 + res;
//			}
//		}
//		
//		count[rem] = min == Integer.MAX_VALUE ? -1 : min;
//		
//		return count[rem];
//	}
	
	public int coinChange(int[] coins, int amount) {
		
		if (amount < 0) return -1;
		if (amount == 0) return 0;
		
		
		int max = amount + 1;
		int[] dp = new int[amount+1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		
		for (int i = 1; i <= amount; i++){
			for (int coin : coins){
				if (coin <= i){
					dp[i] = Math.min(dp[i],dp[i-coin] + 1);
				}
			}
		}
		
		return dp[amount] > amount ? -1 : dp[amount];
	}
	
	
	
}
