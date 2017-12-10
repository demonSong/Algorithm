package com.daimens.algorithm.december;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SolutionDay10_L0501 {
	
	class Edge{
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	class Node implements Comparable<Node>{
		
		int v;
		int dist;
		
		Node(int v, int dist){
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node that) {
			return this.dist - that.dist;
		}
	}
	
    public int networkDelayTime(int[][] times, int N, int K) {
    	
    	int n = times.length;
    	List<Edge>[] graph = new ArrayList[N];
    	
    	for (int i = 0; i < N; ++i) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	for (int i = 0; i < n; ++i) {
    		int from   = times[i][0];
    		int to     = times[i][1];
    		int weight = times[i][2];
    		from --;
    		to --;
    		graph[from].add(new Edge(from, to, weight));
    	}
    	
    	int ans = 0;
    	boolean[] vis = new boolean[N];
    	
    	Queue<Node> queue = new PriorityQueue<>();
    	queue.offer(new Node(K - 1, 0));
    	
    	int[] dist = new int[N];
    	Arrays.fill(dist, 0x3f3f3f3f);
    	
    	while (!queue.isEmpty()) {
    		Node now = queue.poll();
    		for (Edge e : graph[now.v]) {
    			if (!vis[e.from]) {
    				Node nxt = new Node(e.to, now.dist + e.weight);
    				dist[e.to] = Math.min(dist[e.to], nxt.dist);
    				queue.offer(nxt);
    			}
    		}
    		vis[now.v] = true;
    	}
    	
    	for (int i = 0; i < N; ++i) {
    		if (i == K - 1) continue;
    		ans = Math.max(ans, dist[i]);
    	}
    	
    	
    	return ans >= 0x3f3f3f3f ? -1 : ans;
    }
	
	public static void main(String[] args) {
		SolutionDay10_L0501 day = new SolutionDay10_L0501();
		int[][] times = {{2, 1, 1},{2, 3, 1},{3, 4, 1}};
		System.out.println(day.networkDelayTime(times, 4, 2));
	}

}
