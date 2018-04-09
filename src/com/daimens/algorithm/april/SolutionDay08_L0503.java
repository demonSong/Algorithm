package com.daimens.algorithm.april;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SolutionDay08_L0503 {
	
	class Node{
		int u;
		int v;
		int to;
	}
	
    public int numBusesToDestination(int[][] routes, int S, int T) {
    	int[][] g = new int[1000000 + 2][1000000 + 2];
    	int inf = 0x3f3f3f3f;
    	for (int i = 0; i < 100000 + 2; ++i) Arrays.fill(g[i], inf);
    	
    	int n = routes.length;
    	Set<Integer>[] sets = new HashSet[n];
    	
    	for (int i = 0; i < n; ++i) {
    		int[] r = routes[i];
    		for (int j = 0; j < r.length; ++j) sets[i].add(r[j]);
    		for (int l = 0; l < r.length; ++l) {
    			for (int k = l + 1; k < r.length; ++k) {
    				g[r[l]][r[k]] = g[r[k]][r[l]] = 1;
    			}
    		}
    	}
    	
    	for (int i = 0; i < n; ++i) {
    		for (int j = i + 1; j < n; ++j) {
    			if (common(sets[i], routes[j])) {
    				for (int r1 : routes[i]) {
    					for (int r2 : routes[j]) {
    						g[r1][r2] = g[r2][r1] = 2;
    					}
    				}
    			}
    		}
    	}
    	
    	for (int i = 0; i < 1000000 + 2; ++i) {
    		for (int j = 0; j < 1000000 + 2; ++j) {
    			for (int k = 0; k < 1000000 + 2; ++k) {
    				g[j][k] = Math.min(g[j][k], g[j][i] + g[i][k]);
    			}
    		}
    	}
    	return g[S][T];
    }
    
    boolean common(Set<Integer> r1, int[] r2) {
    	for (int r : r2) if (r1.contains(r)) return true;
    	return false;
    }
	
	public static void main(String[] args) {
		SolutionDay08_L0503 day = new SolutionDay08_L0503();
		int[][] r = {{1, 2, 7}, {3, 6, 7}};
		System.out.println(day.numBusesToDestination(r, 1, 6));
	}

}
