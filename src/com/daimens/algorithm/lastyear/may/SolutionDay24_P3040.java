package com.daimens.algorithm.may;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * @author DemonSong
 * 
 *         3040.Allowance
 * 
 *         Description
 * 
 *         As a reward for record milk production, Farmer John has decided to
 *         start paying Bessie the cow a small weekly allowance. FJ has a set of
 *         coins in N (1 <= N <= 20) different denominations, where each
 *         denomination of coin evenly divides the next-larger denomination
 *         (e.g., 1 cent coins, 5 cent coins, 10 cent coins, and 50 cent
 *         coins).Using the given set of coins, he would like to pay Bessie at
 *         least some given amount of money C (1 <= C <= 100,000,000) every
 *         week.Please help him ompute the maximum number of weeks he can pay
 *         Bessie. Input
 * 
 *         Line 1: Two space-separated integers: N and C
 * 
 *         Lines 2..N+1: Each line corresponds to a denomination of coin and
 *         contains two integers: the value V (1 <= V <= 100,000,000) of the
 *         denomination, and the number of coins B (1 <= B <= 1,000,000) of this
 *         denomation in Farmer John's possession. Output
 * 
 *         Line 1: A single integer that is the number of weeks Farmer John can
 *         pay Bessie at least C allowance Sample Input
 * 
 *         3 6 10 1 1 100 5 120 Sample Output
 * 
 *         111
 *
 */
public class SolutionDay24_P3040 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int C = in.nextInt();
		int[] V = new int[N];
		int[] B = new int[N];
		for (int i = 0; i < N; i++){
			V[i] = in.nextInt();
			B[i] = in.nextInt();
		}
		System.out.println(solve(V, B, C));
		in.close();
	}
	
	private static class Coin{
		int v;
		int b;
		Coin(int v, int b){
			this.v = v;
			this.b = b;
		}
		
		@Override
		public String toString() {
			return "[" + v + "," + b + "]";
		}
	}
	
	//优先处理面值大的元素
	private static int solve(int[] V, int[] B, int C){
		int total = 0;
		Coin[] coins = new Coin[V.length];
		for (int i = 0; i < V.length; i++){
			coins[i] = new Coin(V[i],B[i]);
		}
		Arrays.sort(coins, new Comparator<Coin>() {
			@Override
			public int compare(Coin o1, Coin o2) {
				return o2.v - o1.v;
			}
		});
		
		for (int i = 0; i < V.length; i++){
			if (coins[i].v >= C) {
				total += coins[i].b;
				coins[i].b = 0;
			}
		}
		
		int[] need = new int[V.length];
		while (true){
			int sum = C;
			need = new int[V.length];
			//从大到小凑
			for (int i = 0; i < V.length; i++){
				if (coins[i].b > 0 && sum > 0){
					int can_use = Math.min(coins[i].b, sum / coins[i].v);
					if (can_use > 0){
						sum -= can_use * coins[i].v;
						need[i] = can_use;
					}
				}
			}
			
			//从小到大凑
			for (int i = V.length - 1; i >= 0; --i){
				if (coins[i].b > 0 && sum > 0){
					int can_use = Math.min(coins[i].b - need[i], sum / coins[i].v + 1);
					if (can_use > 0){
						sum -= can_use * coins[i].v;
						need[i] += can_use;
					}
				}
			}
			
			if (sum > 0){
				//剩余硬币凑不满
				break;
			}
			
			int min_week = 1 << 30;
			for (int i = 0; i < V.length; i++){
				if (need[i] == 0) continue;
				min_week = Math.min(min_week, coins[i].b / need[i]);
			}
			
			total += min_week;
			for (int i = 0; i < V.length; i++){
				if (need[i] == 0) continue;
				coins[i].b -= min_week * need[i];
			}
		}
		
		return total;
	}
}
