package com.daimens.algorithm.october;

public class SolutionDay29_L0502 {
	
	public int findLength(int[] A, int[] B) {
		int n = A.length;
		int m = B.length;
		int max = 0;
		int[][] dp = new int[n + 1][m + 1];
		
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				if (A[i - 1] == B[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				else {
					dp[i][j] = 0;
				}
				
				max = Math.max(dp[i][j], max);
				max = Math.max(dp[i - 1][j], max);
				max = Math.max(dp[i][j - 1], max);
			}
		}
		
		return max;
	}
	
//    public int findLength(int[] A, int[] B) {
//    	max = 0;
//    	int n = A.length;
//    	int m = B.length;
//    	dp = new int[n + 1][m + 1];
//    	for (int i = 0; i < n + 1; ++i) {
//    		for (int j = 0; j < m + 1; ++j) {
//    			dp[i][j] = -1;
//    		}
//    	}
//    	dfs(A, B, n - 1, m - 1);
//    	return max;
//    }
//    
//    
//    int[][] dp;
//    int max = 0;
//    int dfs(int[] A, int[] B, int n, int m) {
//    	if (n == -1 || m == -1) return 0;
//    	if (dp[n][m] >= 0) return dp[n][m];
//    	if (A[n] == B[m]) {
//    		int ans = 1;
//    		int sub = dfs(A, B, n - 1, m - 1);
//    		dfs(A, B, n - 1, m);
//    		dfs(A, B, n, m - 1);
//    		
//    		max = Math.max(max, ans + sub);
//    		dp[n][m] = ans + sub;
//    		return ans + sub;
//    	}
//    	else {
//    		dfs(A, B, n - 1, m);
//    		dfs(A, B, n, m - 1);
//    		dp[n][m] = 0;
//    		return 0;
//    	}
//    }
    
//    public void go(int[] A, int[] B, int n, int m) {
//    	if (n == -1 || m == -1) {
//    		return;
//    	}
//    	
//    	if (dp[n][m] >= 0) return;
//    	
//    	if (A[n] == B[m]) {
//    		
//    		int cnt = 0;
//    		
//    		int i = n;
//    		int j = m;
//    		while (i >= 0 && j >= 0 && A[i] == B[j]) {
//    			i --;
//    			j --;
//    			cnt ++;
//    		}
//    		max = Math.max(max, cnt);
//    	}
//    	// 以 n 为中心
//    	int j = m - 1;
//    	while (j >= 0 && A[n] != B[j]) {
//    		j --;
//    	}
//    	go(A, B, n, j);
//    	
//    	int i = n - 1;
//    	while (i >= 0 && A[i] != B[m]) {
//    		i --;
//    	}
//    	go(A, B, i, m);
//    	
//    	go(A, B, n - 1, m - 1);
//    	
//    	dp[n][m] = max;
//    }
    
    
	
	public static void main(String[] args) {
		SolutionDay29_L0502 day = new SolutionDay29_L0502();
		int[] A = {1, 2, 3, 2, 1};
		int[] B = {3, 2, 1, 4, 7};
		System.out.println(day.findLength(A, B));
	}

}
