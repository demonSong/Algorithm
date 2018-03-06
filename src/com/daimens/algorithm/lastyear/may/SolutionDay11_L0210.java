package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author DemonSong
 * 
 *         210. Course Schedule II
 * 
 *         here are a total of n courses you have to take, labeled from 0 to n -
 *         1.
 * 
 *         Some courses may have prerequisites, for example to take course 0 you
 *         have to first take course 1, which is expressed as a pair: [0,1]
 * 
 *         Given the total number of courses and a list of prerequisite pairs,
 *         return the ordering of courses you should take to finish all courses.
 * 
 *         There may be multiple correct orders, you just need to return one of
 *         them. If it is impossible to finish all courses, return an empty
 *         array.
 * 
 *         For example:
 * 
 *         2, [[1,0]] There are a total of 2 courses to take. To take course 1
 *         you should have finished course 0. So the correct course order is
 *         [0,1]
 * 
 *         4, [[1,0],[2,0],[3,1],[3,2]] There are a total of 4 courses to take.
 *         To take course 3 you should have finished both courses 1 and 2. Both
 *         courses 1 and 2 should be taken after you finished course 0. So one
 *         correct course order is [0,1,2,3]. Another correct ordering
 *         is[0,2,1,3].
 * 
 *         Note: The input prerequisites is a graph represented by a list of
 *         edges, not adjacency matrices. Read more about how a graph is
 *         represented. You may assume that there are no duplicate edges in the
 *         input prerequisites.
 * 
 *
 */
public class SolutionDay11_L0210 {
	
//	public int[] findOrder(int numCourses, int[][] prerequisites) {
//		List<Integer> graph[] = new ArrayList[numCourses];
//		for (int i = 0; i < numCourses; i++) {
//			graph[i] = new ArrayList<>();
//		}
//		boolean[] visited = new boolean[numCourses];
//		boolean[] onStack = new boolean[numCourses];
//		for (int[] pre : prerequisites) {
//			graph[pre[1]].add(pre[0]);
//		}
//
//		isCycle = false;
//		ans = new LinkedList<>();
//		for (int i = 0; i < numCourses; i++) {
//			if (!visited[i]) {
//				dfs(graph, i, visited, onStack);
//			}
//		}
//		
//		if (hasCycle()) return new int[]{};
//		int[] res = new int[ans.size()];
//		for (int i = 0; i < ans.size(); i++){
//			res[i] = ans.get(i);
//		}
//		return res;
//	}
//
//	List<Integer> ans = new LinkedList<>();
//	private void dfs(List<Integer> g[], int v, boolean[] visited, boolean[] onStack) {
//		visited[v] = true;
//		onStack[v] = true;
//		for (int w : g[v]) {
//			if (this.hasCycle())
//				return;
//			if (!visited[w])
//				dfs(g, w, visited, onStack);
//			else if (onStack[w]) {
//				isCycle = true;
//			}
//		}
//		onStack[v] = false;
//		ans.add(0,v);
//	}
//
//	boolean isCycle = false;
//	private boolean hasCycle() {
//		return isCycle;
//	}
	
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] ans = new int[numCourses];
		int[] indegree = new int[numCourses];
		int[][] matrix = new int[numCourses][numCourses];
		
		for (int[] pre : prerequisites){
			int prepr = pre[1];
			int ready = pre[0];
			indegree[ready]++;
			matrix[prepr][ready] = 1;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < numCourses; i++){
			if (indegree[i] == 0){
				queue.offer(i);
			}
		}
		
		int count = 0;
		while (!queue.isEmpty()){
			int v = queue.poll();
			ans[count ++] = v;
			for (int i = 0; i < numCourses; i++){
				if (matrix[v][i] == 1){
					indegree[i]--;
					if (indegree[i] == 0) queue.offer(i);
				}
			}
		}
		return count == numCourses ? ans : new int[0];
	}
	
//	public int[] findOrder(int numCourses, int[][] prerequisites) {
//		
//		if (prerequisites.length == 0){
//			int[] hh = new int[numCourses];
//			for (int i = 0; i < numCourses; i++){
//				hh[i] = i;
//			}
//			return hh;
//		}
//		
//		List<Integer> graph[] = new ArrayList[numCourses];
//		for (int i = 0; i < numCourses; i++){
//			graph[i] = new ArrayList<>();
//		}
//		boolean[] visited = new boolean[numCourses];
//		for (int[] pre : prerequisites){
//			graph[pre[1]].add(pre[0]);
//		}
//		
//		if (!canFinish(numCourses, prerequisites)) return new int[]{};
//		
//		for (int i = 0; i < numCourses; i++){
//			if (!visited[i]) dfs(graph,visited,i);
//		}
//		
//		for (int i = 0; i < visited.length; i++){
//			if (!visited[i]){
//				ans.add(i);
//			}
//		}
//		
//		int[] res = new int[ans.size()];
//		int count = 0;
//		for (int i = 0; i < ans.size(); i++){
//			res[count++] = ans.get(i);
//		}
//		
//		return res;
//    }
//	
//	List<Integer> ans = new LinkedList<>();
//	private void dfs(List<Integer> graph[], boolean[] visited, int start){
//		//如果该课程没有其他先决课程，那么直接把它加入到ans即可
//		for (int i = 0; i < graph[start].size(); i++){
//			visited[start] = true;
//			visited[graph[start].get(i)] = true;
//			dfs(graph, visited, graph[start].get(i));
//		}
//		if (visited[start] && !ans.contains(start)) ans.add(0,start);
//	}
//	
//	public boolean canFinish(int numCourses, int[][] prerequisites) {
//		List<Integer> graph[] = new ArrayList[numCourses];
//		for (int i = 0; i < numCourses; i++){
//			graph[i] = new ArrayList<>();
//		}
//		boolean[] visited = new boolean[numCourses];
//		for (int[] pre : prerequisites){
//			graph[pre[0]].add(pre[1]);
//		}
//		
//		for (int i = 0; i < numCourses; i++){
//			if (helper(graph, visited, i)){
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	private boolean helper(List<Integer> graph[], boolean[] visited,int start){
//		if (visited[start]) return true;
//		visited[start] = true;
//		for (int i = 0; i < graph[start].size(); i++){
//			if(helper(graph, visited, graph[start].get(i))){
//				return true;
//			}
//		}
//		//这句话是关键
//		visited[start] = false;
//		return false;
//	}
	
	public static void main(String[] args) {
		SolutionDay11_L0210 day = new SolutionDay11_L0210();
		day.findOrder(3, new int[][]{{2,0},{2,1}});
	}

}
