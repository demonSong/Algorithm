package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DemonSong
 * 73.Set Matrix Zeroes
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m+n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 */
public class SolutionDay06_073 {
	public void setZeroes(int[][] matrix) {
		//记录0元素的 i 和  j
		
		int n = matrix.length;
		if(n == 0) return;
		int m = matrix[0].length;
		if(m == 0) return;
		
		List<Integer> adrs = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(matrix[i][j] == 0){
					//记录行号与列号
					adrs.add(m * i + j); //只记录其中一个 adr，在进行行扫描和列扫描时，如果遇到0元素，把它放入队列，但没扫描到的0元素怎么解决？
				}
			}
		}
		
		for (int adr : adrs) {
			int row = adr / m;
			int col = adr % m;
			
			for (int i = 0; i < m; i++){
				matrix[row][i] = 0;
			}
			
			for (int j = 0; j < n; j++){
				matrix[j][col] = 0;
			}
		}
    }
	
	//把除第一行和第一列的0元素全部搬移到第一行和第一列中去，典型的归简
}
