package com.daimens.algorithm.march;

public class SolutionDay18_L0501 {
	
//    public int minSwap(int[] A, int[] B) {
//    	mem = new int[A.length][2];
//    	return dfs(A, B, 0, false);
//    }
//    
//    int[][] mem;
//    public int dfs(int[] A, int[] B, int pos, boolean flag) {
//    	if (A.length == pos) return 0;
//    	if (mem[pos][flag ? 1 : 0] > 0) return mem[pos][flag ? 1 : 0]; 
//    	int value = 0;
//    	boolean changed = false;
//    	if (pos - 1 >= 0 && (A[pos] <= A[pos - 1] || B[pos] <= B[pos - 1])) {
//    		int tmp = A[pos];
//    		A[pos] = B[pos];
//    		B[pos] = tmp;
//    		value = dfs(A, B, pos + 1, !flag) + 1;
//    		tmp = A[pos];
//    		A[pos] = B[pos];
//    		B[pos] = tmp;
//    		changed = true;
//    	}
//    	else {
//    		int sub1 = dfs(A, B, pos + 1, flag);
//    		if (pos - 1 >= 0 && A[pos] > B[pos - 1] && B[pos] > A[pos - 1]) { // 交换后 符合情况
//    			int tmp = A[pos];
//        		A[pos] = B[pos];
//        		B[pos] = tmp;
//        		int sub2 = dfs(A, B, pos + 1, !flag);
//        		
//        		tmp = A[pos];
//        		A[pos] = B[pos];
//        		B[pos] = tmp;
//        		
//        		if (sub1 < sub2 + 1) {
//        			value = sub1;
//        		}
//        		else {
//        			changed = true;
//        			value = sub2 + 1;
//        		}
//    		}
//    		else {
//    			value = sub1;
//    		}
//    	}
//    	mem[pos][changed ? 1 : 0] = value;
//    	return value;
//    }
    
    public int minSwap(int[] A, int[] B) {
        Integer[][] dp = new Integer[A.length + 10][2];
        return  dfs(0, 0, A, B, dp);
    }
    
    int dfs(int idx, int rev, int[] A, int[] B, Integer[][] dp) {
        int pa, pb;
        if (idx == 0) {
            pa = -1;
            pb = -1;
        }
        else {
            pa = A[idx - 1];
            pb = B[idx - 1];
            if (rev == 1) {
                int t = pa;
                pa = pb;
                pb = t;
            }
        }

        if (idx == A.length) {
            return 0;
        }
        if (dp[idx][rev] != null) {
            return dp[idx][rev];
        }
        int ans = A.length + 10;
        if (A[idx] > pa && B[idx] > pb) { // 不交换
            ans = Math.min(ans, dfs(idx + 1, 0, A, B, dp));
        }
        if (A[idx] > pb && B[idx] > pa) { // 交换
            ans = Math.min(ans, 1 + dfs(idx + 1, 1, A, B, dp));
        }
        dp[idx][rev] = ans;
        return ans;
    }
	
	public static void main(String[] args) {
		SolutionDay18_L0501 day = new SolutionDay18_L0501();
		int[] A = {0, 4, 4, 5, 9};
		int[] B = {0, 1, 6, 8, 10};
		System.out.println(day.minSwap(A, B));
	}
}
