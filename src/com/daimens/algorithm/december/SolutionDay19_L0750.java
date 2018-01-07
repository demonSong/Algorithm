package com.daimens.algorithm.december;

public class SolutionDay19_L0750 {
	
    public int countCornerRectangles(int[][] grid) {
    	int n = grid.length;
    	int m = grid[0].length;
    	int ret = 0;
    	for (int i = 0; i < n; ++i) {
    		for (int j = i + 1; j < n; ++j) {
    			int np = 0;
    			for (int k = 0; k < m; ++k) {
    				if (grid[i][k] == 1 && grid[j][k] == 1) {
    					np ++;
    				}
    			}
    			ret += np * (np - 1) / 2;
    		}
    	}
    	return ret;
    }
	
}
