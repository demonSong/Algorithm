package com.daimens.algorithm.april;

import java.util.Arrays;

/**
 * 
 * @author DemonSong
 * 
 *         188.Best Time to Buy and Sell Stock IV
 * 
 *         Say you have an array for which the ith element is the price of a
 *         given stock on day i.
 * 
 *         Design an algorithm to find the maximum profit. You may complete at
 *         most k transactions.
 * 
 *         Note: You may not engage in multiple transactions at the same time
 *         (ie, you must sell the stock before you buy again).
 *
 */
public class SolutionDay26_188 {

	public int maxProfit(int k, int[] prices) {

		if (k == 0)
			return 0;

		if (k >= prices.length / 2) {
			int maxPro = 0;
			for (int i = 1; i < prices.length; i++) {
				if (prices[i] > prices[i - 1])
					maxPro += prices[i] - prices[i - 1];
			}
			return maxPro;
		}

		int[] sell = new int[k];
		int[] buy = new int[k];
		Arrays.fill(buy, Integer.MIN_VALUE);

		for (int i = 0; i < prices.length; i++) {
			buy[0] = Math.max(buy[0], -prices[i]);
			sell[0] = Math.max(sell[0], buy[0] + prices[i]);
			for (int j = 1; j < k; j++) {
				buy[j] = Math.max(sell[j - 1] - prices[i], buy[j]);
				sell[j] = Math.max(buy[j] + prices[i], sell[j]);
			}
		}
		return sell[k - 1];
	}

}
