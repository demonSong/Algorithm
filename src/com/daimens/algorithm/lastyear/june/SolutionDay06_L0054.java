package com.daimens.algorithm.june;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 
 *         54. Spiral Matrix
 * 
 *         Given a matrix of m x n elements (m rows, n columns), return all
 *         elements of the matrix in spiral order.
 * 
 *         For example, Given the following matrix:
 * 
 *         [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] You should return
 *         [1,2,3,6,9,8,7,4,5].
 *
 */
public class SolutionDay06_L0054 {

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();
		if (matrix.length == 0 || matrix[0].length == 0) return ans;
		int row_s = 0;
		int col_s = 0;
		int row_e = matrix.length - 1;
		int col_e = matrix[0].length - 1;

		while (row_s <= row_e && col_s <= col_e){
			for (int i = col_s; i <= col_e; ++i){
				ans.add(matrix[row_s][i]);
			}
			row_s++;
			
			for (int i = row_s; i <= row_e; ++i){
				ans.add(matrix[i][col_e]);
			}
			col_e--;
			
			if (row_s <= row_e){
				for (int i = col_e; i >= col_s; --i){
					ans.add(matrix[row_e][i]);
				}
				row_e--;
			}
			
			if (col_s <= col_e){
				for (int i = row_e; i >= row_s; --i){
					ans.add(matrix[i][col_s]);
				}
				col_s++;
			}
		}
		
		return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay06_L0054 day = new SolutionDay06_L0054();
		int[][] matrix = {{2,3}};
		day.spiralOrder(matrix);
	}
	
}
