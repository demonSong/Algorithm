package com.daimens.algorithm.feburary;

public class SolutionDay25_L0789 {
	
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
    	int INF = 0x3f3f3f3f;
    	int min_ghost = INF;
    	for (int[] g : ghosts) {
    		int dx = Math.abs(g[0] - target[0]);
    		int dy = Math.abs(g[1] - target[1]);
    		min_ghost = Math.min(min_ghost, dx + dy);
    	}
    	int me = target[0] + target[1];
    	return min_ghost > me;
    }
}
