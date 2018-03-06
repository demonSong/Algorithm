package com.daimens.algorithm.july;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SolutionDay23_P1466 {
	InputStream is;
	PrintWriter out;
	String INPUT = "./data/judge/201707/1466.txt";
	
	void solve() {
		while (in.hasNext()){
			int n = in.nextInt();
			init(n);
			for (int i = 0; i < n; ++i){
				in.next();
				String str = in.next();
				int num = Integer.parseInt(str.substring(1, str.length() - 1));
				for (int j = 0; j < num; ++j){
					int id = in.nextInt();
					addEdge(i, id);
				}
			}
			int pairs = bipartiteMatching();
			System.out.println(n - pairs);
		}
		in.close();
	}
	
	//二分图
	List<Integer>[] g;
	int[] matching;
	int V;
	public void init(int n){
		V = n;
		g = new ArrayList[V];
		for (int i = 0; i < V; ++i) g[i] = new ArrayList<Integer>();
		matching = new int[V];
	}
	
	
	public void addEdge(int from, int to){
		g[from].add(to);
	}
	
	public boolean dfs(int v, boolean[] visited){
		visited[v] = true;
		for (int u : g[v]){
			int w = matching[u];
			if (w == -1 || !visited[w] && dfs(w, visited)){
				matching[u] = v;
				matching[v] = u;
				return true;
			}
		}
		return false;
	}
	
	public int bipartiteMatching(){
		int res = 0;
		Arrays.fill(matching, -1);
		for (int i = 0; i < V; ++i){
			if (matching[i] < 0){
				if (dfs(i, new boolean[V])){
					res ++;
				}
			}
		}
		return res;
	}
	
	Scanner in;
	public SolutionDay23_P1466(){
		in = new Scanner(System.in);
	}
	
	void run() throws Exception {
		solve();
	}

	public static void main(String[] args) throws Exception {
		new SolutionDay23_P1466().run();
	}
}


