package com.daimens.algorithm.october;

public class SolutionDay01_L0503 {
	
//  TLE
//	class Triple{	
//		int i;
//		int j;
//		int k;
//		
//		Triple(int i, int j, int k){
//			this.i = i;
//			this.j = j;
//			this.k = k;
//		}
//	}
//	
//	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
//    	int n = nums.length;
//    	int[] sum = new int[n + 1];
//    	for (int i = 0; i < n; ++i) {
//    		sum[i + 1] = nums[i] + sum[i]; 
//    	}
//    	
//    	int max = 0;
//    	Triple ans = new Triple(-1, -1, -1);
//    	
//    	for (int i = 0; i + 3 * k <= n; ++i) {
//    		for (int j = i + k; j + 2 * k <= n; ++j) {
//    			for (int l = j + k; l + k <= n; ++l) {
//    				int tmp = 0;
//    				tmp += sum[i + k] - sum[i];
//    				tmp += sum[j + k] - sum[j];
//    				tmp += sum[l + k] - sum[l];
//    				if (tmp > max) {
//    					max = tmp;
//    					ans.i = i;
//    					ans.j = j;
//    					ans.k = l;
//    				}
//    				else if (tmp == max){
//    					ans.i = ans.i > i ? i : ans.i;
//    					ans.j = ans.j > j ? j : ans.j;
//    					ans.k = ans.k > l ? l : ans.k;
//    				}
//    			}
//    		}
//    	}
//    	
//    	return new int[] {ans.i, ans.j, ans.k};
//    }
	
//	Map<Integer, int[]> mem = new HashMap<>();
//	public int[] f(int[] dp, int pos, int k, int turn, int n) {
//		if (turn == 0) {
//			return new int[] {0, 0, 0};
//		}
//		else {
//			int max = 0;
//			int idx = pos;
//			int[] res = new int[3];
//			for (int i = pos; i + turn * k <= n; ++i) {
//                if (mem.containsKey(i * 4 + turn)) return mem.get(i * 4 + turn);
//				int[] ans = f(dp, i + k, k, turn - 1, n);
//				ans[turn - 1] = i;
//				if (dp[ans[0]] + dp[ans[1]] + dp[ans[2]] > max) {
//					max = dp[ans[0]] + dp[ans[1]] + dp[ans[2]];
//					for (int j = turn - 1; j >= 0; --j) {
//						res[j] = ans[j];
//					}
//					idx = i;
//					mem.put(idx * 4 + turn, res);
//				}
//				else if (dp[ans[0]] + dp[ans[1]] + dp[ans[2]] == max){
//					for (int j = turn - 1; j >= 0; --j) {
//						res[j] = Math.min(res[j], ans[j]);
//					}
//					idx = i;
//					mem.put(idx * 4 + turn, res);
//				}
//			}
//			return res;
//		}
//	}
	
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    	int n = nums.length;
    	int[] sum = new int[n + 1];
    	for (int i = 0; i < n; ++i) {
    		sum[i + 1] = nums[i] + sum[i]; 
    	}
    	
    	int[] dp = new int[n];
    	
    	for (int l = 2 * k; l + k <= n; ++l) {
    		dp[l] = sum[l + k] - sum[l];
    	}
    	
    	for (int l = k; l + 2 * k <= n; ++l) {
    		dp[l] = sum[l + k] - sum[l];
    	}
    	
    	for (int l = 0; l + 3 * k <= n; ++l) {
    		dp[l] = sum[l + k] - sum[l];
    	}
    	
    	int[] left = new int[n];
    	for (int i = 0; i < n; ++i) {
    		if (i - k >= 0) {
    			if (left[i - 1] == -1) {
    				left[i] = i - k;
    			}
    			else if (dp[i - k] <= dp[left[i - 1]]) {
    				left[i] = left[i - 1];
    			}
    			else{
    				left[i] = i - k;
    			}
    		}
    		else {
    			left[i] = -1;
    		}
    	}
    	
    	int[] right = new int[n];
    	for (int i = n - 1; i >= 0; --i) {
    		if (i + k < n) {
    			if (right[i + 1] == -1) {
    				right[i] = i + k;
    			}
    			else if (dp[right[i + 1]] > dp[i + k]) {
    				right[i] = right[i + 1];
    			}
    			else {
    				right[i] = i + k;
    			}
    		}
    		else {
    			right[i] = -1;
    		}
    	}
    	
    	int max = 0;
    	int[] res = {-1, -1, -1};
    	for (int i = 0; i < n; ++i) {
    		if (left[i] != -1 && right[i] != -1) {
    			int tmp = dp[i] + dp[right[i]] + dp[left[i]];
    			if (tmp > max) {
    				max = dp[i] + dp[right[i]] + dp[left[i]];
    				res[0] = left[i];
    				res[1] = i;
    				res[2] = right[i];
    			}
    		}
    	}
    	return res;
    }

	public static void main(String[] args) {
		SolutionDay01_L0503 day = new SolutionDay01_L0503();
		int[] nums = {18,11,14,7,16,4,18,11,4,8};
		System.out.println(day.maxSumOfThreeSubarrays(nums, 2));
	}
}
