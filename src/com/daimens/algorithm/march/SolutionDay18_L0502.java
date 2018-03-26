package com.daimens.algorithm.march;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class SolutionDay18_L0502 {
	
//	public List<Integer> eventualSafeNodes(int[][] graph) {
////		List<Integer> g[] = new ArrayList[graph.length];
////        for (int i = 0; i < graph.length; i++){
////            g[i] = new ArrayList<>();
////        }
////        
////        for (int i = 0; i < graph.length; ++i) {
////        	for (int j = 0; j < graph[i].length; ++j) {
////        		g[i].add(graph[i][j]);
////        	}
////        }
//        
//        boolean[] vis = new boolean[graph.length];
//        boolean[] onStack = new boolean[graph.length];
//        for (int i = 0; i < graph.length; i++){
//        	if (!vis[i])
//        		dfs(graph, i, vis, onStack);
//        }
//        
//        List<Integer> ans = new ArrayList<>();
//        for (int i = 0; i < graph.length; ++i) if (!onStack[i]) ans.add(i); 
//        return ans;
//	}
//	
//	private boolean dfs(int g[][], int v, boolean[] vis, boolean[] onStack){
//		vis[v] = true;
//		onStack[v] = true;
//		for (int w : g[v]) {
//			if (!onStack[w]) {
//				if (dfs(g, w, vis, onStack)) return true;
//			}
//			else {
//				return true;
//			}
//		}
//		onStack[v] = false;
//		return false;
//	}
	
	public List<Integer> eventualSafeNodes(int[][] graph) {
		int n = graph.length;
		List<Integer>[] g = new ArrayList[n];
		for (int i = 0; i < n; ++i) g[i] = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < graph[i].length; ++j) {
				int u = i;
				int v = graph[i][j];
				g[v].add(u);
			}
		}
		
        int[] indegree = new int[n];	
        for (int i = 0; i < n; ++i) {
        	for (int v : g[i]) {
        		indegree[v] ++;
        	}
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i){
            if (indegree[i] == 0) q.offer(i);
        }
        
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
        	int u = q.poll();
        	for (int v : g[u]) {
        		indegree[v]--;
        		if (indegree[v] == 0) q.offer(v);
        	}
        	ans.add(u);
        }
        Collections.sort(ans);
        return ans;
	}
	
	
	public static void main(String[] args) {
		SolutionDay18_L0502 day = new SolutionDay18_L0502();
		int[][] graph = {{1, 2},{2, 3},{5},{0},{5},{},{}};
		System.out.println(day.eventualSafeNodes(graph));
	}

}
