package com.daimens.algorithm.december;

/**
 * 
 * @author Demon Song
 * 121.Best Time to Buy and Sell Stock
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction(ie,buy one and sell one 
 * share of the stock),design an algorithm to find the maximum profit.
 * Example 1:
 * Input:[7,1,5,3,6,4]
 * Output:5
 * max.difference = 6-1 =5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * 
 * Example 2:
 * Input:[7,6,4,3,1]
 * Output:0
 * In this case ,no transaction is done, i.e. max profit =0.
 *
 */
public class Solution121_1 {
//	public int maxProfit(int[] prices) {
//		int minprice = Integer.MAX_VALUE;
//		int maxprofit = 0;
//		for (int i =0;i<prices.length;i++){
//			if (prices[i] < minprice){
//				//如果是当前最低的价格,总能在未来找到最大值，利益最大化。
//				minprice = prices[i];
//			}else if (prices[i]-minprice > maxprofit){
//				maxprofit = prices[i] - minprice;
//			}
//		}
//		return maxprofit;
//    }
	
//	public int maxProfit(int[] prices) {
//		
//		int buy = Integer.MIN_VALUE;
//		int sell = 0;
//		
//		for (int price : prices){
//			buy = Math.max(buy, -price);
//			sell = Math.max(sell, buy + price);
//		}
//		
//		return sell;
//	}
	
	public int maxProfit(int[] prices) {
		int sum = 0;
		int max = 0;
		
		for (int i = 1; i < prices.length;i++){
			sum = Math.max(0, sum += prices[i]-prices[i-1]);
			max = Math.max(max, sum);
		}
		
		return max;
	}
	
}
