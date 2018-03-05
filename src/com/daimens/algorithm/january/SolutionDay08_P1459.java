package com.daimens.algorithm.january;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class SolutionDay08_P1459 {
	
	String INPUT = "./data/judge/201801/P1459.txt";
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static int next() throws IOException {
		in.nextToken();
		in.nextToken();
		return (int) in.nval;
	}
	
	
	public static void main(String[] args) throws IOException {
		new SolutionDay08_P1459().read();
	}
	
	class Edge{
		int to;
		int cap;
		int rev;
		
		Edge(int to, int cap, int rev){
			this.to  = to;
			this.cap = cap;
			this.rev = rev;
		}
		
		@Override
		public String toString() {
			return "(" + to + ", " + cap + "," + rev + ")";
		}
	}
	
	static final int INF = 0x3f3f3f3f;
	
	List<Edge>[] graph;
	int[] level;
	
	void addEdge(int from, int to, int cap) {
		graph[from].add(new Edge(to, cap, graph[to].size()));
		graph[to].add(new Edge(from, 0, graph[from].size() - 1));
	}
	
	void bfs(int s, int to) {
		Arrays.fill(level, -1);
		Queue<Integer> q = new ArrayDeque<Integer>();
		level[s] = 0;
		q.offer(s);
		while (!q.isEmpty()) {
			int now = q.poll();
			for (Edge e : graph[now]) {
				if (level[e.to] < 0 && e.cap > 0) {
					level[e.to] = level[now] + 1;
					q.offer(e.to);
				}
			}
		}
	}
	
	int dfs(int s, int v, int f, boolean[] vis) {
		if (s == v) return f;
		vis[s] = true;
		for (Edge e : graph[s]) {
			int to  = e.to;
			if (!vis[to] && e.cap > 0 && level[s] + 1 == level[to]) {
				int d = dfs(to, v, Math.min(f, e.cap), vis);
				if (d > 0) {
					e.cap -= d;
					graph[to].get(e.rev).cap += d;
					return d;
				}
			}
		}
		return 0;
	}
	
	int dinic(int s, int to, int V) {
		int flow = 0;
		for(;;) {
			bfs(s, to);
			if (level[to] < 0) break;
			int f = 0;
			while ((f = dfs(s, to, INF, new boolean[V])) > 0) flow += f;
		}
		return flow;
	}
	
	void read() throws IOException {
		while(in.nextToken() != StreamTokenizer.TT_EOF) {
			int n  = (int) in.nval;  // n 个顶点
			int np = nextInt();  // power stations
			int nc = nextInt();  // consumers
			int m  = nextInt();  // power stations line
			
			graph = new ArrayList[n + 2];
			level = new int[n + 2];
			for (int i = 0; i < n + 2; ++i) graph[i] = new ArrayList<Edge>();
			for (int i = 0; i < m; ++i) {
				addEdge(next() + 1, next() + 1, next());
			}
			
			for (int i = 0; i < np; ++i) {
				addEdge(0, next() + 1, next());
			}
			
			for (int i = 0; i < nc; ++i) {
				addEdge(next() + 1, n + 1, next());
			}
			
			System.out.println(dinic(0, n + 1, n + 2));
		}
	}
}

