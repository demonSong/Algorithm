package com.daimens.algorithm.september;

import java.util.Arrays;

public class SolutionDay24_L0684 {
	
	class Union{
		int[] id;
		int[] sz;
		
		Union(int n){
			id = new int[n];
			sz = new int[n];
			for (int i = 0; i < n; ++i) {
				id[i] = i;
			}
			Arrays.fill(sz, 1);
		}
		
		int find(int i) {
			while (i != id[i]) {
				i = id[i];
			}
			return i;
		}
		
		boolean same(int i, int j) {
			return find(i) == find(j);
		}
		
		void union(int i, int j) {
			int p = find(i);
			int q = find(j);
			if (p == q) return;
			
			if (sz[p] > sz[q]) {
				id[q] = p;
				sz[p] += sz[q];
			}
			else {
				id[p] = q;
				sz[q] += sz[p];
			}
		}
	}
	
	public int[] findRedundantDirectedConnection(int[][] edges) {
		int n = edges.length;
		Union union = new Union(2000 + 16);
		for (int i = 0; i < n; ++i) {
			if (union.same(edges[i][0], edges[i][1])) return new int[] {edges[i][0], edges[i][1]};
			else {
				union.union(edges[i][0], edges[i][1]);
			}
		}
		return new int[] {-1,-1};
    }
	
	public static void main(String[] args) {
		SolutionDay24_L0684 day = new SolutionDay24_L0684();
	}

}
