package com.daimens.algorithm.april;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SolutionDay08_L0815 {
	
    public int numBusesToDestination(int[][] routes, int S, int T) {
    	Map<Integer, Set<Integer>> to_routes = new HashMap<>();
    	for (int i = 0; i < routes.length; ++i) {
    		for (int r : routes[i]) {
    			to_routes.computeIfAbsent(r, k -> new HashSet<>()).add(i);
    		}
    	}
    	
    	Queue<Integer> q = new ArrayDeque<>();
    	Set<Integer> seen = new HashSet<>();
    	seen.add(S);
    	q.offer(S);
    	int bus = 0;
    	while (!q.isEmpty()) {
    		int len = q.size();
    		for (int i = 0; i < len; ++i) {
    			int stop = q.poll();
    			if (stop == T) return bus;
    			for (int r : to_routes.get(stop)) {
    				for (int s : routes[r]) {
    					if (!seen.contains(s)) {
    						seen.add(s);
    						q.offer(s);
    					}
    				}
    			}
    		}
    		bus ++;
    	}
    	return -1;
    }
	
	public static void main(String[] args) {
		SolutionDay08_L0815 day = new SolutionDay08_L0815();
		int[][] r = {{1, 2, 7}, {3, 6, 7}};
		System.out.println(day.numBusesToDestination(r, 1, 6));
	}

}
