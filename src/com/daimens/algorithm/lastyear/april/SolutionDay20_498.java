package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong 
 * 
 * 498.Diagonal Traverse
 *
 *         Given a matrix of M x N elements (M rows, N columns), return all
 *         elements of the matrix in diagonal order as shown in the below image.
 * 
 *         Example: Input: [ [ 1, 2, 3 ], [ 4, 5, 6 ], [ 7, 8, 9 ] ] Output:
 *         [1,2,4,7,5,3,6,8,9] Explanation:
 * 
 *         Note: The total number of elements of the given matrix will not
 *         exceed 10,000.
 *
 */
public class SolutionDay20_498 {
	
//	public int[] findDiagonalOrder(int[][] matrix) {
//        if(matrix.length == 0 || matrix[0].length == 0) return new int[]{};
//		int row = matrix.length;
//		int col = matrix[0].length;
//		
//		int[][] array = new int[row][col + (row - 1)];
//		
//		for(int i = 0; i < array.length; i++){
//			Arrays.fill(array[i], Integer.MIN_VALUE);
//		}
//		
//		for (int i = 0; i < row; i++){
//			for (int j = 0; j < col; j++){
//				array[i][j+i] = matrix[i][j];
//			}
//		}
//		
//		int[] ans = new int[row * col];
//		int index = 0;
//		for (int i = 0; i < array[0].length; i++){
//			if (i % 2 == 0){
//				for (int j = row -1;  j >= 0; j--){
//					if (array[j][i] != Integer.MIN_VALUE)
//						ans[index++] = array[j][i];
//				}
//			}else{
//				for (int j = 0;  j < row; j++){
//					if (array[j][i] != Integer.MIN_VALUE)
//						ans[index++] = array[j][i];
//				}
//			}
//		}
//		
//		return ans;
//    }
	
	public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return new int[]{};
		int row = matrix.length;
		int col = matrix[0].length;
		
		int[] ans = new int[row * col];
		int index = 0;
		for (int i = 0; i < (col + row - 1); i++){
			if (i % 2 == 0){
				for (int j = row -1;  j >= 0; j--){
					if (i-j >= 0 && i - j < col)
						ans[index++] = matrix[j][i-j];
				}
			}else{
				for (int j = 0;  j < row; j++){
					if (i-j >= 0 && i - j < col)
						ans[index++] = matrix[j][i-j];
				}
			}
		}
		return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay20_498 day = new SolutionDay20_498();
		//int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		
		int[][] matrix = {{2},{3}};
		day.findDiagonalOrder(matrix);
	}
}
