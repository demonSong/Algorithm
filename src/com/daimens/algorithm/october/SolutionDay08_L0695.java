package com.daimens.algorithm.october;

public class SolutionDay08_L0695 {
	
	int[][] dir = {{1, 0},{-1, 0},{0, 1},{0, -1}};
    public int maxAreaOfIsland(int[][] grid) {
    	int n = grid.length;
    	if (n == 0) return 0;
    	int m = grid[0].length;
    	if (m == 0) return 0;
    	
    	int max = 0;
    	for (int i = 0; i < n; ++i) {
    		for (int j = 0; j < m; ++j) {
    			if (grid[i][j] == 1) {
    				max = Math.max(max, dfs(grid, i, j, n, m, new boolean[n][m]));
    			}
    		}
    	}
    	
    	return max;
    }
    
    public int dfs(int[][] grid, int i, int j, int n, int m, boolean[][] vis) {
    	int res = 1;
    	vis[i][j] = true;
    	for (int[] d : dir) {
    		int nx = i + d[0];
    		int ny = j + d[1];
    		if (nx >= 0 && nx < n && ny >= 0 && ny < m && !vis[nx][ny] && grid[nx][ny] == 1) {
    			res += dfs(grid, nx, ny, n, m, vis);
    		}
    	}
    	return res;
    }
	
	public static void main(String[] args) {
		SolutionDay08_L0695 day = new SolutionDay08_L0695();
	}
}
