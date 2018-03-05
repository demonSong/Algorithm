package com.daimens.algorithm.january;

public class SolutionDay12_L0764 {

	public int orderOfLargestPlusSign(int N, int[][] mines) {
		int[][][] grid = new int[N][N][4];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				for (int k = 0; k < 4; ++k) {
					grid[i][j][k] = 1;
				}
			}
		}
		
		for (int[] mine : mines) {
			int r = mine[0];
			int c = mine[1];
			for (int k = 0; k < 4; ++k) grid[r][c][k] = 0;
		}
		
		for (int i = 0; i < N; ++i) {
			for (int j = 1; j < N; ++j) {
				if (grid[i][j][0] == 1)
					grid[i][j][0] += grid[i][j - 1][0]; 
			}
			for (int j = N - 2; j >= 0; --j) {
				if (grid[i][j][1] == 1)
					grid[i][j][1] += grid[i][j + 1][1];
			}
		}
		
		for (int j = 0; j < N; ++j) {
			for (int i = 1; i < N; ++i) {
				if (grid[i][j][2] == 1)
					grid[i][j][2] += grid[i - 1][j][2];
			}
			for (int i = N - 2; i >= 0; --i) {
				if (grid[i][j][3] == 1)
					grid[i][j][3] += grid[i + 1][j][3];
			}
		}
		
		int ans = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				int order = Math.min(Math.min(grid[i][j][0], grid[i][j][1]), Math.min(grid[i][j][2], grid[i][j][3]));
				ans = Math.max(ans, order);
			}
		}
		return ans;
	}

	

	public static void main(String[] args) {
		SolutionDay12_L0764 day = new SolutionDay12_L0764();
		int N = 10;
		int[][] mines = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 7 }, { 1, 2 }, { 1, 3 }, { 1, 9 }, { 2, 3 }, { 2, 5 },
				{ 2, 7 }, { 2, 8 }, { 3, 2 }, { 3, 5 }, { 3, 7 }, { 4, 2 }, { 4, 3 }, { 4, 5 }, { 4, 7 }, { 5, 1 },
				{ 5, 4 }, { 5, 8 }, { 5, 9 }, { 7, 2 }, { 7, 5 }, { 7, 7 }, { 7, 8 }, { 8, 5 }, { 8, 8 }, { 9, 0 },
				{ 9, 1 }, { 9, 2 }, { 9, 8 } };
		System.out.println(day.orderOfLargestPlusSign(N, mines));
	}
	
	static class D{
		
		public static void pp(int[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
		
		public static void pp(char[][] board, int row, int col) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < row; ++i) {
				for (int j = 0; j < col; ++j) {
					sb.append(board[i][j] + (j + 1 == col ? "\n" : " "));
				}
			}
			System.out.println(sb.toString());
		}
	}
}

