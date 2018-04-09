package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SolutionDay31_L0500 {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] grid = new int[n][m];
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					grid[i][j] = in.nextInt();
				}
			}
			
			List<int[]> ans = new ArrayList<>();
			int[][] dir = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					if (grid[i][j] == 1) {
						boolean ok = true;
						for (int[] d : dir) {
							int nx = i + d[0];
							int ny = j + d[1];
							if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
								if (grid[i][j] == 0) {
									ok = false;
								}
							}
						}
						if (ok) {
							ans.add(new int[] {i, j});
						}
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			Stack<String> s = new Stack<>();
			
			for (int i = 0; i < ans.size(); ++i) {
				System.out.println(ans.get(i)[0] + " " + ans.get(i)[1]);
			}
		}
		in.close();
	}

}
