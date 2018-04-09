package com.daimens.algorithm.march;

public class SolutionDay11_L0502 {
	
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[100][100];
        dp[0][0] = poured;
        for (int i = 0; i < 100; ++i){
            for (int j = 0; j <= i; ++j){
                if (dp[i][j] > 1){
                    if (i < 99){
                        dp[i + 1][j] += (dp[i][j] - 1) / 2;
                        dp[i + 1][j + 1] += (dp[i][j] - 1) / 2;
                    }
                    dp[i][j] = 1;
                }
            }
        }
        return dp[query_row][query_glass];
    }
    
	
	public static void main(String[] args) {
		SolutionDay11_L0502 day = new SolutionDay11_L0502();
		System.out.println(day.champagneTower(2, 1, 0));
	}
}
