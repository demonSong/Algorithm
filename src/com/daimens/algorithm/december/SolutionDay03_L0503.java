package com.daimens.algorithm.december;

public class SolutionDay03_L0503 {
	
    public int cherryPickup(int[][] grid) {
    	n = grid.length;
    	m = grid[0].length;
    	
        v = new int[n][m];
        
    	for (int i = 0; i < n; ++i) {
    		for (int j = 0; j < m; ++j) {
    			v[i][j] = grid[i][j];
    		}
    	}
    	
    	mem = new int[52][52][52];
    	for (int i = 0; i < 52; ++i) {
    		for (int j = 0; j < 52; ++j) {
    			for (int k = 0; k < 52; ++k) {
    				mem[i][j][k] = -1;
    			}
    		}
    	}
    	
    	int ans = f(0, 0, 0, 0) + grid[0][0];
    	return ans < -10000 ? 0 : ans;
    }
    
    int[][] v;
    int n, m;
    int INF = 0x3f3f3f3f;
    
    int[][][] mem;
    
    int[] dx = {1, 0};
    int[] dy = {0, 1};
    int f(int x1, int y1, int x2, int y2) {
    	if (x1 == n - 1 && y1 == m - 1) return 0;
    	if (mem[x1][y1][x2] != -1) return mem[x1][y1][x2];
    	int ans = -INF;
    	
    	for (int i = 0; i < 2; ++i) {
    		for (int j = 0; j < 2; ++j) {
    			int nx1 = x1 + dx[i];
    			int ny1 = y1 + dy[i];
    			
    			int nx2 = x2 + dx[j];
    			int ny2 = y2 + dy[j];
    			
    			if (nx1 >= 0 && nx1 < n && nx2 >= 0 && nx2 < n && ny1 >= 0 && ny1 < m && ny2 >= 0 && ny2 < m) {
    				if (v[nx1][ny1] == -1) continue;
    				if (v[nx2][ny2] == -1) continue;
    				
    				if (nx1 == nx2 && ny1 == ny2) {
    					ans = Math.max(ans, v[nx1][ny1] + f(nx1, ny1, nx2, ny2));
    				}
    				else {
    					ans = Math.max(ans, v[nx1][ny1] + v[nx2][ny2] + f(nx1, ny1, nx2, ny2));
    				}
    			}
    		}
    	}
    	
    	return mem[x1][y1][x2] = ans;
    }
	
	public static void main(String[] args) {
		SolutionDay03_L0503 day = new SolutionDay03_L0503();
	}

}
