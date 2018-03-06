package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         122.Best Time to Buy and Sell Stock II 
 *         Say you have an array for
 *         which the ith element is the price of a given stock on day i.
 * 
 *         Design an algorithm to find the maximum profit. You may complete as
 *         many transactions as you like (ie, buy one and sell one share of the
 *         stock multiple times). However, you may not engage in multiple
 *         transactions at the same time (ie, you must sell the stock before you
 *         buy again).
 *
 */
public class SolutionDay19_122 {
//	public int maxProfit(int[] prices) {
//		
//		if (prices.length == 2){
//			return prices[1] > prices[0] ? prices[1] - prices[0] : 0;
//		}
//		
//		int max = 0;
//		
//		int bottom = 0, up = 0;
//		for (int i  = 1; i + 1 < prices.length; i++){
//			// dec inc
//			if (prices[i-1] > prices[i] && prices[i] < prices[i+1]){
//				bottom = prices[i];
//			}
//			else if (prices[i-1] < prices[i] && prices[i] > prices[i+1]){
//				up = prices[i];
//				max += up - bottom;
//				bottom = 0;
//				up = 0;
//			}
//			
//		}
//		
//		return max;
//	}
	
	public int maxProfit(int[] prices) {
		int max = 0;
		for (int i = 0; i < prices.length -1; i++){
			if (prices[i+1] > prices[i]) max += prices[i+1] - prices[i];
		}
		return max;
	}
}
