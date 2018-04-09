package com.daimens.algorithm.march;

public class SolutionDay11_L0503 {
	
    public int bestRotation(int[] A) {
    	int n = A.length;
    	int[] count = new int[n + 1];
    	for (int i = 0; i < n; ++i) {
    		int ori = (i - A[i] + n) % n;
    		count[ori + 1] --;
    		int end = (ori + 1 - (n - A[i]) + n) % n;
    		if (end <= ori) {
    			count[end] ++;
    		}
    		else {
    			count[0] ++;
    			count[end]++;
    			count[n]--;
    		}
    	}
    	int max = count[0];
    	int res = 0;
    	for (int i = 1; i <= n; ++i) {
    		count[i] += count[i - 1];
    		if (max > count[i]) {
    			res = i;
    			max = count[i];
    		}
    	}
    	return res;
    }
	
	public static void main(String[] args) {
		SolutionDay11_L0503 day = new SolutionDay11_L0503();
	}

}
