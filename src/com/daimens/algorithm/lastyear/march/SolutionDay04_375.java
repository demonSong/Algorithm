package com.daimens.algorithm.march;

/**
 * 
 * @author DemonSong
 * 375.Guess Number Higher or Lower II
 * We are playing the Guess Game.The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
 * However,when you guess a particular number x, and you guess wrong, you pay
 * $x. You win the game when you guess the number I picked.
 * Example:
 * n = 10, I pick 8.
 * First round: You guess 5,I tell you that it's higher. You pay $5.
 * Second round: You guess 7, I tell you that it's higher. You pay $7.
 * Third round: You guess 9, I tell you that it's lower. You pay $9.
 * 
 * Game over. 8 is the number I picked.
 * You end up paying $5+$7+$9 = $21.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 *
 */
public class SolutionDay04_375 {
	public int getMoneyAmount(int n) {
		if (n == 1) {
			return 0;
		}
		int[][] dp = new int[n + 1][n + 1];
		for (int jminusi = 1; jminusi < n; jminusi++) {
			for (int i = 0; i + jminusi <= n; i++) {
				int j = i + jminusi;
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j; k++) { //i j 只在这里才有可能循环比较
					dp[i][j] = Math.min(dp[i][j],
							k + Math.max(k - 1 >= i ? dp[i][k - 1] : 0, j >= k + 1 ? dp[k + 1][j] : 0));
				}
			}
		}
		return dp[1][n];
	}
	
	public static void main(String[] args) {
		SolutionDay04_375 day = new SolutionDay04_375();
		day.getMoneyAmount(10);
	}
}
