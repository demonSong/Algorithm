package com.daimens.algorithm.february;

/**
 * 
 * @author DemonSong
 * 48.Rotate Image
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 *
 */
public class SolutionDay27_048 {
	//建立了新空间才完成
//	public void rotate(int[][] matrix){
//		int n = matrix.length;
//		int[][] tmp = new int[n][n];
//		
//		for (int i = 0; i < n; i++){
//			for (int j = 0; j < n; j++){
//				tmp[j][n-1-i] = matrix[i][j];
//			}
//		}
//		
//		for (int i = 0; i < n; i++){
//			for (int j = 0; j < n; j++){
//				matrix[i][j] = tmp[i][j];
//			}
//		}
//		
//	}
	
	public void rotate(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++){
			for (int j = i; j < matrix[0].length; j++){
				int tmp = 0;
				tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}
		
		for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
	}
	
}
