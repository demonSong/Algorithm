package com.daimens.algorithm.july;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SolutionDay09_H1269 {
	Scanner is;
	PrintWriter out;
	
	class Edge{
		int from;
		int to;
		
		public Edge(int from, int to){
			this.from = from;
			this.to = to;
		}
	}
	
	List<Edge>[] g;
	List<Edge>[] rg;
	boolean[] marked;
	void solve() {
		while (is.hasNext()){
			int N = is.nextInt();
			int M = is.nextInt();
			if (N + M == 0) break;
			g = new ArrayList[N];
			rg = new ArrayList[N];
			for (int i = 0; i < N; ++i){
				g[i] = new ArrayList<>();
				rg[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; ++i){
				int from = is.nextInt();
				int to = is.nextInt();
				from--;
				to--;
				g[from].add(new Edge(from, to));
				rg[to].add(new Edge(to, from));
			}
			marked = new boolean[N];	
			DepthFirstOrder order = new DepthFirstOrder(rg);
			Stack<Integer> reverseOrder = order.reverseOrder();
			int cnt = 0;
			while (!reverseOrder.isEmpty()){
				int v = reverseOrder.pop();
				if (!marked[v]){
					dfs(g, v);
					cnt++;
				}
			}
			if (cnt == 1) out.println("Yes");
			else out.println("No");
		}
	}
		
	private void dfs(List<Edge>[] g, int v){
		marked[v] = true;
		for (Edge e : g[v]){
			int to = e.to;
			if (!marked[to]) dfs(g, to);
		}
	}
	
	class DepthFirstOrder{
		
		boolean[] marked;
		Stack<Integer> reverse;
		List<Edge>[] graph;
		
		public DepthFirstOrder(List<Edge>[] graph){
			this.graph = graph;
			int n = graph.length;
			marked = new boolean[n];
			reverse = new Stack<>();
			for (int i = 0; i < n; ++i){
				if (!marked[i]) dfs(graph, i);
			}
		}
		
		public void dfs(List<Edge>[] g, int v){
			marked[v] = true;
			for (Edge e : g[v]){
				int to = e.to;
				if (!marked[to]) dfs(g, to);
			}
			reverse.push(v);
		}
		
		public Stack<Integer> reverseOrder(){
			return this.reverse;
		}
	}
	
	void run() throws Exception {
		is = new Scanner(System.in);
		out = new PrintWriter(System.out);
		solve();
		out.flush();
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay09_H1269().run();
	}
}