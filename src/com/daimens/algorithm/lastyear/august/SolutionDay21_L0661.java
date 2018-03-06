package com.daimens.algorithm.august;

public class SolutionDay21_L0661 {
	
	int[][] dir = {{1,0},{-1,0},{0,1},{0,-1},{0,0},{1,1},{-1,1},{-1,-1},{1,-1}};
	public int[][] imageSmoother(int[][] M) {
		int n = M.length;
		int m = M[0].length;
		int[][] ans = new int[n][m];
		for (int i = 0; i < n; ++i){
			for (int j = 0; j < m; ++j){
				int sum = 0;
				int cnt = 0;
				for (int[] d : dir){
					int nx = i + d[0];
					int ny = j + d[1];
					if (nx >= 0 && nx < n && ny >= 0 && ny < m){
						cnt ++;
						sum += M[nx][ny];
					}
				}
				ans[i][j] = sum / cnt;
			}
		}
		return ans;
    }
//	
//	public int[][] clone(int[][] M){
//		int n = M.length;
//		int m = M[0].length;
//		int[][] clone = new int[n][m];
//		for (int i = 0; i < n; ++i){
//			for (int j = 0; j < m; ++j){
//				clone[i][j] = M[i][j];
//			}
//		}
//		return clone;
//	}
	
}
