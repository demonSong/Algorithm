package com.daimens.algorithm.august;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionDay05_B1003 {
	
	static class Pair implements Comparable<Pair>{
		int id;
		int k;
		int p;
		double ratio;
		public Pair(int id, int k, int p){
			this.id = id;
			this.k = k;
			this.p = p;
			this.ratio = k / (1.0 * p); //单点伤害消耗的水晶数量，相同取p较小的
		}
		@Override
		public int compareTo(Pair that) {
			double cmp = this.ratio - that.ratio;
			return cmp != 0 ? (cmp < 0 ? -1 : 1) : (this.p - that.p);
		}
		
		@Override
		public String toString() {
			return "id:"  + id + " " + ratio;
		}
	}
	
	static int max = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] monsters = new int[n][2];
			for (int i = 0; i < n; ++i){
				int a = in.nextInt();
				int b = in.nextInt();
				monsters[i] = new int[]{a, b};
				max = Math.max(max, a);
			} 
			int[][] warriors = new int[m][2];
			for (int i = 0; i < m; ++i){
				int k = in.nextInt();
				int p = in.nextInt();
				warriors[i] = new int[]{k, p};
			}
			System.out.println(solve(monsters, warriors));
		}
		in.close();
	}
	
	public static long solve(int[][] monsters, int[][] warriors){
		long sum = 0;
		for (int i = 0; i < monsters.length; ++i){
			int b = monsters[i][1];
			Pair[] ps = new Pair[warriors.length];
			for (int k = 0; k < warriors.length; ++k){
				int rem = warriors[k][1] - b;
				if (rem < 0) rem = 0;
				ps[k] = new Pair(k, warriors[k][0], rem);
			}
			Arrays.sort(ps);
			int j = 0;
			int rem = ps[j].p;
			sum += (monsters[i][0] / rem) * (ps[j].k);
			if (monsters[i][0] % rem != 0){
				sum += dp(monsters[i][0] % rem, b, ps[j].k, ps);
			}
		}
		return sum;
	}
	
	public static long dp(int rem, int b, int now, Pair[] ps){
		int max = now;
		int res = 0;
		int j = 0;
		while (j < ps.length && ps[j].p > rem){
			max = Math.min(max, ps[j].k);
			++j;
		}
		int re = ps[j].p;
		res += rem / re * (ps[j].k);
		if (rem % re != 0){
			res += dp(rem % re, b, ps[j].k, ps);
		}
		return Math.min(max, res);
	}
	
//	public static int preprocessing(int[] monster, int[][] warriors){
//		List<int[]> valids = new ArrayList<>();
//		int w = monster[0];
//		int b = monster[1];
//		for (int i = 0; i < warriors.length; ++i){
//			if (warriors[i][1] >= b){
//				valids.add(new int[]{warriors[i][0], warriors[i][1] - b});
//			}
//		}
//		if (valids.isEmpty()) return -1;
//		int[] weights = new int[valids.size()];
//		int[] values = new int[valids.size()];
//		for (int i = 0; i < valids.size(); ++i){  shanghai / jingshi  shanghai > b
//			weights[i] = valids.get(i)[1];
//			values[i] = valids.get(i)[0];
//		}
//		return dpSolve3(weights, values, w); 
//	}
	
	public static int dpSolve3(int[][] warriors, int[] monster){
		int c = monster[0];
		int b = monster[1];
		int[][] dp = new int[c+1][11];
		Arrays.fill(dp, 1 << 29);
		for (int k = 0; k <= 10; ++k){
			dp[0][k] = 0;
			for (int i = 0; i < warriors.length; i++){
				int w = warriors[i][1] - k;
				if (w < 0) continue;
				for (int j = c; j >= w; j--){
					//dp[j] = Math.min(dp[j], dp[j-w]+warriors[i][0]);
				}
			}
		}
		
		return 0;	
	}
}
