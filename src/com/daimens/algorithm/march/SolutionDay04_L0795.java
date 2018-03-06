package com.daimens.algorithm.march;

public class SolutionDay04_L0795 {
	
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
    	return count(A, R) - count(A, L - 1);
    }
    
    int count(int[] a, int r) {
    	int ret = 0;
    	int cnt = 0;
    	for (int v : a) {
    		if (v <= r) cnt ++;
    		else cnt = 0;
    		ret += cnt;
    	}
    	return ret;
    }
    
    public static void main(String[] args) {
    	SolutionDay04_L0795 day = new SolutionDay04_L0795();
    	int[] A = {73,55,36,5,55,14,9,7,72,52};
    	System.out.println(day.numSubarrayBoundedMax(A, 32, 69));
	}
    
}
