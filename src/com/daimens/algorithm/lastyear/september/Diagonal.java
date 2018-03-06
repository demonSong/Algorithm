package com.daimens.algorithm.september;

public class Diagonal {

	public static void main(String[] args) {
		int n = 8;
		int w = 10;
		String[][] board = new String[n][w];
		
		int cnt = 1;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < w; ++j) {
				board[i][j] = cnt <= 9 ? "0" + cnt : cnt + "";
				cnt ++;
			}
		}
		
		String[][] trans = new String[n + w][n + w];
		for (int i = 0; i < n + w; ++i) {
			for (int j = 0; j < n + w; ++j) {
				trans[i][j] = "  ";
			}
		}
		
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < w; ++j) {
				if (((i + j) & 1) == 0) {
					int ni = i + j;
					int nj = j - i + n;
					trans[ni][nj] = board[i][j];
				}
			}
		}
		
		pp(board);
		pp(trans);
	}
	
	public static void pp(String[][] board) {
		StringBuilder sb = new StringBuilder();
		int n = board.length;
		int m = board[0].length;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				sb.append(board[i][j] + ((j + 1 == m) ? "\n" : " "));
			}
		}
		System.out.println(sb.toString());
	}
}	
