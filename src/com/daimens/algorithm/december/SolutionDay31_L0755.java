package com.daimens.algorithm.december;

import java.util.Arrays;

public class SolutionDay31_L0755 {
	
    public int[] pourWater(int[] a, int V, int K) {
    	int n = a.length;
    	for (int i = 0; i < V; ++i) {
    		boolean ok = false;
    		int index = -1;
    		for (int j = K - 1; j >= 0; --j) {
    			if (a[j] > a[j + 1]) break;
    			if (a[j] < a[j + 1]) {
    				ok = true;
    				index = j;
    			}
    		}
    		if (ok) {
    			a[index] ++;
    			continue;
    		}
    		
    		for (int j = K + 1; j < n; ++j) {
    			if (a[j] > a[j - 1]) break;
    			if (a[j] < a[j - 1]) {
    				ok = true;
    				index = j;
    			}
    		}
    		if (ok) a[index] ++; else a[K] ++;
    	}
    	return a;
    }
    
	
	public static void main(String[] args) {
		SolutionDay31_L0755 day = new SolutionDay31_L0755();
		int[] heights = {3, 1, 3};
		System.out.println(Arrays.toString(day.pourWater(heights, 5, 1)));
	}

}
