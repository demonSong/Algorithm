package com.daimens.algorithm.october;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class SolutionDay22_L0714 {

	// class Node implements Comparable<Node>{
	// int idx;
	// int val;
	//
	// Node(int idx, int val){
	// this.idx = idx;
	// this.val = val;
	// }
	//
	// @Override
	// public int compareTo(Node o) {
	// return this.val != o.val ? this.val - o.val : this.idx - o.idx;
	// }
	// }
	//
	// TreeSet<Node> sorted;
	// Map<Integer, List<Integer>> map;
	// public int maxProfit(int[] prices, int fee) {
	// int n = prices.length;
	// f = fee;
	// mem = new int[prices.length + 1];
	// Arrays.fill(mem, -1);
	//
	// map = new HashMap<>();
	// sorted = new TreeSet<>();
	//
	// for (int i = 0; i < n; ++i) {
	// sorted.add(new Node(i, prices[i]));
	// }
	//
	// for (int i = 0; i < n; ++i) {
	// map.put(i, new ArrayList<>());
	// for (Node node : sorted.subSet(new Node(-1, -50016), new Node(i, prices[i] -
	// fee))) {
	// if (node.idx < i) {
	// map.get(node.idx).add(i);
	// }
	// }
	// }
	// return f(prices, 0);
	// }
	//
	// int f;
	// int[] mem;
	// public int f(int[] prices, int pos) {
	// if (pos == prices.length) {
	// return 0;
	// }
	// if (mem[pos] != -1) {
	// return mem[pos];
	// }
	// int cost = 0;
	//
	// for (int j : map.get(pos)) {
	// cost = Math.max(cost, prices[j] - f - prices[pos] + f(prices, j + 1));
	// }
	//
	// cost = Math.max(cost, f(prices, pos + 1));
	//
	// mem[pos] = cost;
	// return cost;
	// }

	
	int[] mem;
	int f(int[] prices, int pos, int fee) {
		if (pos == -1) return 0;
		int max = 0;
		for (int i = 0; i < pos; ++i) {
			max = Math.max(max, prices[pos] - fee - prices[i] + f(prices, i - 1, fee));
		}
		max = Math.max(max, f(prices, pos - 1, fee));
		return max;
	}
	
	public int maxProfit(int[] prices, int fee) {
		return f(prices, prices.length - 1, fee);
	}
	
//	public int maxProfit(int[] prices, int fee) {
//		int n = prices.length;
//		int[] hold = new int[n + 1];
//		int[] unHold = new int[n + 1];
//
//		hold[0] = Integer.MIN_VALUE;
//
//		for (int i = 1; i <= n; ++i) {
//			hold[i] = Math.max(hold[i - 1], unHold[i - 1] - prices[i - 1] - fee);
//			unHold[i] = Math.max(unHold[i - 1], hold[i - 1] + prices[i - 1]);
//		}
//
//		return unHold[n];
//	}

	public static void main(String[] args) {
		SolutionDay22_L0714 day = new SolutionDay22_L0714();
		int[] prices = { 1, 3, 2, 8, 4, 9 };

		List<Integer> ans = new ArrayList<>();
		ans.add(0);
		ans.add(1);

		int[] toArray = new int[ans.size()];
		for (int i = 0; i < ans.size(); ++i) {
			toArray[i] = ans.get(i);
		}

		System.out.println(day.maxProfit(prices, 2));
	}
}
