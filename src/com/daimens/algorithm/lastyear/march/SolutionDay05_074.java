package com.daimens.algorithm.march;

/**
 * 
 * @author Demon Song
 * 74.Search a 2D Matrix
 * Write an efficient algorithm that searches for a value in an m x n matrix. 
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * Consider the following matrix:
 * [
 * 	[1,   3,  5,  7],
 *  [10, 11, 16, 20],
 *  [23, 30, 34, 50]
 * ]
 * 
 * Given target = 3, return true.
 *
 */
public class SolutionDay05_074 {
	
//	public boolean searchMatrix(int[][] matrix, int target) {
//		if(matrix.length == 0 || matrix[0].length == 0) return false;
//		
//		//先搜寻行 在搜寻列
//		int row_s = 0, row_e = matrix.length-1;
//		
//		while(row_s < row_e){
//			int row_m = row_s + (row_e - row_s) /2;
//			if(matrix[row_m][0] < target){
//				row_s = row_m +1;
//			}else{
//				row_e = row_m; //以防数组越界,找到的row_e的只有一种情况，要么target <= matrix[row_e]
//			}
//		}
//		
//		//循环结束end = start
//		if(matrix[row_e][0] > target){
//			//进行行元素的binary search
//			if(row_e -1 < 0) return false;
//			
//			int col_s = 0, col_e = matrix[row_e-1].length-1;
//			
//			while(col_s < col_e){
//				int col_m = col_s + (col_e-col_s) /2;
//				if(matrix[row_e-1][col_m] < target){
//					col_s = col_m + 1;
//				}else{
//					col_e = col_m;
//				}
//			}
//			
//			return matrix[row_e-1][col_e] == target ? true : false;
//		}
//		if(matrix[row_e][0] < target){
//		    //进行行元素的binary search
//			//if(row_e + 1 > matrix.length-1) return false;
//			
//			int col_s = 0, col_e = matrix[row_e].length-1;
//			
//			while(col_s < col_e){
//				int col_m = col_s + (col_e-col_s) /2;
//				if(matrix[row_e][col_m] < target){
//					col_s = col_m + 1;
//				}else{
//					col_e = col_m;
//				}
//			}
//			
//			return matrix[row_e][col_e] == target ? true : false;
//		}
//		else{
//	        return true;
//		}
//		
//    }
	
	//just treat as a sorted list
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix.length == 0 || matrix[0].length == 0) return false;
		
		int n = matrix.length;
		int m = matrix[0].length;
		
		int l = 0, r = m * n -1;
		
		while (l != r) {
			int mid = l + (r - l) / 2;
			if (matrix[mid / m][mid % m] < target) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		
		return matrix[r / m][r % m] == target;
	}
		
	
	public static void main(String[] args) {
		SolutionDay05_074 day = new SolutionDay05_074();
		int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		day.searchMatrix(matrix, 13);
	}
}
