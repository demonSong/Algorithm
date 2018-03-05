package com.daimens.algorithm.january;

public class SolutionDay21_L0766 {
	
//    public boolean isToeplitzMatrix(int[][] matrix) {
//        int n = matrix.length;
//        int m = matrix[0].length;
//        
//        for (int j = 0; j < m; ++j) {
//        	int i = 0;
//        	while (i + 1 < n && j + 1 < m) {
//        		if (matrix[i + 1][j + 1] != matrix[i][j]) return false;
//        		i ++;
//        		j ++;
//        	}
//        }
//        
//        for (int i = 0; i < n; ++i) {
//        	int j = 0;
//        	while (i + 1 < n && j + 1 < m) {
//        		if (matrix[i + 1][j + 1] != matrix[i][j]) return false;
//        		i ++;
//        		j ++;
//        	}
//        }
//    	return true;
//    }
	
    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n - 1; ++i) {
        	for (int j = 0; j < m - 1; ++j) {
        		if (matrix[i][j] != matrix[i + 1][j + 1]) return false;
        	}
        }
        return true;
    }
	
	public static void main(String[] args) {
		SolutionDay21_L0766 day = new SolutionDay21_L0766();
	}
}
