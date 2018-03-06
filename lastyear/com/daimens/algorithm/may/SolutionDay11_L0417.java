package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         417.Pacific Atlantic Water Flow
 * 
 *         Given an m x n matrix of non-negative integers representing the
 *         height of each unit cell in a continent, the "Pacific ocean" touches
 *         the left and top edges of the matrix and the "Atlantic ocean" touches
 *         the right and bottom edges.
 * 
 *         Water can only flow in four directions (up, down, left, or right)
 *         from a cell to another one with height equal or lower.
 * 
 *         Find the list of grid coordinates where water can flow to both the
 *         Pacific and Atlantic ocean.
 * 
 *         Note: The order of returned grid coordinates does not matter. Both m
 *         and n are less than 150. Example:
 * 
 *         Given the following 5x5 matrix:
 * 
 *         Pacific ~ ~ ~ ~ ~ ~ 1 2 2 3 (5) * ~ 3 2 3 (4) (4) * ~ 2 4 (5) 3 1 * ~
 *         (6) (7) 1 4 5 * ~ (5) 1 1 2 4 * * * * * Atlantic
 * 
 *         Return:
 * 
 *         [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions
 *         with parentheses in above matrix).
 *
 */
public class SolutionDay11_L0417 {
	
//	public List<int[]> pacificAtlantic(int[][] matrix) {
//        int row = matrix.length;
//        if (row == 0) return new ArrayList<int[]>();
//        int col = matrix[0].length;
//        if (col == 0) return new ArrayList<int[]>();
//        
//        List<int[]> ans = new ArrayList<>();
//        boolean[][] visited = new boolean[row][col];
//        for (int i = 0; i < row; i++){
//        	for (int j = 0; j < col; j++){
//        		a = false;
//        		p = false;
//        		visited = new boolean[row][col];
//        		dfs(ans, matrix, i, j, row, col, new int[]{i,j},visited);
//        	}
//        }
//        return ans;
//    }
//	
//	int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
//	static boolean a = false;
//	static boolean p = false;
//	
//	private void dfs(List<int[]> ans, int[][] matrix, int i, int j, int row, int col, int[] local, boolean[][] visited){
//		if ((i != local[0] || j != local[1]) && (i < 0 || i >= row || j < 0 || j >= col || matrix[i][j] >= matrix[local[0]][local[1]] || visited[i][j])){
//			if (i < 0 || j < 0) a = true;
//			if (i >= row || j >= col) p = true;
//			
//			if (a && p && !ans.contains(local)){
//				ans.add(local);
//			}
//			
//			return;
//		}
//		
//		visited[i][j] = true;
//		for (int[] dir : direction){
//			int nx = i + dir[0];
//			int ny = j + dir[1];
//			dfs(ans, matrix, nx, ny, row, col, local, visited);
//		}
//	}
	
	public List<int[]> pacificAtlantic(int[][] matrix) {
        int row = matrix.length;
        if (row == 0) return new ArrayList<int[]>();
        int col = matrix[0].length;
        if (col == 0) return new ArrayList<int[]>();
        
        List<int[]> ans = new ArrayList<>();
        boolean[][] a = new boolean[row][col];
        boolean[][] p = new boolean[row][col];
        
        for (int i = 0; i < row; i++){
        	dfs(matrix, i, 0, Integer.MIN_VALUE, a);
        	dfs(matrix, i, col-1, Integer.MIN_VALUE, p);
        }
        
        for (int j = 0; j < col; j++){
        	dfs(matrix, 0, j, Integer.MIN_VALUE, a);
        	dfs(matrix, row-1, j, Integer.MIN_VALUE, p);
        }
        
        
        for (int i = 0; i < row; i++){
        	for (int j = 0; j < col; j++){
        		if (a[i][j] && p[i][j]){
        			ans.add(new int[]{i,j});
        		}
        	}
        }
        return ans;
    }
	
	int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	private void dfs(int[][] matrix, int i, int j, int height, boolean[][] visited){
		int row = matrix.length, col = matrix[0].length;
		if (i < 0 || i >= row || j < 0 || j >= col || matrix[i][j] < height || visited[i][j]){
			return;
		}
		visited[i][j] = true;
		for (int[] d : dir){
			dfs(matrix, i+d[0], j+d[1], matrix[i][j], visited);
		}
	}
	
	public static void main(String[] args) {
		SolutionDay11_L0417 day = new SolutionDay11_L0417();
		int[][] matrix = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
		day.pacificAtlantic(matrix);
	}

}
