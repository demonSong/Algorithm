package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 363.Max Sum of Rectangle No larger Than K
 * Given a non-empty 2D matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger
 * than k.
 * Example:
 * Given matrix = [
 * 	[1, 0,1],
 * 	[0,-2,3]
 * ]
 * 
 * The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).
 * Note:
 * 1. The rectangle inside the matrix have an area > 0.
 * 2. What if the number of rows in much larger than the number of columns?
 *
 */
public class SolutionDay07_363 {
	
//	public int maxSumSubmatrix(int[][] matrix, int k) {
//
//		int row = matrix.length, col = matrix[0].length;
//		int lenMax = Math.max(row, col);
//		int lenMin = row + col - lenMax;
//
//		int[][] tmp = new int[lenMin][lenMax];
//		if (row > col) {
//			for (int i = 0; i < row; i++) {
//				for (int j = 0; j < col; j++) {
//					tmp[j][i] = matrix[i][j];
//				}
//			}
//
//		}
//
//		else {
//			tmp = matrix;
//		}
//
//		int max = 0;
//		for (int i = 0; i < lenMin; i++) {
//
//			int[] oneMatrix = tmp[i];
//
//			for (int j = i; j < lenMin; j++) {
//
//				// 得到oneMatrix
//				for (int z = 0; z < lenMax; z++) {
//					oneMatrix[z] = tmp[j][z] + oneMatrix[z];
//				}
//
//				int[] sums = new int[oneMatrix.length];
//				sums[0] = oneMatrix[0];
//				for (int z = 1; z < lenMax; z++) {
//					sums[z] = sums[z - 1] + oneMatrix[z];
//				}
//
//				// binarySearch
//				for (int z = 0; z + 1 < lenMax; z++) {
//					int sum = binarySearch(sums, k, z + 1, sums.length - 1, z);
//					max = Math.max(max, sum);
//				}
//
//			}
//		}
//
//		return max;
//	}
	
	 public int maxSumSubmatrix(int[][] matrix, int k) {
		 return 0;
	 }
	 
	private int binarySearch(int[] sums, int target, int lf, int rt, int i) {

		while (lf < rt) {
			int mid = lf + (rt + 1 - lf) / 2;
			if (sums[mid] - sums[i] > target) {
				rt = mid - 1;
			} else {
				lf = mid;
			}
		}

		if (sums[lf] - sums[i] <= target)
			return sums[lf] - sums[i];

		return 0;
	}
	
	public static void main(String[] args) {
		SolutionDay07_363 day = new SolutionDay07_363();
		
		int[][] nums = {{2,2,-1}};
		day.maxSumSubmatrix(nums, 3);
	}

}
