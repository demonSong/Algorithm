package com.daimens.algorithm.april;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolutionDay05_L0502 {

	static class E{
		int u;
		int v;
		int c;
		E(int u, int v, int c){
			this.u = u;
			this.v = v;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] machines = new int[n][2];
			int[][] taskes = new int[m][2];
			for (int i = 0; i < n; ++i) {
				machines[i] = new int[] {in.nextInt(), in.nextInt()};
			}
			for (int i = 0; i < m; ++i) {
				taskes[i] = new int[] {in.nextInt(), in.nextInt()};
			}
			
			int V = n + m;
			List<E>[] g = new ArrayList[V];
			for (int i = 0; i < V; ++i) g[i] = new ArrayList<>();
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					if (taskes[j][0] <= machines[i][0] && taskes[j][1] <= machines[i][1])
						g[i].add(new E(i, n + j, 200 * taskes[j][0] + 3 * taskes[j][1]));
				}
			}
		}
		in.close();
	}

}
