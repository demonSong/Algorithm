package com.daimens.algorithm.july;

import java.util.Scanner;

public class SolutionDay20_P2914 {
	
//	static class Edge{
//		int to;
//		int cap;
//		
//		public Edge(int to, int cap){
//			this.to = to;
//			this.cap = cap;
//		}
//		
//		@Override
//		public String toString() {
//			return to + " " + cap;
//		}
//	}
//	
//	static int[][] g;
//	static int V;
//	static int INF = 1 << 30;
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		while (in.hasNext()){
//			int N = in.nextInt();
//			int M = in.nextInt();
//			V = N;
//			g = new int[V][V];
//			for (int i = 0; i < M; ++i){
//				int from = in.nextInt();
//				int to = in.nextInt();
//				int cap = in.nextInt();
//				addEdge(from, to, cap);
//			}
//			int minCut = INF;
//			for (int i = 0; i < V; ++i){
//				for (int j = i + 1; j < V; ++j){
//					int flow = fordFulkerson(i, j);
//					if (flow != 0)
//						minCut = Math.min(minCut, flow);
//				}
//			}
//			System.out.println(minCut);
//		}
//		in.close();
//	}
//	
//	public static void addEdge(int from, int to, int cap){
//		g[from][to] += cap;
//		g[to][from] += cap;
//	}
//	
//	public static int fordFulkerson(int s, int t){
//		int flow = 0;
//		for (;;){
//			int f = dfs(s, t, INF, new boolean[V]);
//			if (f > 0) flow += f;
//			else break;
//		}
//		return flow;
//	}
//	
//	public static int dfs(int s, int t, int f, boolean[] visited){
//		if (s == t) return f;
//		visited[s] = true;
//		for (int i = 0; i < V; ++i){
//			if (!visited[i] && g[s][i] > 0){
//				int d = dfs(i, t, Math.min(f, g[s][i]), visited);
//				if (d > 0){
//					g[s][i] -= d;
//					g[i][s] += d;
//					return d;
//				}
//			}
//		}
//		return 0;
//	}
	
	
	static int INF = 0x3f3f3f3f;
	static int[][] g;
	static int[] v;
	static int[] w;
	static int N;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()){
			String[] nums = in.nextLine().trim().split(" ");
			N = Integer.parseInt(nums[0]);
			int M = Integer.parseInt(nums[1]);
			g = new int[N][N];
			v = new int[N];
			for (int i = 0; i < M; ++i){
				nums = in.nextLine().trim().split(" ");
				int from = Integer.parseInt(nums[0]);
				int to = Integer.parseInt(nums[1]);
				int cap = Integer.parseInt(nums[2]);
				g[from][to] += cap;
				g[to][from] += cap;
			}
			System.out.println(stoerWagner(N));
		}
		in.close();
	}
	
	public static int stoerWagner(int n){
		int minCut = INF;
		for (int i = 0; i < n; ++i){
			v[i] = i;
		}
		
		while (n > 1){
			int pre = 0;
			w = new int[N];
			visited = new boolean[N];
			for (int i = 1; i < n; ++i){
				int k = -1;
				for (int j = 1; j < n; ++j){ //求每次加入集合A之后的最大权值
					if (!visited[v[j]]){
						w[v[j]] += g[v[pre]][v[j]];
						if (k == -1 || w[v[k]] < w[v[j]]){
							k = j;
						}
					}
				}
				visited[v[k]] = true;
				if (i == n - 1){
					int s = v[pre], t = v[k];
					minCut = Math.min(minCut, w[v[k]]); //更新最小割集
					for (int j = 0; j < n; ++j){  //结点s和结点t合并
						g[s][v[j]] += g[v[j]][t];
						g[v[j]][s] += g[v[j]][t];
					}
					v[k] = v[--n]; //删除结点k
				}
				pre = k;
			}
		}
		return minCut;
	}
	
}
