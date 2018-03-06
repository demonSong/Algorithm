package com.daimens.algorithm.february;

/**
 * 
 * @author Demon Song
 * 486.Predict the Winner
 * Given an array of scores that are non-negative integers.
 * Player 1 picks one of the numbers from either end of the 
 * array followed by the player 2 and then player 1 and so on. 
 * Each time a player picks a number,that number will not be
 * available for the next player.This continues until all the scores
 * have been chosen.The player with the maximum score wins.
 * Given an array of scores,predict whether player 1 is the winner.You
 * can assume each player plays to maximize his score.
 * Example 1:
 * Input: [ 1, 5 , 2]
 * Output: False
 * Explanation:
 * Initially,player 1 can choose between 1 and 2.
 * if he chooses 2 (or 1),then player 2 can choose from 1 (or 2) and 5. 
 * If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return False.
 * 
 * Example 2:
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation:
 * Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. 
 * No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), 
 * so you need to return True representing player1 can win.
 * 
 * Note:
 * 1. 1 <= length of the array <= 20.
 * 2. Any scores in the given array are non-negative integers and will not exceed 10,000,000.
 * 3. If the scores of both players are equal,then player 1 is still the winner. 
 *
 */
public class SolutionDay08_486 {
	
	//这游戏实际上是不公平的，给定的数组决定了，先后手的输赢
//	public boolean PredictTheWinner(int[] nums) {
//		return helper(nums, 0, nums.length-1) >=0;
//	}
//	
//	private int helper(int[] nums, int s,int e){
//		return s == e ? nums[e] : Math.max(nums[e] - helper(nums, s, e-1),nums[s] - helper(nums, s+1, e));
//	}
	
//	public boolean PredictTheWinner(int[] nums) {
//		int n = nums.length;
//		int[][] dp = new int[n][n];
//		for (int i = 0; i < n; i++){
//			dp[i][i] = nums[i];
//		}
//		
//		for (int len = 1; len < n; len ++){
//			for (int i = 0; i < n-len; i++){
//				int j = i + len;
//				dp[i][j] = Math.max(nums[i]- dp[i+1][j], nums[j] - dp[i][j-1]);
//			}
//		}
//		return dp[0][n-1] >= 0;
//	}
	
	
	//dp[i][j]表示数组下标i到j之间取数字为子问题，它等于能够在i到j之间获得的所有数字之和的最大值
	public boolean PredictTheWinner(int[] nums) {
        if(nums.length <= 2) return true;
        int n = nums.length;
        int[] sum = new int[n+1];
        sum[0] = 0;
        for(int i = 1; i <= n; i ++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        
        int[][] dp = new int[n][n];
        for(int len = 1; len < n; len ++) {
            for(int i = 0; i + len < n; i ++) {
                int j = i + len;
                if(len == 1) dp[i][j] = Math.max(nums[i], nums[j]);
                else {
                    int can1 = sum[j+1] - sum[i+1] - dp[i+1][j] + nums[i];
                    int can2 = sum[j] - sum[i] - dp[i][j-1] + nums[j];
                    dp[i][j] = Math.max(can1, can2);
                }
            }
        }
        return sum[n] - dp[0][n-1] <= dp[0][n-1];
    }
}
