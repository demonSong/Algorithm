package com.daimens.algorithm.may;

import java.util.PriorityQueue;

/**
 * 
 * @author DemonSong
 * 
 *         502.IPO
 * 
 *         Suppose LeetCode will start its IPO soon. In order to sell a good
 *         price of its shares to Venture Capital, LeetCode would like to work
 *         on some projects to increase its capital before the IPO. Since it has
 *         limited resources, it can only finish at most k distinct projects
 *         before the IPO. Help LeetCode design the best way to maximize its
 *         total capital after finishing at most k distinct projects.
 * 
 *         You are given several projects. For each project i, it has a pure
 *         profit Pi and a minimum capital of Ci is needed to start the
 *         corresponding project. Initially, you have W capital. When you finish
 *         a project, you will obtain its pure profit and the profit will be
 *         added to your total capital.
 * 
 *         To sum up, pick a list of at most k distinct projects from given
 *         projects to maximize your final capital, and output your final
 *         maximized capital.
 * 
 *         Example 1: Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
 * 
 *         Output: 4
 * 
 *         Explanation: Since your initial capital is 0, you can only start the
 *         project indexed 0. After finishing it you will obtain profit 1 and
 *         your capital becomes 1. With capital 1, you can either start the
 *         project indexed 1 or the project indexed 2. Since you can choose at
 *         most 2 projects, you need to finish the project indexed 2 to get the
 *         maximum capital. Therefore, output the final maximized capital, which
 *         is 0 + 1 + 3 = 4. Note: You may assume all numbers in the input are
 *         non-negative integers. The length of Profits array and Capital array
 *         will not exceed 50,000. The answer is guaranteed to fit in a 32-bit
 *         signed integer.
 *
 */
public class SolutionDay19_L0502 {
	
	// TLE
//	public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
//		int total = W;
//		for (int i = 0; i < k; i++){
//			int maxProfit = 0;
//			//find the max Profit
//			int index = -1;
//			for (int j = 0; j < Capital.length; j++){
//				if (Capital[j] <= total){
//					if (maxProfit < Profits[j]){
//						maxProfit = Profits[j];
//						index = j;
//					}
//				}
//			}
//			if (index == -1) return total;
//			Capital[index] = Integer.MAX_VALUE;
//			total += maxProfit;
//		}
//        return total;
//    }
	
	private class Pair{
		int profit;
		int capital;
		
		Pair(int profit, int capital){
			this.profit = profit;
			this.capital = capital;
		}
	}
	
	public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
		int n = Profits.length;
		Pair[] pairs = new Pair[n];
		
		for (int i = 0; i < n; i++){
			pairs[i] = new Pair(Profits[i],Capital[i]);
		}
		
		PriorityQueue<Pair> q1 = new PriorityQueue<>((o1,o2) -> (o1.capital - o2.capital));
		PriorityQueue<Pair> q2 = new PriorityQueue<>((o1,o2) -> (o2.profit - o1.profit));
		
		for (int i = 0; i < n; i++){
			q1.offer(pairs[i]);
		}
		
		int total = W;
		for (int i = 0; i < k; i++){
			while (!q1.isEmpty() && q1.peek().capital <= total){
				q2.offer(q1.poll());
			}
			if (q2.isEmpty()) return total;
			total += q2.poll().profit;
		}
		return total;
	}
	
	
	
}
