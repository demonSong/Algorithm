package com.daimens.algorithm.feburary;

public class SolutionDay18_L0785 {

	boolean dfs(int[][] G, int[] color, int v, int c) {
		color[v] = c;
		for (int i = 0; i < color.length; ++i) {
			if (G[v][i] != 0) {
				if (color[i] != 0 && color[i] == c) return false;
				if (color[i] == 0 && !dfs(G, color, i, -c)) return false;
			}
		}
		return true;
	}
	
	public boolean isBipartite(int[][] graph) {
		int n = graph.length;
		int[] color = new int[n];
		int[][] G = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < graph[i].length; ++j) {
				G[i][graph[i][j]] = 1;
			}
		}
		
		for (int i = 0; i < n; ++i) {
			if (color[i] == 0) {
				if (!dfs(G, color, i, 1)) return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		SolutionDay18_L0785 day = new SolutionDay18_L0785();
	}

}
