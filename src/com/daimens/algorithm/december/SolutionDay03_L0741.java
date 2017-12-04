package com.daimens.algorithm.december;

public class SolutionDay03_L0741 {
	
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
    	
    	// grid[0][0] 始终会被访问到，f在求解问题时，是从起点出发（不包含起点）的最大cherry数
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
    	// 一旦抵达终态则范围0， 因为此处f的最大cherry数不包含起点，所以终态才为 (n - 1, m - 1)
    	if (x1 == n - 1 && y1 == m - 1) return 0;
    	// x2一旦确定，y2跟着确定，没必要记录y2的状态
    	if (mem[x1][y1][x2] != -1) return mem[x1][y1][x2];
    	int ans = -INF;
    	
    	for (int i = 0; i < 2; ++i) {
    		for (int j = 0; j < 2; ++j) {
    			// 只能往右 和 往下
    			int nx1 = x1 + dx[i];
    			int ny1 = y1 + dy[i];
    			
    			int nx2 = x2 + dx[j];
    			int ny2 = y2 + dy[j];
    			
    			if (nx1 >= 0 && nx1 < n && nx2 >= 0 && nx2 < n && ny1 >= 0 && ny1 < m && ny2 >= 0 && ny2 < m) {
    				if (v[nx1][ny1] == -1) continue;
    				if (v[nx2][ny2] == -1) continue;
    				
    				if (nx1 == nx2 && ny1 == ny2) {
    					// 重合的情况，只需加一次cherry，第一次取cherry，第二次经过但无cherry可取
    					ans = Math.max(ans, v[nx1][ny1] + f(nx1, ny1, nx2, ny2));
    				}
    				else {
    					// 去取一次cherry，回也取一次cherry
    					ans = Math.max(ans, v[nx1][ny1] + v[nx2][ny2] + f(nx1, ny1, nx2, ny2));
    				}
    			}
    		}
    	}
    	return mem[x1][y1][x2] = ans;
    }
	
	public static void main(String[] args) {
		SolutionDay03_L0741 day = new SolutionDay03_L0741();
	}

}
