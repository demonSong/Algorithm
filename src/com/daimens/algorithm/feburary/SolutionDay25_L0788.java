package com.daimens.algorithm.feburary;

public class SolutionDay25_L0788 {
	
    public int rotatedDigits(int N) {
    	int cnt = 0;
    	for (int i = 1; i <= N; ++i) {
    		int rotate = valid(i);
    		if (rotate != -1 && rotate != i) cnt += 1;
    	}
    	return cnt;
    }
    
    public int valid(int n) {
    	int[] map = {0, 1, 5, -1, -1, 2, 9, -1, 8, 6};
    	int num = 0; int p = 1;
    	for(;n != 0; n /= 10) {
    		int digit = map[n % 10];
    		if (digit == -1) return -1;
    		num = num + p * digit;
            p *= 10;
    	}
    	return num;
    }
}
