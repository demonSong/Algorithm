package com.daimens.algorithm.march;

import java.util.ArrayList;
import java.util.List;

public class SolutionDay11_L0501 {
	List<Integer>[] map;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    	ans = new ArrayList<>();
    	int n = graph.length;
    	map = new ArrayList[n];
    	for (int i = 0; i < n; ++i) map[i] = new ArrayList<>();
    	for (int i = 0; i < n; ++i) {
    		for (int j = 0; j < graph[i].length; ++j) {
    			map[i].add(graph[i][j]);
    		}
    	}
    	
    	dfs(map, 0, new ArrayList<>());
    	return ans;
    }
    
    List<List<Integer>> ans;
    
    void dfs(List<Integer>[] map, int s, List<Integer> path) {
    	path.add(s);
    	if(map[s].size() == 0) {
    		ans.add(new ArrayList<>(path));
    	}
    	else {
    		for (int to : map[s]) {
    			dfs(map, to, path);
    			path.remove(path.size() - 1);
    		}
    	}
    }
    
	public static void main(String[] args) {
		SolutionDay11_L0501 day = new SolutionDay11_L0501();
		int[][] graph = {{1, 2},{3},{3},{}};
		System.out.println(day.allPathsSourceTarget(graph));
	}
	
}
