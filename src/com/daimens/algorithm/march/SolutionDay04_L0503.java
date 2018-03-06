package com.daimens.algorithm.march;

public class SolutionDay04_L0503 {
	
	
    public int preimageSizeFZF(int K) {
    	return (int)(count(K) - count(K - 1));
    }
    
    long count(int K) {
    	if (K == -1) return 0;
    	long lf = 0;
    	long rt = Integer.MAX_VALUE;
    	while (lf < rt) {
    		long mid = lf + (rt - lf + 1) / 2;
    		long cnt = 0;
    		for (long k = mid / 5; k > 0; k /= 5) cnt += k;
    		if (cnt <= K) {
    			lf = mid;
    		}
    		else{
    			rt = mid - 1;
    		}
    	}
    	return rt + 1;
    }
    
    public static void main(String[] args) {
    	SolutionDay04_L0503 day = new SolutionDay04_L0503();
    	System.out.println(day.preimageSizeFZF(0));
	}
}
