package com.daimens.algorithm.recursion;

import com.daimens.algorithm.utils.Bag;
import com.daimens.algorithm.utils.LoadData;

public class DynamicProgramming {
	
	public static void main(String[] args) {
		Bag[] bag = LoadData.loadAll01BagData();
		
		
		for (int i = 0; i < bag.length; i++){
			int c = bag[i].capacity;
			int[] w = bag[i].w;
			int[] v = bag[i].v;
			System.out.println("测试样例" +(i+1)+":" + (isRight(dpSolve3(w, v, c), bag[i]) ? "正确" : "错误"));
		}
		
	}
	
	public static boolean isRight(int solveAnswer, Bag bag){
		return solveAnswer == bag.answer;
	}
	
	
	static int[][] dp = null;
	public static int solve(int[] w, int[] v, int c){
		dp = new int[w.length+1][c+1];
		return select2(0, w, v, c, 0);
	}
	
	
	private static int select1(int start,int[] w, int[] v, int c){
		
		if (dp[start][c] > 0){
			return dp[start][c];
		}
		
		int value = 0;
		if (start == w.length){
			value = 0;
		}
		
		else if (c < w[start]){
			value = select1(start+1, w, v, c);
		}
		
		else{
			value = Math.max(select1(start + 1, w, v, c),select1(start+1, w, v, c-w[start])+v[start]);
		}
		
		return dp[start][c] = value;
	}
	
	private static int select2(int start, int[] w, int[] v, int c, int sum){
		if (dp[start][c] > 0){
			return dp[start][c];
		}
		
		
		int value = 0;
		if (start == w.length){
			value = sum;
		}
		
		else if (c < w[start]){
			value = select2(start + 1, w, v, c, sum);
		}
		
		else{
			value = Math.max(select2(start + 1, w, v, c, sum), select2(start + 1, w, v, c-w[start], sum + v[start]));
		}
		
		return dp[start][c] = value;
	}
	
	public static int dpSolve1(int[] w, int[] v, int c){
		int[][] dp = new int[w.length + 1][c + 1];
		
		for (int j = 0; j <= c; j++){
			dp[w.length][j] = 0;
		}
		
		for (int i = w.length-1; i>= 0; i--){
			for (int j = 0; j <= c; j++){
				if (w[i] > j){
					dp[i][j] = dp[i+1][j];
				}
				else{
					dp[i][j] = Math.max(dp[i+1][j], dp[i+1][j-w[i]] + v[i]);
				}
			}
		}
		
		return dp[0][c];
	}
	
	public static int dpSolve2(int[] w, int[] v, int c) {
		int[][] dp = new int[w.length + 1][c + 1];

		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j <= c; j++) {
				if (w[i] > j) {
					dp[i + 1][j] = dp[i][j];
				} else {
					dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
				}
			}
		}

		return dp[w.length][c];
	}
	
	public static int dpSolve3(int[] w, int[] v, int c){
		int[] dp = new int[c+1];
		for (int i = 0; i < w.length; i++){
			for (int j = c; j >= w[i]; j--){
				dp[j] = Math.max(dp[j], dp[j-w[i]]+v[i]);
			}
		}
		return dp[c];
	}

}
