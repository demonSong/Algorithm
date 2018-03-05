package com.daimens.algorithm.january;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class SolutionDay29_L0773 {
	
	public int slidingPuzzle(int[][] board) {
		int n = board.length;
		int m = board[0].length;
		String ter = "123450";
		
		Queue<int[][]> q = new ArrayDeque<>();
		Set<String> vis = new HashSet<>();
		q.offer(board);
		vis.add(hash(board));
		
		int turn = 0;
		int[][] dir = {{1, 0},{-1, 0},{0, 1},{0, -1}};
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; ++i) {
				int[][] now = q.poll();
				if (hash(now).equals(ter)) {
					return turn;
				}
				// find 0
				int x = -1;
				int y = -1;
				for (int k = 0; k < n; ++k) {
					for (int l = 0; l < m; ++l) {
						if (now[k][l] == 0) {
							x = k;
							y = l;
							break;
						}
					}
				}
				
				for (int[] d : dir) {
					int nx = d[0] + x;
					int ny = d[1] + y;
					if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
						int[][] aux = clone(now);
						swap(aux, x, y, nx, ny);
						if (!vis.contains(hash(aux))) {
							q.offer(aux);
							vis.add(hash(aux));
						}
						
					}
				}
			}
			turn ++;
		}
		return -1;
    }
	
	int[][] clone(int[][] board) {
		int n = board.length;
		int m = board[0].length;
		int[][] aux = new int[n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				aux[i][j] = board[i][j];
			}
		}
		return aux;
	}
	
	String hash(int[][] board) {
		StringBuilder sb = new StringBuilder();
		int n = board.length;
		int m = board[0].length;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				sb.append(board[i][j]);
			}
		}
		return sb.toString();
	}
	
	void swap(int[][] board, int ai, int aj, int bi, int bj) {
		int tmp = board[ai][aj];
		board[ai][aj] = board[bi][bj];
		board[bi][bj] = tmp;
	}
	
}
