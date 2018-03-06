package com.daimens.algorithm.july;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolutionDay04_P1273 {
	
	static class Edge{
		int from;
		int to;
		int cap;
		
		public Edge(int from, int to, int cap){
			this.from = from;
			this.to = to;
			this.cap = cap;
		}
		
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", cap=" + cap + "]";
		}
	}
	
	
	
	static List<Edge>[] g;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			String line = in.nextLine().trim();
			String[] nums = line.split(" ");
			int M = Integer.parseInt(nums[0]);
			int N = Integer.parseInt(nums[1]);
			g = new ArrayList[N];
			for (int i = 0; i < N; ++i){ 
				g[i] = new ArrayList<Edge>();
				for (int j = 0; j < N; ++j){
					g[i].add(new Edge(i, j, 0));
				}
			}
			for (int i = 0; i < M; ++i){
				line = in.nextLine().trim();
				nums = line.split(" ");
				int from = Integer.parseInt(nums[0]);
				int to = Integer.parseInt(nums[1]);
				int cap = Integer.parseInt(nums[2]);
				from--;
				to--;
				g[from].add(new Edge(from, to, cap));
			}
			System.out.println(fordFulkerson());
		}
		in.close();
	}
	
	
	static final int INF = 1 << 30;
	public static int fordFulkerson(){
		int n = g.length;
		int flow = 0;
		for (;;){
			boolean[] visited = new boolean[n];
			int d = dfs(0, n - 1, INF, visited);
			if (d != 0){
				flow += d;
			}
			else break;
		}
		return flow;
	}
	
	public static int dfs(int s, int t, int F, boolean[] visited){
		if (s == t) return F;
		visited[s] = true;
		for (Edge e : g[s]){
			int to = e.to;
			if (!visited[to] && e.cap > 0){
				int d = dfs(to, t, Math.min(e.cap, F), visited);
				if (d > 0){
					e.cap -= d;
					g[to].get(s).cap += d;
					return d;
				}
			}
		}
		return 0;
	}
}
