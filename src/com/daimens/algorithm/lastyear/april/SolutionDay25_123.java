package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         123.Best Time to Buy and Sell Stock III
 * 
 *         Say you have an array for which the ith element is the price of a
 *         given stock on day i.
 * 
 *         Design an algorithm to find the maximum profit. You may complete at
 *         most two transactions.
 * 
 *         Note: You may not engage in multiple transactions at the same time
 *         (ie, you must sell the stock before you buy again).
 *
 */
public class SolutionDay25_123 {
	
//	public int maxProfit(int[] prices) {
//		
//		int len = prices.length;
//		if (len == 0) return 0;
//		
//		if(maxProfit(prices, 0, len-1) == 0) return 0;
//		
//		int max = 0;
//		for (int i = 0; i < len; i++){
//			max = Math.max(max, maxProfit(prices,0,i-1) + maxProfit(prices,i,len-1));
//		}
//		
//        return max;
//    }
	
	// buy and sell 最多两次交易
//	private int maxProfit(int[] prices,int buy){
//		if (buy == prices.length) return 0;
//	
//		int profit = 0;
//		int max = 0;
//		for (int i = buy; i < prices.length; i++){
//			for (int j = i + 1; j < prices.length; j++){
//				if (prices[j] > prices[i]){
//					profit = (prices[j] - prices[i]) + maxProfit(prices,j+1);
//					max = Math.max(max, profit);
//				}
//			}
//		}
//		
//		return max;
//	}
	
	private int maxProfit(int[] prices, int start, int end){
		
		if (start >= end) return 0;
		
		int sum = 0;
		int max = 0;
		for (int i = start; i <= end; i++){
			if (i == start) continue;
			sum = Math.max(0, sum += prices[i] - prices[i-1]);
			max = Math.max(max, sum);
		}
		return max;
	}
	
	//对未来的不确定，可以在遍历路径上记录下来
	public int maxProfit(int[] prices) {
		int sell1 = 0, sell2 = 0, buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
		for (int i = 0; i < prices.length; i++) {
			buy1 = Math.max(buy1, -prices[i]);
			sell1 = Math.max(sell1, buy1 + prices[i]);
			buy2 = Math.max(buy2, sell1 - prices[i]);
			sell2 = Math.max(sell2, buy2 + prices[i]);
		}
		return sell2;
	}
	
	public static void main(String[] args) {
		SolutionDay25_123 day = new SolutionDay25_123();
		int[] prices = {9,8,7,6,5,4,3,2,1};
		day.maxProfit(prices);
		System.out.println();
	}

}
