package com.daimens.algorithm.april;

import java.util.Stack;

/**
 * 
 * @author DemonSong
 * 
 *         85.Maximal Rectangle
 * 
 *         Given a 2D binary matrix filled with 0's and 1's, find the largest
 *         rectangle containing only 1's and return its area.
 * 
 *         For example, given the following matrix:
 * 
 *         1 0 1 0 0 1 0 1 1 1 1 1 1 1 1 1 0 0 1 0 Return 6.
 **
 */
public class SolutionDay25_085 {
	
//	public int maximalRectangle(char[][] matrix) {
//		
//		int row = matrix.length;
//		if (row == 0) return 0;
//		int col = matrix[0].length;
//		if (col == 0) return 0;
//		
//		int[][] w = new int[row+1][col+1];
//		int[][] l = new int[row+1][col+1];
//		int max = 0;
//
//		for (int j = 1; j < col+1; j++){
//			if (matrix[0][j-1] == '1'){
//				w[1][j] = 1; // 宽
//				l[1][j] = l[1][j-1] + 1; // 长
//			}
//			max = Math.max(max, w[1][j] * l[1][j]);
//		}
//		
//		for (int i = 1; i < row + 1; i++){
//			if (matrix[i-1][0] == '1'){
//				l[i][1] = 1;
//				w[i][1] = w[i-1][1] + 1;
//			}
//			
//			max = Math.max(max, w[i][1] * l[i][1]);
//		}
//		
//		for (int i = 2; i < row + 1; i++){
//			for (int j = 2; j < col + 1; j++){
//				if (matrix[i-1][j-1] == '1'){
//					w[i][j] = Math.min(w[i-1][j-1], Math.min(w[i-1][j], w[i][j-1])) + 1;
//					l[i][j] = Math.min(l[i-1][j-1], Math.min(l[i-1][j], l[i][j-1])) + 1;
//				}
//				max = Math.max(max, w[i][j] * l[i][j]);
//			}
//		}
//		
//		return max;
//    }

	public int maximalRectangle(char[][] matrix) {

		int row = matrix.length;
		if (row == 0)
			return 0;
		int col = matrix[0].length;
		if (col == 0)
			return 0;

		int[][] w = new int[row + 1][col + 1];

		for (int j = 1; j < col + 1; j++) {
			if (matrix[0][j - 1] == '1') {
				w[1][j] = 1; // 宽
			}
		}

		for (int i = 2; i < row + 1; i++) {
			for (int j = 1; j < col + 1; j++) {
				if (matrix[i - 1][j - 1] == '1') {
					w[i][j] = w[i-1][j] + 1;
				}
			}
		}

		int max = 0;
		for (int i = 1; i < row+1; i++){
			max = Math.max(max,largestRectangleArea(w[i]));
		}

		return max;
	}
	
	public int largestRectangleArea(int[] heights) {

		Stack<Integer> stack = new Stack<>();

		int max = 0;
		for (int i = 0; i <= heights.length; i++) {
			int curr = (i == heights.length) ? 0 : heights[i];
			int count = 0;
			while (!stack.isEmpty() && heights[stack.peek()] > curr) {
				int pos = stack.pop();
				max = Math.max(max, heights[pos] * (i - pos));
				heights[pos] = curr;
				count++;
			}
			i -= count;
			stack.push(i);
		}

		return max;
	}
	
	public static void main(String[] args) {
		SolutionDay25_085 day = new SolutionDay25_085();
		//char[][] matrix = {{'0','0','1','1','0','1','1','1'}};
		//char[][] matrix = {{'0'},{'0'},{'1'},{'1'},{'0'},{'1'},{'1'},{'1'}};
		char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
		day.maximalRectangle(matrix);
	}
}
