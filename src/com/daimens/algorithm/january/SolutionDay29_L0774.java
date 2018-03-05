package com.daimens.algorithm.january;

public class SolutionDay29_L0774 {
	
    public double minmaxGasDist(int[] stations, int K) {
    	int n = stations.length;
    	double[] gap = new double[n - 1];
    	for (int i = 0; i < n - 1; ++i) {
    		gap[i] = stations[i + 1] - stations[i];
    	}
    	double lf = 0;
    	double rt = Integer.MAX_VALUE;
    	double eps = 1e-7;
    	while (Math.abs(rt - lf) > eps) {
    		double mid = (lf + rt) /2;
    		if (check(gap, mid, K)) {
    			rt = mid;
    		}
    		else {
    			lf = mid;
    		}
    	}
    	return lf;
    }
    
    boolean check(double[] gap, double mid, int K) {
    	int count = 0;
    	for (int i = 0; i < gap.length; ++i) {
    		count += (int)(gap[i] / mid);
    	}
    	return count <= K;
    }
    
    public static void main(String[] args) {
    	SolutionDay29_L0774 day = new SolutionDay29_L0774();
    	int[] stations = {10,19,25,27,56,63,70,87,96,97};
    	System.out.println(day.minmaxGasDist(stations, 3));
	}
}
