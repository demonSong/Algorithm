package com.daimens.algorithm.july;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SolutionDay20_P1273 {
	
	static class Edge{
		int from;
		int to;
		int cap;
		int rev;
		
		public Edge(int from, int to, int cap, int rev){
			this.from = from;
			this.to = to;
			this.cap = cap;
			this.rev = rev;
		}
		
		@Override
		public String toString() {
			return from + " " + to + " " + cap;
		}
	}
	
	
	static List<Edge>[] g;
	static int[] level;
	static int N;
	static int INF = 1 << 30;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			String[] nums = in.nextLine().trim().split(" ");
			int M = Integer.parseInt(nums[0]);
			N = Integer.parseInt(nums[1]);
			level = new int[N];
			
			g = new ArrayList[N];
			for (int i = 0; i < N; ++i){ 
				g[i] = new ArrayList<Edge>();
			}
			
			for (int i = 0; i < M; ++i){
				nums = in.nextLine().trim().split(" ");
				int from = Integer.parseInt(nums[0]);
				int to = Integer.parseInt(nums[1]);
				int cap = Integer.parseInt(nums[2]);
				from --;
				to --;
				addEdge(from, to, cap);
			}
			System.out.println(dinic());
		}
		in.close();
	}
	
	public static void addEdge(int from, int to, int cap){
		g[from].add(new Edge(from, to, cap, g[to].size()));
		g[to].add(new Edge(to, from, 0, g[from].size() - 1));
	}
	
	public static void bfs(int s){
		for (int i = 0; i < N; ++i) level[i] = -1;
		level[s] = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(s);
		while (!queue.isEmpty()){
			int v = queue.poll();
			for (Edge e : g[v]){
				int to = e.to;
				if (e.cap > 0 && level[to] < 0){
					level[to] = level[v] + 1;
					queue.offer(to);
				}
			}
		}
	}
	
	public static int dfs(int s, int t, int f, boolean[] visited){
		if (s == t) return f;
		visited[s] = true;
		for (Edge e : g[s]){
			int from = e.from;
			int to = e.to;
			if (!visited[to] && level[from] + 1 == level[to] && e.cap > 0){
				int d = dfs(to, t, Math.min(f, e.cap), visited);
				if (d > 0){
					e.cap -= d;
					g[to].get(e.rev).cap += d;
					return d;
				}
			}
		}
		return 0;
	}
	
		
	public static int dinic(){
		int flow = 0;
		for(;;){
			bfs(0);
			if (level[N - 1] < 0) break;
			int f = 0;
			while ((f = dfs(0, N - 1, INF, new boolean[N])) > 0) flow += f;
		}
		return flow;
	}

}
