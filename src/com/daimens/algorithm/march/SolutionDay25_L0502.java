package com.daimens.algorithm.march;

public class SolutionDay25_L0502 {
	
    public int maxIncreaseKeepingSkyline(int[][] grid) {
    	int n = grid.length;
    	if (n == 0) return 0;
    	int m = grid[0].length;
    	if (m == 0) return 0;
    	int[] hor = new int[m];
    	for (int i = 0; i < m; ++i) {
    		for (int j = 0; j < n; ++j) {
    			hor[i] = Math.max(hor[i], grid[j][i]);
    		}
    	}
    	int[] vec = new int[n];
    	for (int i = 0; i < n; ++i) {
    		for (int j = 0; j < m; ++j) {
    			vec[i] = Math.max(vec[i], grid[i][j]);
    		}
    	}
    	
    	int sum = 0;
    	for (int i = 0; i < n; ++i) {
    		for (int j = 0; j < m; ++j) {
    			int val = Math.min(hor[j], vec[i]);
    			sum += val - grid[i][j];
    		}
    	}
    	
    	return sum;
    }
    
	
	public static void main(String[] args) {
		SolutionDay25_L0502 day = new SolutionDay25_L0502();
	}
}
