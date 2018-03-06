package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         329. Longest Increasing Path in a Matrix
 * 
 *         Given an integer matrix, find the length of the longest increasing
 *         path.
 * 
 *         From each cell, you can either move to four directions: left, right,
 *         up or down. You may NOT move diagonally or move outside of the
 *         boundary (i.e. wrap-around is not allowed).
 * 
 *         Example 1:
 * 
 *         nums = [ [9,9,4], [6,6,8], [2,1,1] ] Return 4 The longest increasing
 *         path is [1, 2, 6, 9].
 * 
 *         Example 2:
 * 
 *         nums = [ [3,4,5], [3,2,6], [2,2,1] ] Return 4 The longest increasing
 *         path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * 
 *
 */
public class SolutionDay10_L0329 {
	
	
//	public int longestIncreasingPath(int[][] matrix) {
//		
//		row = matrix.length;
//		if (row == 0) return 0;
//		col = matrix[0].length;
//		if (col == 0) return 0;
//		
//		max = 0;
//		for (int i = 0; i < row; i++) {
//			for (int j = 0; j < col; j++) {
//				count = 1;
//				dfs(matrix,i,j);
//			}
//		}
//        return max;
//    }
//	
//	static int count = 1;
//	static int max = 0;
//	static int row,col;
//	static final int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
//	
//	
//	private void dfs(int[][] matrix, int cx, int cy){
//		for (int i = 0; i < 4; i++){
//			int nx = cx + direction[i][0];
//			int ny = cy + direction[i][1];
//			max = Math.max(max, count);
//			if (nx >= 0 && nx < row && ny >=0 && ny < col && matrix[nx][ny] > matrix[cx][cy]){
//				count++;
//				dfs(matrix, nx, ny);
//				count--;
//			}
//		}
//	}
	
	public int longestIncreasingPath(int[][] matrix) {
		
		int row = matrix.length;
		if (row == 0) return 0;
		int col = matrix[0].length;
		if (col == 0) return 0;
		
		int[][] cache = new int[row][col];
		
		int max = 1;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int len = dfs(matrix, i, j, row, col, cache);
				max = Math.max(len, max);
			}
		}
        return max;
    }
	
	final static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	
	private int dfs(int[][] matrix, int i , int j, int row, int col, int[][] cache){
		if (cache[i][j] != 0) return cache[i][j];
		int max = 1;
		for (int[] dir : direction){
			int nx = i + dir[0], ny = j + dir[1];
			if (nx < 0 || nx >= row || ny < 0 || ny >= col || matrix[nx][ny] <= matrix[i][j]) continue;
			int len = 1 + dfs(matrix, nx, ny, row, col, cache);
			max = Math.max(max, len);
		}
		return cache[i][j] = max;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(-1<<30);
		SolutionDay10_L0329 day = new SolutionDay10_L0329();
		int[][] nums = {{9,9,4},{6,6,8},{2,1,1}};
		day.longestIncreasingPath(nums);
	}
}
