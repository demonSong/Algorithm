package com.daimens.algorithm.may;

/**
 * 
 * @author DemonSong
 * 
 *         576.Out of Boundary Paths
 * 
 *         There is an m by n grid with a ball. Given the start coordinate (i,j)
 *         of the ball, you can move the ball to adjacent cell or cross the grid
 *         boundary in four directions (up, down, left, right). However, you can
 *         at most move N times. Find out the number of paths to move the ball
 *         out of grid boundary. The answer may be very large, return it after
 *         mod 109 + 7.
 * 
 *         Example 1: Input:m = 2, n = 2, N = 2, i = 0, j = 0 Output: 6
 *         Explanation:
 * 
 *         Example 2: Input:m = 1, n = 3, N = 3, i = 0, j = 1 Output: 12
 *         Explanation:
 * 
 *         Note: Once you move the ball out of boundary, you cannot move it
 *         back. The length and height of the grid is in range [1,50]. N is in
 *         range [0,50].
 *
 */
public class SolutionDay07_576 {

	// public int findPaths(int m, int n, int N, int i, int j) {
	//
	// int MOD = 1000000000 + 7;
	//
	// int[][] dp = new int[m][n];
	// //boolean[][] hh = new boolean[m][n];
	// dp[i][j] = 1;
	// //hh[i][j] = true;
	// int[][] directions= {{-1,0},{1,0},{0,-1},{0,1}};
	//
	// int[] now = {i,j,0};
	// Queue<int[]> queue = new LinkedList<>();
	// queue.add(now);
	//
	// int ans = 0;
	//
	// while (!queue.isEmpty()){
	// int[] tmp = queue.poll();
	// if (tmp[2] == N) break;
	// for (int l = 0; l < 4; l++){
	// int cur_i = tmp[0] + directions[l][0];
	// int cur_j = tmp[1] + directions[l][1];
	// if (cur_i == -1 || cur_i == m || cur_j == -1 || cur_j == n) {
	// if (cur_i == -1) ans += (dp[0][cur_j] >= 0 ? 0 : 1) % MOD;
	// if (cur_i == m) ans += dp[m-1][cur_j]% MOD;
	// if (cur_j == -1) ans += dp[cur_i][0]% MOD;
	// if (cur_j == n) ans += dp[cur_i][n-1]% MOD;
	// continue;
	// }
	// //if (hh[cur_i][cur_j]) continue;
	// dp[cur_i][cur_j] = (cur_i == 0 ? 0 : dp[cur_i-1][cur_j]) + (cur_i == m-1
	// ? 0 : dp[cur_i+1][cur_j]) + (cur_j == n-1 ? 0 : dp[cur_i][cur_j+1]) +
	// (cur_j == 0 ? 0 : dp[cur_i][cur_j-1]);
	// //hh[cur_i][cur_j] = true;
	// queue.add(new int[]{cur_i,cur_j,tmp[2]+1});
	// }
	// }
	//
	//// int ans = 0;
	////
	//// for (int k = 1; k <= n; k++){
	//// ans += dp[0][k] % MOD;
	//// ans += dp[m+1][k] % MOD;
	//// }
	////
	//// for (int k = 1; k <= m; k++){
	//// ans += dp[k][0] % MOD;
	//// ans += dp[k][n] % MOD;
	//// }
	//
	// return ans;
	// }

	// public int findPaths(int m, int n, int N, int i, int j) {
	//
	// int MOD = 1000000000 + 7;
	// int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	//
	// int[] now = { i, j, 0 };
	// Queue<int[]> queue = new LinkedList<>();
	// queue.add(now);
	//
	// int ans = 0;
	// while (!queue.isEmpty()) {
	// int[] tmp = queue.poll();
	// if (tmp[2] == N)
	// break;
	// for (int l = 0; l < 4; l++) {
	// int cur_i = tmp[0] + directions[l][0];
	// int cur_j = tmp[1] + directions[l][1];
	// if (cur_i == -1 || cur_i == m || cur_j == -1 || cur_j == n) {
	// if (cur_i == -1)
	// ans = (ans + 1) % MOD;
	// if (cur_i == m)
	// ans = (ans + 1) % MOD;
	// if (cur_j == -1)
	// ans = (ans + 1) % MOD;
	// if (cur_j == n)
	// ans = (ans + 1) % MOD;
	// continue;
	// }
	// queue.add(new int[] { cur_i, cur_j, tmp[2] + 1 });
	// }
	// }
	// return ans;
	// }

	static int row;
	static int col;

	public int findPaths(int m, int n, int N, int i, int j) {

		row = m;
		col = n;

		if (N == 0)
			return 0;

		int MOD = 1000000000 + 7;
		int[][][] dp = new int[m][n][N];

		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		dp[i][j][0] = 1;

		int ret = 0;
		for (int k = 1; k < N; k++) {
			for (int x = 0; x < m; x++) {
				for (int y = 0; y < n; y++) {
					for (int l = 0; l < 4; l++) {
						int curr_x = x + directions[l][0];
						int curr_y = y + directions[l][1];
						if (valid(curr_x, curr_y)) {
							dp[x][y][k] = (dp[x][y][k] + dp[curr_x][curr_y][k - 1]) % MOD;
						} else {
							ret += dp[x][y][k - 1];
							ret %= MOD;
						}
					}
				}
			}
		}

		for (int x = 0; x < m; x++) {
			for (int y = 0; y < n; y++) {
				for (int l = 0; l < 4; l++) {
					int curr_x = x + directions[l][0];
					int curr_y = y + directions[l][1];
					if (!valid(curr_x, curr_y)) {
						ret += dp[x][y][N - 1];
						ret %= MOD;
					}
				}
			}
		}

		return ret;
	}

	private boolean valid(int x, int y) {
		return x >= 0 && x < row && y >= 0 && y < col;
	}

	public static void main(String[] args) {
		SolutionDay07_576 day = new SolutionDay07_576();
		// day.findPaths(2, 2, 2, 0, 0);
		// day.findPaths(10, 10, 10, 5, 5);
		day.findPaths(1, 3, 3, 0, 1);
	}

}
