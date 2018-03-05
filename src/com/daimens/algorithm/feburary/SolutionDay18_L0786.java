package com.daimens.algorithm.feburary;

import java.util.Arrays;

public class SolutionDay18_L0786 {
	
	
    public int[] kthSmallestPrimeFraction(int[] a, int K) {
        double low = 0, high = 1;
        double eps = 1e-8;
        int n = a.length;
        for(int rep = 0; rep < 50; ++rep){
        	double x = low + (high-low) / 2;
        	int num = 0;
        	for(int i = 0;i < n;i++){
        		int ind = Arrays.binarySearch(a, (int)(x * a[i]));
        		if(ind < 0) ind = -ind - 2;
        		num += ind + 1;
        	}
        	if(num >= K){
        		high = x;
        	}else{
        		low = x;
        	}
        }
        
        for (int i = 0; i < n; ++i) {
        	double find = a[i] * high;
        	int idx = Arrays.binarySearch(a, (int)find);
        	if (idx < 0) idx = -idx;
        	for (int j = -1; j <= 1; ++j) {
        		if (j + idx >= 0 && j + idx < n && Math.abs(a[j + idx] - find) < eps) {
        			return new int[] {a[j + idx], a[i]};
        		}
        	}
        }
        return new int[]{-1, -1};
    }
	
	public static void main(String[] args) {
		SolutionDay18_L0786 day = new SolutionDay18_L0786();
		int[] a = {1, 2, 3, 5};
		System.out.println(day.kthSmallestPrimeFraction(a, 3));
	}

}
