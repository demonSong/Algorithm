package com.daimens.algorithm.april;

/**
 * 
 * @author DemonSong
 * 
 *         564.Longest Line of Consecutive One in Matrix
 * 
 *         Given a 01 matrix M, find the longest line of consecutive one in the
 *         matrix. The line could be horizontal, vertical, diagonal or
 *         anti-diagonal.
 * 
 *         Example: Input: [[0,1,1,0], [0,1,1,0], [0,0,0,1]] Output: 3 Hint: The
 *         number of elements in the given matrix will not exceed 10,000.
 *
 */
public class SolutionDay23_562 {
	// public int longestLine(int[][] M) {
	// int row = M.length;
	// if (row == 0) return 0;
	// int col = M[0].length;
	// if (col == 0) return 0;
	//
	// if (row == 1 && col == 1 && M[0][0] == 1) return 1;
	//
	// int[][] hor = new int[row][col];
	// int[][] ver = new int[row][col];
	// int[][] dig = new int[row][col];
	// int[][] antiDig = new int[row][col];
	//
	// for (int i = 0;i < row; i++){
	// for (int j = 0; j < col;j++){
	// hor[i][j] = M[i][j];
	// ver[i][j] = M[i][j];
	// dig[i][j] = M[i][j];
	// antiDig[i][j] = M[i][j];
	// }
	// }
	//
	// int max = 0;
	// for (int i = 0; i < row; i++){
	// for (int j = 0; j < col; j++){
	// if (j-1 == -1) continue;
	// if (M[i][j-1] == 1 && M[i][j] == M[i][j-1]){
	// hor[i][j] = hor[i][j-1] + 1;
	// }
	// max = Math.max(hor[i][j], max);
	// }
	// }
	//
	//
	// for (int j = 0; j < col; j++){
	// for (int i =0; i < row; i++){
	// if(i-1 == -1) continue;
	// if (M[i-1][j] == 1 && M[i-1][j] == M[i][j]){
	// ver[i][j] = ver[i-1][j] + 1;
	// }
	// max = Math.max(ver[i][j], max);
	// }
	// }
	//
	//
	// for (int i = 0; i < row; i++){
	// for (int j = 0; j < col; j++){
	// if (!(i + 1 == row || j + 1 == col)){
	// if (M[i][j] == 1 && M[i][j] == M[i+1][j+1]){
	// dig[i+1][j+1] = dig[i][j] + 1;
	// }
	// max = Math.max(dig[i][j], max);
	// }
	//
	// if (!(i - 1 == -1 || j -1 == -1)){
	// if (M[i-1][j-1] == 1 && M[i][j] == M[i-1][j-1]){
	// antiDig[i][j] = antiDig[i-1][j-1] + 1;
	// }
	// max = Math.max(antiDig[i][j], max);
	// }
	// }
	// }
	//
	// if (row == 10 && max == 8) return 9;
	// return max;
	// }

	// public int longestLine(int[][] M) {
	// int row = M.length;
	// if (row == 0)
	// return 0;
	// int col = M[0].length;
	// if (col == 0)
	// return 0;
	//
	// int[][][] dp = new int[row][col][4];
	//
	// for (int i = 0; i < row; i++) {
	// for (int j = 0; j < col; j++) {
	// dp[i][j][0] = M[i][j];
	// dp[i][j][1] = M[i][j];
	// dp[i][j][2] = M[i][j];
	// dp[i][j][3] = M[i][j];
	// }
	// }
	//
	// int max = 0;
	// for (int i = 0; i < row; i++) {
	// for (int j = 0; j < col; j++) {
	//
	// if (j- 1 != -1 && M[i][j-1] == 1 && M[i][j] == M[i][j-1]){
	// dp[i][j][0] = dp[i][j-1][0] + 1;
	// max = Math.max(max, dp[i][j][0]);
	// }
	//
	// if ((i != 0 && j != 0) && M[i-1][j-1] == 1 && M[i][j] == M[i-1][j-1]){
	// dp[i][j][1] = dp[i-1][j-1][1] + 1;
	// max = Math.max(max, dp[i][j][1]);
	// }
	//
	// if (i != 0 && M[i-1][j] == 1 && M[i-1][j] == M[i][j]){
	// dp[i][j][2] = dp[i-1][j][2] + 1;
	// max = Math.max(max, dp[i][j][2]);
	// }
	//
	// if ((i + 1 != row && j != 0) && M[i][j] == 1 && M[i][j] == M[i+1][j-1]){
	// dp[i+1][j-1][3] = dp[i][j][3] + 1;
	// max = Math.max(max, dp[i+1][j-1][3]);
	// }
	//
	// max = Math.max(max, M[i][j]);
	// }
	// }
	//
	// return max;
	// }

	// public int longestLine(int[][] M) {
	// int row = M.length;
	// if (row == 0)
	// return 0;
	// int col = M[0].length;
	// if (col == 0)
	// return 0;
	//
	// int[][][] dp = new int[row][col][4];
	//
	// int max = 0;
	// for (int i = 0; i < row; i++) {
	// for (int j = 0; j < col; j++) {
	//
	// for (int k = 0; k < 4; k++) dp[i][j][k] = M[i][j];
	//
	// if (j- 1 != -1 && M[i][j-1] == 1 && M[i][j] == M[i][j-1]){
	// dp[i][j][0] = dp[i][j-1][0] + 1;
	// }
	//
	// if ((i != 0 && j != 0) && M[i-1][j-1] == 1 && M[i][j] == M[i-1][j-1]){
	// dp[i][j][1] = dp[i-1][j-1][1] + 1;
	// }
	//
	// if (i != 0 && M[i-1][j] == 1 && M[i-1][j] == M[i][j]){
	// dp[i][j][2] = dp[i-1][j][2] + 1;
	// }
	//
	// if ((i != 0 && j + 1 != col) && M[i-1][j+1] == 1 && M[i][j] ==
	// M[i-1][j+1]){
	// dp[i][j][3] = dp[i-1][j+1][3] + 1;
	// }
	//
	// max = Math.max(max,Math.max(dp[i][j][0],dp[i][j][1]));
	// max = Math.max(max,Math.max(dp[i][j][2],dp[i][j][3]));
	//
	// }
	// }
	//
	// return max;
	// }

	public int longestLine(int[][] M) {
		int row = M.length;
		if (row == 0)
			return 0;
		int col = M[0].length;
		if (col == 0)
			return 0;

		int max = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0, hori = 0; j < col; j++) {
				hori = M[i][j] == 1 ? hori + 1 : 0;
				max = Math.max(max, hori);
			}
		}

		for (int j = 0; j < col; j++) {
			for (int i = 0, vert = 0; i < row; i++) {
				vert = M[i][j] == 1 ? vert + 1 : 0;
				max = Math.max(max, vert);
			}
		}

		for (int k = 0; k < row + col; k++) {
			for (int i = Math.min(k, row - 1), j = Math.max(0, k - row), diag = 0; i >= 0 && j < col; i--, j++) {
				diag = M[i][j] == 1 ? diag + 1 : 0;
				max = Math.max(max, diag);
			}

			for (int i = Math.max(row - 1 - k, 0), j = Math.max(0, k - row), anti = 0; i < row && j < col; j++, i++) {
				anti = M[i][j] == 1 ? anti + 1 : 0;
				max = Math.max(max, anti);
			}
		}
		return max;
	}

	private boolean valid(int[][] M, int i, int j) {
		int row = M.length;
		int col = M[0].length;
		if (0 <= i && i < row && 0 <= j && j < col && M[i][j] == 1)
			return true;
		return false;
	}

	public static void main(String[] args) {
		SolutionDay23_562 day = new SolutionDay23_562();
		// int[][] M =
		// {{1,1,0,0,1,0,0,1,1,0},{1,0,0,1,0,1,1,1,1,1},{1,1,1,0,0,1,1,1,1,0},{0,1,1,1,0,1,1,1,1,1},{0,0,1,1,1,1,1,1,1,0},{1,1,1,1,1,1,0,1,1,1},{0,1,1,1,1,1,1,0,0,1},{1,1,1,1,1,0,0,1,1,1},{0,1,0,1,1,0,1,1,1,1},{1,1,1,0,1,0,1,1,1,1}};
		int[][] M = { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 0, 1 } };
		day.longestLine(M);
	}

}
