package com.daimens.algorithm.september;

import java.util.Arrays;
import java.util.Scanner;

public class SolutionDay02_P1088 {
	
	static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		new SolutionDay02_P1088().solve();
	}
	
	static final int MAX_N = 100 + 16;
	static final int[][] dir = {{1, 0},{-1, 0},{0, 1},{0, -1}};
	int[][] dp;
	
	class Pair implements Comparable<Pair>{
		int val;
		int id;
		
		public Pair(int val, int id){
			this.val = val;
			this.id  = id;
		}

		@Override
		public int compareTo(Pair o) {
			return o.val - this.val;
		}
	
		@Override
		public String toString() {
			return "" + val;
		}
	}
	
	void solve() {
		while (in.hasNext()) {
			dp = new int[MAX_N][MAX_N];
			int R = in.nextInt();
			int C = in.nextInt();
			int[][] board = new int[R][C];
			Pair[] nodes = new Pair[R * C];
			for (int i = 0; i < R; ++i){
				for (int j = 0; j < C; ++j){
					board[i][j] = in.nextInt();
					nodes[i * C + j] = new Pair(board[i][j], i * C + j);
				}
			}
			
			for (int i = 0; i < MAX_N; ++i) Arrays.fill(dp[i], 1);
			Arrays.sort(nodes);
			int max = 1;
			for (Pair node : nodes){
				int x = node.id / C;
				int y = node.id % C;
				
				for (int[] d : dir){
					int nx = d[0] + x;
					int ny = d[1] + y;
					if (nx >= 0 && nx < R && ny >= 0 && ny < C){
						if (board[nx][ny] < board[x][y]){
							dp[nx][ny] = Math.max(dp[nx][ny], dp[x][y] + 1);
							max = Math.max(max, dp[nx][ny]);
						}
					}
				}
			}
			System.out.println(max);
		}
	}

}
