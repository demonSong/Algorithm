package com.daimens.algorithm.april;

public class SolutionDay08_L0812 {
	
    public double largestTriangleArea(int[][] points) {
    	double max = 0;
    	int n = points.length;
    	for (int i = 0; i < n; ++i) {
    		for (int j = i + 1; j < n; ++j) {
    			for (int l = j + 1; l < n; ++l) {
    				max = Math.max(max, tra(points[i], points[j], points[l]));
    			}
    		}
    	}
    	return max;
    }
    
    public double tra(int[] p1, int[] p2, int[] p3) {
    	int x1 = p1[0];
    	int y1 = p1[1];
    	int x2 = p2[0];
    	int y2 = p2[1];
    	int x3 = p3[0];
    	int y3 = p3[1];
    	return 0.5 * Math.abs((x1 * y2 + x2 * y3 + x3* y1 - x1 * y3 - x2 * y1 - x3 * y2));
    }
	
	public static void main(String[] args) {
		SolutionDay08_L0812 day = new SolutionDay08_L0812();
	}
}	
