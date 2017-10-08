package com.daimens.algorithm.october;

import java.util.HashSet;
import java.util.Set;

public class SolutionDay08_L0503 {
	
//	int[][] dir = {{1, 0},{0, 1},{-1, 0},{0, -1}};
//    int[][] dp = new int[50 * 50 + 16][4]; // four direction
//    
//    boolean[][] vis = new boolean[52][52];
//    int n, m;
//    
//    List<Integer> start = new ArrayList<>();
//    public int numDistinctIslands(int[][] grid) {
//		n = grid.length;
//		if (n == 0) return 0;
//		m = grid[0].length;
//		if (m == 0) return 0;
//		vis = new boolean[52][52];
//		int count = 0;
//		for (int i = 0; i < n; ++i) {
//			for (int j = 0; j < m; ++j) {
//				if (grid[i][j] == 1) {
//					if (!vis[i][j]) {
//						start.add(i * m + j);
//						dfs(grid, i, j);
//						count ++;
//					}
//				}
//			}
//		}
//		
//		boolean[] tt = new boolean[50 * 50 + 12];
//		for (int i = 0; i < start.size(); ++i) {
//			for (int j = 0; j < start.size(); ++j) {
//				if (i == j) continue;
//                int ni = start.get(i) / m;
//				int nj = start.get(i) % m;
//				int nx = start.get(j) / m;
//				int ny = start.get(j) % m;
//				if (go(grid, ni, nj, nx, ny, new boolean[52][52]) && !tt[start.get(j)] && !tt[start.get(i)]) {
//					tt[start.get(j)] = true;
//					count--;
//				}
//			}
//		}
//		return count;
//	}
//	
//	boolean check(int i, int j) {
//		return i >= 0 && i < n && j >= 0 && j < m;
//	}
//	
//	public int[] dfs(int[][] grid, int x, int y) {
//		vis[x][y] = true;
//		
//		for (int k = 0; k < 4; ++k) {
//			int[] d = dir[k];
//			int nx = d[0] + x;
//			int ny = d[1] + y;
//			if (check(nx, ny) && !vis[nx][ny] && grid[nx][ny] == 1) {
//				dp[x * m + y][k] = 1 + dfs(grid, nx, ny)[k]; 
//			}
//		}
//		return dp[x * m + y];
//	}
//	
//	public boolean go(int[][] grid, int i, int j, int x, int y, boolean[][] vv) {
//		vv[i][j] = true;
//		if (!check(x, y) || !same(i, j, x, y)) return false;
//		for (int k = 0; k < 4; ++k) {
//            int[] d = dir[k];
//			int ni = d[0] + i;
//			int nj = d[1] + j;
//			if (check(ni, nj) && !vv[ni][nj] && grid[ni][nj] == 1){
//				if (!go(grid, ni, nj, x + d[0], y + d[1], vv)) return false;
//			}
//		}
//		return true;
//	}
//	
//	public boolean same(int i, int j, int x, int y) {
//		for (int k = 0; k < 4; ++k) {
//			if (dp[i * m + j][k] != dp[x * m + y][k]) return false;
//		}
//		return true;
//	}
	


	int[][] dir = {{1, 0},{0, 1},{-1, 0},{0, -1}};
    int[][] dp = new int[50 * 50 + 16][5]; // four direction
    
    boolean[][] vis = new boolean[52][52];
    int n, m;
    
    Set<Integer> set = new HashSet<>();
    public int numDistinctIslands(int[][] grid) {
		n = grid.length;
		if (n == 0) return 0;
		m = grid[0].length;
		if (m == 0) return 0;
		vis = new boolean[52][52];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (grid[i][j] == 1) {
					if (!vis[i][j]) {
						si = i;
						sj = j;
						dfs(grid, i, j);
						set.add(dp[i * m + j][4]);
					}
				}
			}
		}
		
		return set.size();
	}
	
	boolean check(int i, int j) {
		return i >= 0 && i < n && j >= 0 && j < m;
	}
	
	int si, sj;
	public int[] dfs(int[][] grid, int x, int y) {
		vis[x][y] = true;
		
		for (int k = 0; k < 4; ++k) {
			int[] d = dir[k];
			int nx = d[0] + x;
			int ny = d[1] + y;
			if (check(nx, ny) && !vis[nx][ny] && grid[nx][ny] == 1) {
				dp[x * m + y][k] = 1 + dfs(grid, nx, ny)[k];
				dp[x * m + y][4] += (dp[nx * m + ny][4] + 1) * (k + 1);
			}
		}
		
		for (int k = 0; k < 4; ++k) {
			dp[x * m + y][4] += (((x - si + 1) * m + (y - sj + 1)) * 177) *  (dp[x * m + y][k] + 1) * (k + 1);
		}
		
		return dp[x * m + y];
	}
    
	public static void main(String[] args) {
		SolutionDay08_L0503 day = new SolutionDay08_L0503();
		int[][] grid = { { 1, 1, 0, 1, 1 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1 } };
		System.out.println(day.numDistinctIslands(grid));
	}
}
