package com.daimens.algorithm.feburary;

public class SolutionDay04_L0778 {
	
    public int swimInWater(int[][] grid) {
    	N = grid.length;
    	int lf = 0;
    	int rt = N * N;
    	while (lf < rt) {
    		int mid = lf + (rt - lf) / 2;
    		if (go(grid, 0, 0, new boolean[N][N], mid)) {
    			rt = mid;
    		}
    		else {
    			lf = mid + 1;
    		}
    	}
    	
    	return rt;
//    	for (int i = 0; i <= N * N; ++i) {
//    		if (go(grid, 0, 0, new boolean[N][N], i)) {
//    			return i;
//    		}
//    	}
//    	return -1;
    }
    
    int[][] dir = {{-1, 0},{0, 1},{0, -1},{1, 0}};
    int N = 0;
    boolean go(int[][] grid, int i, int j, boolean[][] vis, int mid) {
    	if (i == N - 1 && j == N - 1) return true;
    	vis[i][j] = true;
    	for (int[] d : dir) {
    		int ni = i + d[0];
    		int nj = j + d[1];
    		if (ni >= 0 && ni < N && nj >= 0 && nj < N && !vis[ni][nj] && Math.max(grid[ni][nj], mid) == Math.max(grid[i][j], mid)) {
    			if (go(grid, ni, nj, vis, mid)) return true;
    		}
    	}
    	return false;
    }
	
	public static void main(String[] args) {
		SolutionDay04_L0778 day = new SolutionDay04_L0778();
		int[][] grid = {{0, 2},{1, 3}};
		System.out.println(day.swimInWater(grid));
	}
}
