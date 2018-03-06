package com.daimens.algorithm.july;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolutionDay18_P3713 {
	
	static class Edge{
		int from;
		int to;
		
		public Edge(int from, int to){
			this.from = from;
			this.to = to;
		}
	}
	
	static List<Edge>[] g;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			int N = in.nextInt();
			int M = in.nextInt();
			if (N == 0 && M == 0) break;
			
			init(N);
			
			g = new ArrayList[N];
			for (int i = 0; i < N; ++i) g[i] = new ArrayList<Edge>();
			
			for (int i = 0; i < M; ++i){
				int from = in.nextInt();
				int to = in.nextInt();
				g[from].add(new Edge(from, to));
				g[to].add(new Edge(to, from));
			}
			System.out.println(solve() ? "YES" : "NO");
		}
		in.close();
	}
	
	static int V;
	static int[] dfn;
	static int[] low;
	static int index;
	static int[] status; // 0. 没有访问 1. 正在访问 2. 已经访问
	static int root;
	static int[] is_cut_vertex;
	static boolean has_cut_vertex;
	private static void init(int N){
		V = N;
		dfn = new int[N];
		low = new int[N];
		index = 0;
		status = new int[N];
		is_cut_vertex = new int[N];
		has_cut_vertex = false;
	}
	
	public static void tarjan(int x, int from){
		status[x] = 1;
		dfn[x] = low[x] = ++index;
		int sub_tree = 0;
		int v;
		for (Edge e : g[x]){
			v = e.to;
			if (v != from && status[v] == 1){
				low[x] = Math.min(low[x], dfn[v]);
			}
			if (status[v] == 0){
				tarjan(v, x);
				++sub_tree;
				low[x] = Math.min(low[x], low[v]);
				if ((x == root && sub_tree > 1) || x != root && low[v] >= dfn[x]){
					is_cut_vertex[x] = 1;
					has_cut_vertex = true;
				}
			}
		}
		status[x] = 2;
	}
	
	private static void calc(int del){
		is_cut_vertex = new int[V];
		status = new int[V];
		low = new int[V];
		dfn = new int[V];
		
		status[del] = 2;
		root = 0;
		if (del == 0){
			root = 1;
		}
		tarjan(root, -1);
	}
	
	private static boolean solve(){
		for (int i = 0; i < V; ++i){
			calc(i);
			for (int j = 0; j < V; ++j){
				if (0 == status[j]){
					has_cut_vertex = true;
					break;
				}
			}
			
			if (has_cut_vertex){
				break;
			}
		}
		return !has_cut_vertex;
	}
}