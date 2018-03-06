package com.daimens.algorithm.september;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class SolutionDay13_T0010 {
	
	static class Edge{
		int from;
		int to;
		int cost;
		
		Edge(int from, int to, int cost){
			this.from = from;
			this.to   = to;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return from + " " + to + " " + cost;
		}
		
	}
	
	static List<Edge>[] graph;
	static final int INF = 0x3f3f3f3;
	
	static void add(int from, int to, int cost) {
		graph[from].add(new Edge(from, to, cost));
		graph[to].add(new Edge(to, from, cost));
	}
	
	static class Pair implements Comparable<Pair>{
		int v;
		int d;
		Pair(int v, int d){
			this.v = v;
			this.d = d;
		}
		@Override
		public int compareTo(Pair o) {
			return this.d - o.d;
		}
	}
	
	static boolean[] vis;
	static int[] dist;
	static int[] path;
	static void dijkstra(int s, int t) {
		Queue<Pair> queue = new PriorityQueue<>();
		dist = new int[N];
		path = new int[N];
		vis = new boolean[N];
		Arrays.fill(dist, INF);
		Arrays.fill(path, -1);
		dist[s] = 0;
		queue.offer(new Pair(s, 0));
		while (!queue.isEmpty()) {
			Pair now = queue.poll();
			int v = now.v;
			if (vis[v]) continue;
			vis[v] = true;
			for (Edge e : graph[v]) {
				int cost = e.cost;
				int to = e.to;
				if (dist[to] > dist[v] + cost) {
					dist[to] = dist[v] + cost;
					path[to] = v;
					queue.offer(new Pair(to, dist[to]));
				}
			}
		}
	}
	
	static int N;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			N = in.nextInt();
			int K = in.nextInt();
			graph = new ArrayList[N];
			for (int i = 0; i < N; ++i) graph[i] = new ArrayList<>();
			for (int i = 0; i < N; ++i) {
				String line = in.next().trim();
				for (int j = 0; j < N; ++j) {
					int dist = line.charAt(j) - '0';
					add(i, j, dist);
				}
			}
			
			dijkstra(0, 1);
			int cnt = 0;
			for (int i = path[1]; i != 0; i = path[i]) {
				cnt ++;
			}
			if (K >= cnt) System.out.println(dist[1] / 2);
			else {
				Queue<Integer> pp = new PriorityQueue<>((a, b) -> (b - a));
				for (int i = path[1]; i != 0; i = path[i]) {
					pp.offer(dist[i]);
				}
				int sum = 0;
				for (int i = 0; i < K; ++i) {
					sum += pp.poll();
				}
				System.out.println(dist[1] - sum / 2);
			}
			
		}
		in.close();
	}
	
	

}
