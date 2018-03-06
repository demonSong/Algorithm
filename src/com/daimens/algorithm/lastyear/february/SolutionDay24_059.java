package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 59.Spiral Matrix II
 * Given an integer n,generate a square matrix filled with elements from 1 to n^2 in spiral order.
 * For example,
 * Given n = 3,
 * You should return the following matrix:
 * [
 * 	[ 1, 2, 3],
 * 	[ 8, 9, 4],
 * 	[ 7, 6, 5]
 * ]
 *
 */
public class SolutionDay24_059 {
	//不需要考虑元素之间的变化，也不用总结坐标的变化情况，可以增加一些中间变量来解决问题。
	public int[][] generateMatrix(int n) {
        // Declaration
        int[][] matrix = new int[n][n];
        
        // Edge Case
        if (n == 0) {
            return matrix;
        }
        
        // Normal Case
        int rowStart = 0;
        int rowEnd = n-1;
        int colStart = 0;
        int colEnd = n-1;
        int num = 1; //change
        
        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i ++) {
                matrix[rowStart][i] = num ++; //change
            }
            rowStart ++;
            
            for (int i = rowStart; i <= rowEnd; i ++) {
                matrix[i][colEnd] = num ++; //change
            }
            colEnd --;
            
            for (int i = colEnd; i >= colStart; i --) {
                if (rowStart <= rowEnd)
                    matrix[rowEnd][i] = num ++; //change
            }
            rowEnd --;
            
            for (int i = rowEnd; i >= rowStart; i --) {
                if (colStart <= colEnd)
                    matrix[i][colStart] = num ++; //change
            }
            colStart ++;
        }
        
        return matrix;
    }
}
