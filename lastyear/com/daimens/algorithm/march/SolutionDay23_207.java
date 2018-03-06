package com.daimens.algorithm.march;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author DemonSong
 * 207.Course Schedule
 * There are a total of n course you have to take,labeled from to n -1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed
 * as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * 
 * For example,
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you 
 * should also have finished course 1. So it is impossible.
 * 
 * Note:
 * 1. The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is  represented.
 * 2. You may assume that there are no duplicates edges in the input prerequisites.
 *
 */
public class SolutionDay23_207 {

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		
		int[][] matrix = new int[numCourses][numCourses];
		int[] indegree = new int[numCourses];
		
		for(int i = 0; i < prerequisites.length; i++){
			int ready = prerequisites[i][0];
			int pre = prerequisites[i][1];
			
			if(matrix[pre][ready] == 0){
				indegree[ready]++;
			}
			matrix[pre][ready] = 1;
		}
		
		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 0; i < indegree.length; i++){
			if(indegree[i] == 0) queue.offer(i);
		}
		
		while(!queue.isEmpty()){
			int course = queue.poll();
			count++;
			for(int i = 0; i < numCourses;i++){
				if(matrix[course][i] != 0){
					if(--indegree[i] == 0)
						queue.offer(i);
				}
			}
		}
		
        return count == numCourses;
    }
	
	
}
