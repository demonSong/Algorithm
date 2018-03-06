package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         573.Squirrel Simulation
 * 
 *         There's a tree, a squirrel, and several nuts. Positions are
 *         represented by the cells in a 2D grid. Your goal is to find the
 *         minimal distance for the squirrel to collect all the nuts and put
 *         them under the tree one by one. The squirrel can only take at most
 *         one nut at one time and can move in four directions - up, down, left
 *         and right, to the adjacent cell. The distance is represented by the
 *         number of moves.
 * 
 *         Example 1: Input: Height : 5 Width : 7 Tree position : [2,2] Squirrel
 *         : [4,4] Nuts : [[3,0], [2,5]] Output: 12 Explanation:
 * 
 *         Note: All given positions won't overlap. The squirrel can take at
 *         most one nut at one time. The given positions of nuts have no order.
 *         Height and width are positive integers. 3 <= height * width <=
 *         10,000. The given positions contain at least one nut, only one tree
 *         and one squirrel.
 *
 */
public class SolutionDay07_573 {

	// public int minDistance(int height, int width, int[] tree, int[] squirrel,
	// int[][] nuts) {
	// int n = nuts.length;
	// int[][] dp = new int[n+1][n];
	// dp[0][0] = 0;
	//
	// for (int i = 0; i < n; i++){
	// dp[0][i] = distance(squirrel[0], squirrel[1], nuts[i][0], nuts[i][1])
	// + distance(tree[0], tree[1], nuts[i][0], nuts[i][1]);
	// }
	//
	// for (int i = 1; i <= n; i++){
	// int[] curr = nuts[i-1];
	// for (int j = 0; j < n; j++){
	// int min = Integer.MAX_VALUE;
	// for (int k = 0; k < n; k++){
	// if (k == j) continue;
	// min = Math.min(dp[i-1][j], min);
	// }
	// dp[i][j] = min + distance(curr[0], curr[1], nuts[j][0], nuts[j][1])
	// + distance(tree[0], tree[1], nuts[j][0], nuts[j][1]);
	// }
	// }
	//
	// int min = Integer.MAX_VALUE;
	// for (int i = 0; i < n; i++){
	// min = Math.min(min, dp[n][i]);
	// }
	//
	// return min;
	// }

	// public int minDistance(int height, int width, int[] tree, int[] squirrel,
	// int[][] nuts) {
	//
	// int n = nuts.length;
	//
	// int min = Integer.MAX_VALUE;
	// int index = -1;
	// for (int i = 0; i < n; i++){
	// if (distance(squirrel[0], squirrel[1], nuts[i][0], nuts[i][1]) +
	// distance(tree[0], tree[1], nuts[i][0], nuts[i][1]) < min){
	// min = distance(squirrel[0], squirrel[1], nuts[i][0], nuts[i][1]) +
	// distance(tree[0], tree[1], nuts[i][0], nuts[i][1]);
	// index = i;
	// }
	// }
	//
	// int[] t2n2t = new int[n];
	// int sum = 0;
	// for (int i = 0; i < n; i++){
	// t2n2t[i] = distance(tree[0], tree[1], nuts[i][0], nuts[i][1]) * 2;
	// if (i != index){
	// sum += t2n2t[i];
	// }
	// }
	//
	// return sum + min;
	// }

	public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {

		int n = nuts.length;
		int[] s2n = new int[n];
		int[] t2s = new int[n];

		int sum = 0;
		for (int i = 0; i < n; i++) {
			s2n[i] = distance(squirrel[0], squirrel[1], nuts[i][0], nuts[i][1]);
			t2s[i] = distance(tree[0], tree[1], nuts[i][0], nuts[i][1]);
			sum += t2s[i] * 2;
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			min = Math.min(min, sum - t2s[i] + s2n[i]);
		}

		return min;
	}

	private int distance(int x1, int y1, int x2, int y2) {
		return Math.abs((x1 - x2)) + Math.abs((y1 - y2));
	}

	public static void main(String[] args) {
		SolutionDay07_573 day = new SolutionDay07_573();
		// day.minDistance(1, 3, new int[]{0,1},new int[]{0,0},new
		// int[][]{{0,2}});
		day.minDistance(5, 7, new int[] { 2, 2 }, new int[] { 4, 4 }, new int[][] { { 3, 0 }, { 2, 5 } });
	}

}
